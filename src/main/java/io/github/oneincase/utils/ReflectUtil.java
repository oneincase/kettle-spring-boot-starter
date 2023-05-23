package io.github.oneincase.utils;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

/**
 * Reflect Util
 */
public class ReflectUtil {

    public static Class<?> getClassForName(String name) {
        try {
            return Class.forName(name);
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
    }

    public static Method getMethod(Class<?> classOne, String methodName) {
        try {
            return classOne.getMethod(methodName, null);
        } catch (NoSuchMethodException e) {
            throw new RuntimeException(e);
        }
    }

    public static Object newInstance(Class<?> classOne) {
        try {
            return classOne.newInstance();
        } catch (InstantiationException | IllegalAccessException e) {
            throw new RuntimeException(e);
        }
    }


    public static Object invoke(Object instance, Method method, Object... params) {
        try {
            return method.invoke(instance, params);
        } catch (IllegalAccessException | InvocationTargetException e) {
            throw new RuntimeException(e);
        }
    }

    public static Object invoke(String name, String methodName) {
        Class<?> classOne = getClassForName(name);
        Method method = getMethod(classOne, methodName);
        return invoke(newInstance(classOne), method, null);
    }

}
