package com.github.memorylorry.JSON2SQL.util;

import com.github.memorylorry.JSON2SQL.exception.JSON2SQLParseException;

public class BeanCreator {
    public static Object create(String className) throws JSON2SQLParseException{
        Object obj;
        try {
            Class objClass = Class.forName(className);
            obj = objClass.newInstance();
        } catch (ClassNotFoundException | InstantiationException | IllegalAccessException e) {
            throw new JSON2SQLParseException(e.getMessage());
        }
        return obj;
    }
}
