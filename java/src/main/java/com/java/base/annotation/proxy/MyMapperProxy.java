package com.java.base.annotation.proxy;

import com.java.base.annotation.exception.ComponentConstance;
import com.java.base.annotation.exception.JavaType;
import com.java.base.annotation.ioc.MyLocalMethodMapping;
import com.java.base.annotation.ioc.MySelectMapping;
import com.java.base.annotation.jdbc.DataSourcePool;
import com.java.base.annotation.util.StringUntils;
import org.springframework.cglib.proxy.MethodInterceptor;
import org.springframework.cglib.proxy.MethodProxy;

import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.sql.*;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Stream;

import static com.java.base.annotation.exception.JavaType.*;

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
        Class<?> clazz = null;
        Method proxyMethod = null;
        Object newInstance = null;
        String methodKey = this.clazz.getName() + "#" + method.getName();
        Object obj = mapper.get(methodKey);
        // 1.String MySelect 注解
        if (obj instanceof MySelectMapping) {
            MySelectMapping select = (MySelectMapping) obj;
            Connection con = pool.getConnection();
            List<String> paramList = select.getParamList();
            List<String> nameList = select.getParamNameList();
            Object[] params = new Object[paramList.size()];
            try {
                String sql = select.getSql().replaceAll(ComponentConstance.SQL_PATTERN, "?");
                PreparedStatement preparedStatement = con.prepareStatement(sql);
                if (objects.length == 1) {
                    boolean flag = true;
                    if (objects[0] instanceof Map && flag) {
                        Map map = (Map) objects[0];
                        for (int i = 0; i < nameList.size(); i++) {
                            Object o = map.get(nameList.get(i));
                            if (o != null) {
                                params[i] = o;
                            } else {
                                params[i] = new Object();
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
                            } else {
                                params[i] = new Object();
                            }
                        }
                        flag = false;
                    }
                    if (!objects[0].getClass().isPrimitive() && flag) {
                        Object object = objects[0];
                        Class<?> objectClass = object.getClass();
                        for (int i = 0; i < nameList.size(); i++) {
                            Field field = objectClass.getDeclaredField(nameList.get(i));
                            field.setAccessible(true);
                            if (field.get(object) != null) {
                                params[i] = field.get(object);
                            } else {
                            }
                        }
                        flag = false;
                    }
                    if (params.length == 0) {
                        params = objects;
                    }
                }

                for (int i = 0; i < paramList.size(); i++) {
                    int tag = i + 1;
                    setParam(params, paramList, preparedStatement, i, tag);
                }
                ResultSet resultSet = preparedStatement.executeQuery();
                while (resultSet.next()) {
                    int count = resultSet.getMetaData().getColumnCount();
                    for (int i = 1; i <= count; i++) {
                        System.out.println(resultSet.getObject(i));
                    }
                }
            } finally {
                pool.close(con);
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
