package com.github.memorylorry.type;

public class ColumnList<E extends ColumnOperate> extends CommonList<E> implements ColumnOperate {

    /**
     * @return columns with {,} delimeter
     */
    @Override
    public String buildSQL() {
        return buildSQLDivdByValue(",");
    }

    /**
     * @return columns with {,} delimeter
     */
    @Override
    public String buildSQL(boolean isWithAS) {
        if(isWithAS){
            return buildSQLDivdByValueWithAS(",");
        }
        return buildSQL();
    }

    /**
     * @return columns with {value} delimeter
     */
    public String buildSQLDivdByValue(String value) {
        value = value.toUpperCase();

        String res = "";
        for(E column:this){
            res = res + value + column.buildSQL();
        }

        if(res.length()>0) {
            int len = value.length();
            res = res.substring(len, res.length());
        }

        return res;
    }

    /**
     * @return columns with {value} delimeter
     */
    public String buildSQLDivdByValueWithAS(String value) {
        value = value.toUpperCase();

        String res = "";
        for(E column:this){
            res = res + value + column.buildSQL(true);
        }

        if(res.length()>0) {
            int len = value.length();
            res = res.substring(len, res.length());
        }

        return res;
    }

}
