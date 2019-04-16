package com.java.frame.proxy;

import com.java.frame.auto.MyColumn;
import com.java.frame.exception.MyComponentException;
import com.java.frame.handler.*;
import com.java.frame.jdbc.DataSourcePool;
import com.java.frame.util.PatternUtils;
import com.java.frame.util.StringUntils;
import org.springframework.cglib.proxy.MethodInterceptor;
import org.springframework.cglib.proxy.MethodProxy;

import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.sql.*;
import java.time.LocalDateTime;
import java.time.ZoneOffset;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;

import static com.java.frame.exception.JavaType.*;

/**
 * 自定义MethodInterceptor
 * 这个接口只有一个intercept()方法，这个方法有4个参数：
 * 1. obj表示增强的对象，即实现这个接口类的一个对象；
 * 2. method表示要被拦截的方法；
 * 3. args表示要被拦截方法的参数；
 * 4. proxy表示要触发父类的方法对象；
 *
 * @author xuweizhi
 * @date 2019/04/13 20:04
 */
public class MyMapperProxy implements MethodInterceptor {

    private Map<String, Object> mapper;

    private Class<?> clazz;

    private DataSourcePool pool;

    public MyMapperProxy(Map<String, Object> mapper, Class<?> clazz, DataSourcePool pool) {
        this.mapper = mapper;
        this.clazz = clazz;
        this.pool = pool;
    }

    @Override
    public Object intercept(Object sub, Method method, Object[] objects, MethodProxy methodProxy) throws Throwable {
        Object invoke = null;
        String methodKey = this.clazz.getName() + "#" + method.getName();
        Object obj = mapper.get(methodKey);
        // 1. 解析 MySelect 注解
        if (obj instanceof MySelectMapping) {
            invoke = invokeSelect(method, objects, (MySelectMapping) obj);
        }
        // 2. 解析 MyInsert 注解
        if (obj instanceof MyInsertMapping) {
            MyInsertMapping mapping = (MyInsertMapping) obj;
            String sql = mapping.getSql();
            Class<?> clazz = Class.forName(mapping.getNameSpace());
            Object clazzObject = objects[0];
            parseMyInsert(sql, clazz, clazzObject);
            //if (method.getReturnType().getName().equals(boolean.class) || method.getReturnType().getName().equals(Boolean.class)) {
            //    invoke = execute;
            //}
            //if (method.getReturnType().getName().equals(int.class) || method.getReturnType().getName().equals(Integer.class)) {
            //    if (execute) {
            //        invoke = 1;
            //    } else {
            //        invoke = 2;
            //    }
            //}
        }
        // 3. 解析 MyUpdateMapping 注解
        if (obj instanceof MyUpdateMapping) {
            MyUpdateMapping mapping = (MyUpdateMapping) obj;
            String sql = mapping.getSql();
            Class<?> clazz = Class.forName(mapping.getNameSpace());
            Object clazzObject = objects[0];
            parseMyInsert(sql, clazz, clazzObject);
        }
        // 4. 解析 MyDelete 注解
        if (obj instanceof MyDeleteMapping) {

            MyDeleteMapping mapping = (MyDeleteMapping) obj;

            // sql
            String sql = mapping.getSql();

            // sql 对应数据库实体
            Class<?> clazz = Class.forName(mapping.getNameSpace());

            if (sql.indexOf("where") != -1 || sql.indexOf("WHERE") != -1) {
                int indexOf = sql.indexOf(("where"));
                if (indexOf == -1) {
                    indexOf = sql.indexOf(("WHERE"));
                }
                // delete * from select where
                String prefix = sql.substring(0, indexOf + 5);
                // u_id = #{uId} and
                String suffix = sql.substring(indexOf + 5);
                // 替换掉所有的 and
                suffix = suffix.replaceAll("\\band\\b", "");
                StringBuilder sb = new StringBuilder(prefix + " 1 = 1");

                List<String> list = new ArrayList<>();
                while (suffix.contains("}")) {
                    list.add(" and " + suffix.substring(0, suffix.indexOf("}") + 1).trim());
                    suffix = suffix.substring(suffix.indexOf("}") + 1, suffix.length());
                }

                if (objects.length == 1) {
                    Object param = objects[0];
                    // 1. 如果是哪个啥，哈哈哈
                    if (param.getClass().getName().equals(clazz.getName())) {
                        int end = 0;
                        List<Object> objs = new ArrayList<>();
                        List<String> objss = new ArrayList<>();
                        for (String str : list) {
                            Field field = clazz.getDeclaredField(str.substring(str.indexOf("{") + 1, str.indexOf("}")).trim());
                            field.setAccessible(true);
                            Object o = field.get(param);
                            if (o != null) {
                                sb.append(str);
                                end++;
                                objs.add(o);
                                objss.add(o.getClass().getName());
                            }
                        }
                        sql = PatternUtils.replaceSql(sb.toString());
                        PreparedStatement ps = pool.getConnection().prepareStatement(sql);
                        for (int i = 0; i < end; i++) {
                            setParam(objs.toArray(), objss, ps, i, i + 1);
                        }
                        ps.execute();
                        // 2. 如果参数是 int 或者Integer 类型
                    } else {
                        sb.append(list.get(0));
                        sql = PatternUtils.replaceSql(sb.toString());
                        PreparedStatement ps = pool.getConnection().prepareStatement(sql);
                        setParam(param, param.getClass().getName(), ps, 1);
                        ps.execute();
                    }
                }

            } else if (sql.indexOf("orderby") != -1 || sql.indexOf("ORDERBY") != -1) {
                int indexOf = sql.indexOf(("orderby"));
                if (indexOf == -1) {
                    indexOf = sql.indexOf(("ORDERBY"));
                }
            } else {
                PreparedStatement prepareStatement = pool.getConnection().prepareStatement(mapping.getSql());
                prepareStatement.execute();
            }
        }
        // 5.解析 MyLocalMethod 注解
        if (obj instanceof MyLocalMethodMapping) {
            invoke = parseLocal(invoke, (MyLocalMethodMapping) obj);
            return invoke;
        }
        // 6.解析 MyLocalMethodReinforce 注解
        invoke = parseReinforce(method, objects, invoke);
        return invoke;
    }

