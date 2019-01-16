package com.github.memorylorry.type;

import java.util.Iterator;

public class RestrictList<E> extends CommonList<E> implements RestrictOperate {
    public RestrictList() {
    }

    public String buildSQL() {
        return this.buildSQLDivdByValue(",");
    }

    public String buildSQLDivdByValue(String value) {
        value = value.toUpperCase();
        String res = "";

        RestrictOperate op;
        for(Iterator<E> var3 = this.iterator(); var3.hasNext(); res = res + value + op.buildSQL()) {
            E restric = var3.next();
            op = (RestrictOperate)restric;
        }

        if (res.length() > 0) {
            int len = value.length();
            res = res.substring(len, res.length());
        }

        return res;
    }
}
