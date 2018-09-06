package com.github.memorylorry.type;

public class Order implements RestrictOperate {

    private String name;//不允许有空格
    private String verbose;
    private String expression;//如果expression为空串，则用name
    private String operation;

    public Order(){}

    public Order(String name,String verbose,String expression,String operation){
        this.name = name;
        this.verbose = verbose;
        this.expression = expression;
        this.operation = operation;
    }

    @Override
    public String buildSQL() {
        String columnName = "".equals(expression)?name:expression;
        String res = columnName+" "+operation;
        return res;
    }

}