    private void parseMyInsert(String sql, Class<?> clazz, Object clazzObject) throws SQLException, NoSuchFieldException, IllegalAccessException {
        String replaceSql = PatternUtils.replaceSql(sql);
        PreparedStatement prepareStatement = pool.getConnection().prepareStatement(replaceSql);
        Matcher matcher = PatternUtils.getSqlMatcher(sql);
        List<String> javaTypes = new ArrayList<>();
        while (matcher.find()) {
            javaTypes.add(matcher.group().substring(2, matcher.group().length() - 1).trim());
        }
        for (int i = 0; i < javaTypes.size(); i++) {
            Field field = clazz.getDeclaredField(javaTypes.get(i));
            MyColumn column = field.getAnnotation(MyColumn.class);
            field.setAccessible(true);
            if (column != null) {
                Object o = field.get(clazzObject);
                setParam(o, field.getType().getName(), prepareStatement, i + 1);
            } else {
                throw new MyComponentException(clazz.getName() + field.getName() + "must indicate " + MyColumn.class + " annotations !");
            }
        }
        boolean execute = prepareStatement.execute();
    }

    private Object invokeSelect(Method method, Object[] objects, MySelectMapping obj) throws SQLException, NoSuchFieldException, IllegalAccessException, ClassNotFoundException, InstantiationException, java.lang.reflect.InvocationTargetException, NoSuchMethodException {
        Object invoke;
        MySelectMapping select = obj;
        Connection con = pool.getConnection();
        List<String> paramList = select.getParamList();
        List<String> nameList = select.getParamNameList();
        Object[] params = new Object[paramList.size()];
        try {
            String sql = PatternUtils.replaceSql(select.getSql());
            PreparedStatement preparedStatement = con.prepareStatement(sql);
            boolean flag = true;
            if (objects.length == 1) {
                if (objects[0] instanceof Map && flag) {
                    Map map = (Map) objects[0];
                    for (int i = 0; i < nameList.size(); i++) {
                        Object o = map.get(nameList.get(i));
                        if (o != null) {
                            params[i] = o;
                        }
                    }
                    flag = false;
                }
                if (objects[0] instanceof List && flag) {
                    List list = (List) objects[0];
                    for (int i = 0; i < nameList.size(); i++) {
                        Object o = list.get(i);
                        if (o != null) {
                            params[i] = o;
                        }
                    }
                    flag = false;
                }
                if (checkParamType(objects[0].getClass().getName(), true) && flag) {
                    Object object = objects[0];
                    Class<?> objectClass = object.getClass();
                    for (int i = 0; i < nameList.size(); i++) {
                        Field field = objectClass.getDeclaredField(nameList.get(i).trim());
                        field.setAccessible(true);
                        if (field.get(object) != null) {
                            params[i] = field.get(object);
                        }
                    }
                    flag = false;
                }
            }
            if (flag) {
                params = objects;
            }
            for (int i = 0; i < paramList.size(); i++) {
                int tag = i + 1;
                setParam(params, paramList, preparedStatement, i, tag);
            }
            ResultSet resultSet = preparedStatement.executeQuery();
            List<Object> list = list = new ArrayList<>();
            while (resultSet.next()) {
                int count = resultSet.getMetaData().getColumnCount();
                Class<?> returnType = method.getReturnType();
                Class<?> in = Class.forName(select.getNameSpace());
                Object newInstance = in.getDeclaredConstructor().newInstance();
                Field[] fields = in.getDeclaredFields();
                for (Field field : fields) {
                    if (field.getAnnotation(MyColumn.class) != null) {
                        field.setAccessible(true);
                        getFieldValue(resultSet, field.getAnnotation(MyColumn.class).value(), field, newInstance);
                    }
                }
                list.add(newInstance);
                //for (int i = 1; i <= count; i++) {
                //    System.out.println(resultSet.getObject(i));
                //}
            }
            if (list.size() == 1) {
                invoke = list.get(0);
            } else {
                invoke = list;
            }
        } finally {
            pool.close(con);
        }
        return invoke;
    }

