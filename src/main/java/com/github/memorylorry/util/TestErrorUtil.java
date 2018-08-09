package com.github.memorylorry.util;

import com.github.memorylorry.config.ErrorMessage;
import com.github.memorylorry.type.exception.SliceFormatNotSupportedException;

public class TestErrorUtil {
    public static void testNotNull(String value) throws SliceFormatNotSupportedException{
        if(value==null){
            throw new SliceFormatNotSupportedException(ErrorMessage.VARIABLE_CANNOT_BE_NULL);
        }
    }
    public static void testNotNull(Object value) throws SliceFormatNotSupportedException{
        if(value==null){
            throw new SliceFormatNotSupportedException(ErrorMessage.VARIABLE_CANNOT_BE_NULL);
        }
    }

    public static void testNotInt(String value) throws SliceFormatNotSupportedException{
        if(value.matches("[0-9]*")){
            throw new SliceFormatNotSupportedException(ErrorMessage.VARIABLE_MUST_BE_STRING);
        }
    }

    public static void testNot0Len(String value) throws SliceFormatNotSupportedException{
        if(value.length()==0){
            throw new SliceFormatNotSupportedException(ErrorMessage.VARIABLE_CANNOT_BE_0_LENGTH);
        }
    }
}
