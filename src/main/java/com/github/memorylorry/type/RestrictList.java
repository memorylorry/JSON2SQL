package com.github.memorylorry.type;

public class RestrictList<E> extends CommonList<E> implements RestrictOperate {

    /**
     * @return restrics with {,} delimeter
     */
    @Override
    public String buildSQL() {
        return buildSQLDivdByValue(",");
    }

    /**
     * @return restrics with {value} delimeter
     */
    public String buildSQLDivdByValue(String value) {
        value = value.toUpperCase();

        String res = "";
        for(E restric:this){
            RestrictOperate op = (RestrictOperate) restric;
            res = res + value + op.buildSQL();
        }

        if(res.length()>0) {
            int len = value.length();
            res = res.substring(len, res.length());
        }

        return res;
    }

}