    private void getFieldValue(ResultSet resultSet, String sqlDateType, Field type, Object newInstance) throws SQLException, IllegalAccessException {
        type.setAccessible(true);
        switch (type.getType().getName()) {
            case INTEGER:
                int resultSetInt = resultSet.getInt(sqlDateType);
                type.set(newInstance, Integer.valueOf(resultSetInt == 0 ? 0 : resultSetInt));
                break;
            case STRING:
                String resultSetString = resultSet.getString(sqlDateType);
                if (StringUntils.isNotEmpty(resultSetString)) {
                    type.set(newInstance, resultSetString);
                }
                break;
            case DATE:
                Date date = resultSet.getDate(sqlDateType);
                if (date != null) {
                    type.set(newInstance, new java.util.Date(date.getTime()));
                }
                break;
            case LOCAL_DATE_TIME:
                Date localDate = resultSet.getDate(sqlDateType);
                if (localDate != null) {
                    type.set(newInstance, LocalDateTime.ofEpochSecond(localDate.getTime(), 0, ZoneOffset.ofHours(8)));
                }
                break;
            case SHORT:
                short resultSetShort = resultSet.getShort(sqlDateType);
                if (resultSetShort != 0) {
                    type.set(newInstance, Short.valueOf(resultSetShort));
                }
                break;
            case LONG:
                long resultSetLong = resultSet.getLong(sqlDateType);
                if (resultSetLong != 0) {
                    type.set(newInstance, Long.valueOf(resultSetLong));
                }
                break;
            case FLOAT:
                float resultSetFloat = resultSet.getFloat(sqlDateType);
                if (resultSetFloat != 0) {
                    type.set(newInstance, Float.valueOf(resultSetFloat));
                }
                break;
            case DOUBLE:
                double resultSetDouble = resultSet.getDouble(sqlDateType);
                if (resultSetDouble != 0) {
                    type.set(newInstance, Double.valueOf(resultSetDouble));
                }
                break;
            case BYTE:
                byte resultSetByte = resultSet.getByte(sqlDateType);
                if (resultSetByte != 0) {
                    type.set(newInstance, Byte.valueOf(resultSetByte));
                }
                break;
            case BOOLEAN:
                boolean resultSetBoolean = resultSet.getBoolean(sqlDateType);
                type.set(newInstance, Boolean.valueOf(resultSetBoolean));
                break;
        }
    }

    private boolean checkParamType(String name, Boolean access) {
        switch (name) {
            case INTEGER:
                access = false;
                break;
            case STRING:
                access = false;
                break;
            case DATE:
                access = false;
                break;
            case SHORT:
                access = false;
                break;
            case LONG:
                access = false;
                break;
            case FLOAT:
                access = false;
                break;
            case LOCAL_DATE_TIME:
                access = false;
                break;
            case DOUBLE:
                access = false;
                break;
            case BYTE:
                access = false;
                break;
            case BOOLEAN:
                access = false;
                break;
        }
        return access;
    }

    private void setParam(Object object, String paramType, PreparedStatement preparedStatement, int tag) throws SQLException {
        switch (paramType) {
            case INTEGER:
                preparedStatement.setInt(tag, object == null ? 0 : (Integer) object);
                break;
            case STRING:
                preparedStatement.setString(tag, object == null ? "" : (String) object);
                break;
            case DATE:
                preparedStatement.setDate(tag, object == null ? new Date(System.currentTimeMillis()) : new Date(((java.util.Date) object).getTime()));
                break;
            case LOCAL_DATE_TIME:
                preparedStatement.setDate(tag, object == null ? new Date(System.currentTimeMillis()) : new Date(((LocalDateTime) object).toEpochSecond(ZoneOffset.ofHours(8))));
                break;
            case SHORT:
                preparedStatement.setShort(tag, object == null ? Short.valueOf("0") : (Short) object);
                break;
            case LONG:
                preparedStatement.setLong(tag, object == null ? Long.valueOf(0) : (Long) object);
                break;
            case FLOAT:
                preparedStatement.setFloat(tag, object == null ? Float.valueOf(0) : (Float) object);
                break;
            case DOUBLE:
                preparedStatement.setDouble(tag, object == null ? Double.valueOf(0) : (Double) object);
                break;
            case BYTE:
                preparedStatement.setByte(tag, object == null ? Byte.valueOf("0") : (Byte) object);
                break;
            case BOOLEAN:
                preparedStatement.setBoolean(tag, object == null ? false : (Boolean) object);
                break;
        }
    }

