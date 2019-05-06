package com.github.memorylorry.type;

import java.util.Iterator;

public class ColumnList<E extends ColumnOperate> extends CommonList<E> implements ColumnOperate {
    public ColumnList() {
    }

    public String buildSQL() {
        return this.buildSQLDivdByValue(",");
    }

    public String buildSQL(boolean isWithAS) {
        return isWithAS ? this.buildSQLDivdByValueWithAS(",") : this.buildSQL();
    }
    public String buildSQL(boolean isWithAS,String suffix) {
        return isWithAS ? this.buildSQLDivdByValueWithAS(",",suffix) : this.buildSQL();
    }

    public String buildSQLDivdByValue(String value) {
        value = value.toUpperCase();
        String res = "";

        ColumnOperate column;
        for(Iterator var3 = this.iterator(); var3.hasNext(); res = res + value + column.buildSQL()) {
            column = (ColumnOperate)var3.next();
        }

        if (res.length() > 0) {
            int len = value.length();
            res = res.substring(len, res.length());
        }

        return res;
    }

    public String buildSQLDivdByValueWithAS(String value) {
        value = value.toUpperCase();
        String res = "";

        ColumnOperate column;
        for(Iterator var3 = this.iterator(); var3.hasNext(); res = res + value + column.buildSQL(true)) {
            column = (ColumnOperate)var3.next();
        }

        if (res.length() > 0) {
            int len = value.length();
            res = res.substring(len, res.length());
        }

        return res;
    }
    public String buildSQLDivdByValueWithAS(String value,String suffix) {
        value = value.toUpperCase();
        String res = "";

        ColumnOperate column;
        for(Iterator var3 = this.iterator(); var3.hasNext(); res = res + value + column.buildSQL(true,suffix)) {
            column = (ColumnOperate)var3.next();
        }

        if (res.length() > 0) {
            int len = value.length();
            res = res.substring(len, res.length());
        }

        return res;
    }
}
