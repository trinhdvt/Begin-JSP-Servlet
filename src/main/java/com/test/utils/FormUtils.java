package com.test.utils;

import org.apache.commons.beanutils.BeanUtils;

import javax.servlet.http.HttpServletRequest;
import java.lang.reflect.InvocationTargetException;

public class FormUtils {
    @SuppressWarnings("unchecked")
    public static <T> T toModel(Class<T> tClass, HttpServletRequest request) {
        T object = null;
        try {
            object = (T) tClass.getDeclaredConstructors()[0].newInstance();
            BeanUtils.populate(object, request.getParameterMap());
        } catch (InstantiationException | IllegalAccessException | InvocationTargetException e) {
            e.printStackTrace();
        }
        return object;
    }
}
