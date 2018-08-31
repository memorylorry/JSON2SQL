package com.github.memorylorry.type;

public class Column implements ColumnOperate {

    private String name;//不允许有空格
    private String verbose;
    private String expression;//如果expression为空串，则用name

    public Column(){}

    public Column(String name,String verbose,String expression){
        this.name = name;
        this.verbose = verbose;
        this.expression = expression;
    }

    @Override
    public String buildSQL(boolean isWithAS) {
        if(isWithAS){
            return "".equals(expression)?name:(expression + " AS " + name);
        }
        return buildSQL();
    }

    @Override
    public String buildSQL() {
        return "".equals(expression)?name:expression;
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

}
