package com.test.utils;

import javax.servlet.http.HttpServletRequest;

public class SessionUtil {
    private static SessionUtil instance = null;

    public static SessionUtil getInstance() {
        if (instance == null) {
            instance = new SessionUtil();
        }
        return instance;
    }

    public void putValue(HttpServletRequest req, String key, Object value) {
        req.getSession().setAttribute(key, value);
    }

    public Object getValue(HttpServletRequest req, String key) {
        return req.getSession().getAttribute(key);
    }

    public void removeValue(HttpServletRequest req, String key) {
        req.getSession().removeAttribute(key);
    }
}
