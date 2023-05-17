package io.github.oneincase.utils;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

/**
 * 反射工具类
 */
public class ReflectUtil {

    /**
     * 全限定名获取class
     */
    public static Class<?> getClassForName(String name) {
        try {
            return Class.forName(name);
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * 获取方法
     */
    public static Method getMethod(Class<?> classOne,String methodName) {
        try {
            return classOne.getMethod(methodName,null);
        } catch (NoSuchMethodException e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * 获取实列（使用无参构造）
     */
    public static Object newInstance(Class<?> classOne){
        try {
            return classOne.newInstance();
        } catch (InstantiationException | IllegalAccessException e) {
            throw new RuntimeException(e);
        }
    }


    /**
     * 反射执行方法
     */
    public static Object invoke(Object instance, Method method, Object ...params) {
        try {
            return method.invoke(instance,params);
        } catch (IllegalAccessException | InvocationTargetException e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * 反射执行方法
     */
    public static Object invoke(String name,String methodName)  {
        Class<?> classOne = getClassForName(name);
        Method method = getMethod(classOne, methodName);
        return invoke(newInstance(classOne),method,null);
    }

}
