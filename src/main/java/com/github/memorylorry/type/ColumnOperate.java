package com.github.memorylorry.type;

public interface ColumnOperate {
    String buildSQL(boolean var1);
    String buildSQL(boolean var1,String suffix);

    String buildSQL();
}
