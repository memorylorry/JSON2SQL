package com.github.memorylorry.JSON2SQL.exception;

import javax.activation.UnsupportedDataTypeException;

public class JSON2SQLParseException extends UnsupportedDataTypeException {
    public JSON2SQLParseException(String message) {
        super(message);
    }
}
