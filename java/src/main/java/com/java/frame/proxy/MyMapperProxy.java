package com.java.frame.proxy;

import com.java.frame.auto.MyColumn;
import com.java.frame.exception.ComponentConstance;
import com.java.frame.handler.MyLocalMethodMapping;
import com.java.frame.handler.MySelectMapping;
import com.java.frame.jdbc.DataSourcePool;
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
        // 1.String MySelect 注解
        if (obj instanceof MySelectMapping) {
            invoke = invokeSelect(method, objects, (MySelectMapping) obj);
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

    private Object invokeSelect(Method method, Object[] objects, MySelectMapping obj) throws SQLException, NoSuchFieldException, IllegalAccessException, ClassNotFoundException, InstantiationException, java.lang.reflect.InvocationTargetException, NoSuchMethodException {
        Object invoke;
        MySelectMapping select = obj;
        Connection con = pool.getConnection();
        List<String> paramList = select.getParamList();
        List<String> nameList = select.getParamNameList();
        Object[] params = new Object[paramList.size()];
        try {
            String sql = select.getSql().replaceAll(ComponentConstance.SQL_PATTERN, "?");
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
                        Field field = objectClass.getDeclaredField(nameList.get(i));
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
                type.set(newInstance, Integer.valueOf(resultSetInt));
                break;
            case STRING:
                String resultSetString = resultSet.getString(sqlDateType);
                type.set(newInstance, resultSetString);
                break;
            case DATE:
                Date date = resultSet.getDate(sqlDateType);
                type.set(newInstance, new java.util.Date(date.getTime()));
                break;
            case LOCAL_DATE_TIME:
                Date localDate = resultSet.getDate(sqlDateType);
                type.set(newInstance, LocalDateTime.ofEpochSecond(localDate.getTime(), 0, ZoneOffset.ofHours(8)));
                break;
            case SHORT:
                short resultSetShort = resultSet.getShort(sqlDateType);
                type.set(newInstance, Short.valueOf(resultSetShort));
                break;
            case LONG:
                long resultSetLong = resultSet.getLong(sqlDateType);
                type.set(newInstance, Long.valueOf(resultSetLong));
                break;
            case FLOAT:
                float resultSetFloat = resultSet.getFloat(sqlDateType);
                type.set(newInstance, Float.valueOf(resultSetFloat));
                break;
            case DOUBLE:
                double resultSetDouble = resultSet.getDouble(sqlDateType);
                type.set(newInstance, Double.valueOf(resultSetDouble));
                break;
            case BYTE:
                byte resultSetByte = resultSet.getByte(sqlDateType);
                type.set(newInstance, Byte.valueOf(resultSetByte));
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

    private void setParam(Object[] objects, List<String> paramList, PreparedStatement preparedStatement, int i, int tag) throws SQLException {
        switch (paramList.get(i)) {
            case INTEGER:
                preparedStatement.setInt(tag, (Integer) objects[i]);
                break;
            case STRING:
                preparedStatement.setString(tag, (String) objects[i]);
                break;
            case DATE:
                preparedStatement.setDate(tag, new Date(((java.util.Date) objects[i]).getTime()));
                break;
            case LOCAL_DATE_TIME:
                preparedStatement.setDate(tag, new Date(((LocalDateTime) objects[i]).toEpochSecond(ZoneOffset.ofHours(8))));
                break;
            case SHORT:
                preparedStatement.setShort(tag, (Short) objects[i]);
                break;
            case LONG:
                preparedStatement.setLong(tag, (Long) objects[i]);
                break;
            case FLOAT:
                preparedStatement.setFloat(tag, (Float) objects[i]);
                break;
            case DOUBLE:
                preparedStatement.setDouble(tag, (Double) objects[i]);
                break;
            case BYTE:
                preparedStatement.setByte(tag, (Byte) objects[i]);
                break;
            case BOOLEAN:
                preparedStatement.setBoolean(tag, (Boolean) objects[i]);
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
