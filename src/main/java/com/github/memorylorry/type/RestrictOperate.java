package com.github.memorylorry.type;

public interface RestrictOperate {
    /**
     * 返回filter|order条件的SQL
     * @return
     */
    String buildSQL();
}
