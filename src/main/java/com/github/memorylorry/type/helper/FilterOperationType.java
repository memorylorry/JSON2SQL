package com.github.memorylorry.type.helper;

import java.util.ArrayList;

/**
 * 提供一个根据Filter的operation字段判断option类型的类；
 * 此类可以结合FilterOptionHelper.class去取出option的值
 */
public enum FilterOperationType {
    IN("in", ArrayList.class),
    NOT_IN("not in",ArrayList.class),
    GREATER(">",String.class),
    LESS("<",String.class),
    GREATER_EQUAL(">=",String.class),
    LESS_EQUAL("<=",String.class),
    EQUAL("=",String.class),
    NOT_EQUAL("!=",String.class);

    private String operation;
    private Class type;
    FilterOperationType(String operation,Class type) {
        this.operation = operation;
        this.type = type;
    }

    public static Class getTypeByOperation(String operation) {
        for (FilterOperationType fot : FilterOperationType.values()) {
            if (fot.getOperation().equals(operation)) {
                return fot.type;
            }
        }
        return null;
    }

    public String getOperation() {
        return operation;
    }
}
