package com.github.memorylorry.type.common;

import com.github.memorylorry.type.Operation;

public class Filter extends Operation {

    private String name;//不允许有空格
    private String verbose;
    private String expression;//如果expression为空串，则用name
    /**
     * sql is used to filter
     * 其中关键字，一律用#{value},后端去决定用name还是用expression
     */
    private String sql;
    /**
     * type 0-dimension(where) 1-metric(having)
     */
    private int type;

    public Filter(){}

    public Filter(String name,String verbose,String expression,String sql,int type){
        this.name = name;
        this.verbose = verbose;
        this.expression = expression;
        this.sql = sql;
        this.type = type;
    }

    @Override
    public String toSQL() {
        if("".equals(expression))expression=name;

        String res = "";
        String regx = "#\\{value}";
        //res = (type==0)?sql.replaceAll(regx,expression):sql.replaceAll(regx,name);
        res = sql.replaceAll(regx,expression);

        return res;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getVerbose() {
        return verbose;
    }

    public void setVerbose(String verbose) {
        this.verbose = verbose;
    }

    public String getExpression() {
        return expression;
    }

    public void setExpression(String expression) {
        this.expression = expression;
    }

    public String getSql() {
        return sql;
    }

    public void setSql(String sql) {
        this.sql = sql;
    }

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }
}
