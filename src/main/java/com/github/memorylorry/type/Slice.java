package com.github.memorylorry.type;

public interface Slice {
    String buildBasicSQL() throws IllegalAccessException, InstantiationException;
    String buildCountSQL();
}
