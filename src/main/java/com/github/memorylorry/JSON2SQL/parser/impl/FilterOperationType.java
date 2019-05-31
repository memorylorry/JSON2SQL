package com.github.memorylorry.JSON2SQL.parser.impl;

import com.github.memorylorry.JSON2SQL.func.LgDateFunction;

import java.util.ArrayList;

public enum FilterOperationType {
    IN("in", ArrayList.class),
    NOT_IN("not in", ArrayList.class),
    GREATER(">", String.class),
    LESS("<", String.class),
    GREATER_EQUAL(">=", String.class),
    LESS_EQUAL("<=", String.class),
    EQUAL("=", String.class),
    NOT_EQUAL("!=", String.class),
    LIKE("like", String.class),
    NOT_LIKE("not like", String.class),
    LG_DATE_FUN("lg_date_fun", LgDateFunction.class);

    private String operation;
    private Class type;

    private FilterOperationType(String operation, Class type) {
        this.operation = operation;
        this.type = type;
    }

    public static Class getTypeByOperation(String operation) {
        FilterOperationType[] var1 = values();
        int var2 = var1.length;

        for(int var3 = 0; var3 < var2; ++var3) {
            FilterOperationType fot = var1[var3];
            if (fot.getOperation().equals(operation)) {
                return fot.type;
            }
        }

        return String.class;
    }

    public String getOperation() {
        return this.operation;
    }
}