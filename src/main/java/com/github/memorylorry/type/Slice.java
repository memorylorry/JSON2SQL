package com.github.memorylorry.type;

public interface Slice {
    String buildBasicSQL() throws IllegalAccessException, InstantiationException;
    String buildCountSQL();

    String buildBasicSQL(String suffix,boolean useOrderAndLimit) throws IllegalAccessException, InstantiationException;
    String buildCountSQL(String suffix,boolean useOrderAndLimit);
}
