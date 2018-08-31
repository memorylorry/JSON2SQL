package com.github.memorylorry.type;

public interface ColumnOperate {
    /**
     * 返回字段的SQL
     * @param isWithAS 控制是否带有AS
     * @return
     */
    String buildSQL(boolean isWithAS);

    /**
     * 返回字段的SQL
     * @return
     */
    String buildSQL();
}