    private void setParam(Object[] objects, List<String> paramList, PreparedStatement preparedStatement, int i, int tag) throws SQLException {
        switch (paramList.get(i)) {
            case INTEGER:
                preparedStatement.setInt(tag, objects[i] == null ? 0 : (Integer) objects[i]);
                break;
            case STRING:
                preparedStatement.setString(tag, objects[i] == null ? "" : (String) objects[i]);
                break;
            case DATE:
                preparedStatement.setDate(tag, objects[i] == null ? new Date(System.currentTimeMillis()) : new Date(((java.util.Date) objects[i]).getTime()));
                break;
            case LOCAL_DATE_TIME:
                preparedStatement.setDate(tag, objects[i] == null ? new Date(System.currentTimeMillis()) : new Date(((LocalDateTime) objects[i]).toEpochSecond(ZoneOffset.ofHours(8))));
                break;
            case SHORT:
                preparedStatement.setShort(tag, objects[i] == null ? Short.valueOf("0") : (Short) objects[i]);
                break;
            case LONG:
                preparedStatement.setLong(tag, objects[i] == null ? Long.valueOf(0) : (Long) objects[i]);
                break;
            case FLOAT:
                preparedStatement.setFloat(tag, objects[i] == null ? Float.valueOf(0) : (Float) objects[i]);
                break;
            case DOUBLE:
                preparedStatement.setDouble(tag, objects[i] == null ? Double.valueOf(0) : (Double) objects[i]);
                break;
            case BYTE:
                preparedStatement.setByte(tag, objects[i] == null ? Byte.valueOf("0") : (Byte) objects[i]);
                break;
            case BOOLEAN:
                preparedStatement.setBoolean(tag, objects[i] == null ? false : (Boolean) objects[i]);
                break;
        }
    }

    private Object parseReinforce(Method method, Object[] objects, Object invoke) throws ClassNotFoundException, NoSuchMethodException, InstantiationException, IllegalAccessException, java.lang.reflect.InvocationTargetException {
        Object obj;
        Class<?> c;
        Method proxyMethod;
        Object newInstance;
        String reinforceKey = this.clazz.getName() + "&" + method.getName();
        obj = (MyLocalMethodMapping) mapper.get(reinforceKey);
        if (obj instanceof MyLocalMethodMapping) {
            MyLocalMethodMapping mapping = (MyLocalMethodMapping) obj;
            if (mapping != null) {
                mapping = (MyLocalMethodMapping) mapper.get(reinforceKey);
                c = Class.forName(mapping.getClassName());
                if (StringUntils.isNotEmpty(mapping.getMethodName())) {
                    proxyMethod = c.getDeclaredMethod(mapping.getMethodName(), getClasses(objects));
                } else {
                    proxyMethod = c.getDeclaredMethod(method.getName(), getClasses(objects));
                }
                proxyMethod.setAccessible(true);
                newInstance = c.getDeclaredConstructor().newInstance();
                invoke = proxyMethod.invoke(newInstance, objects);
            }
        }
        return invoke;
    }

    private Object parseLocal(Object invoke, MyLocalMethodMapping obj) throws ClassNotFoundException, InstantiationException, IllegalAccessException, java.lang.reflect.InvocationTargetException, NoSuchMethodException {
        Class<?> c;
        Object newInstance;
        Method proxyMethod;
        MyLocalMethodMapping mapping = obj;
        if (mapping != null) {
            c = Class.forName(mapping.getClassName());
            newInstance = c.getDeclaredConstructor().newInstance();
            proxyMethod = c.getDeclaredMethod(mapping.getMethodName(), mapping.getMethodParamClass());
            proxyMethod.setAccessible(true);
            invoke = proxyMethod.invoke(newInstance, (Object[]) mapping.getMethodParamValues());
        }
        return invoke;
    }

    private Class<?>[] getClasses(Object[] objects) {
        Class<?>[] classes = new Class[objects.length];
        for (int i = 0; i < classes.length; i++) {
            classes[i] = objects[i].getClass();
        }
        return classes;
    }

}
