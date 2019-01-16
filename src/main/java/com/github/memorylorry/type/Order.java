package com.github.memorylorry.type;

public class Order implements RestrictOperate {
    private String name;
    private String verbose;
    private String expression;
    private String operation;

    public Order() {
    }

    public Order(String name, String verbose, String expression, String operation) {
        this.name = name;
        this.verbose = verbose;
        this.expression = expression;
        this.operation = operation;
    }

    public String buildSQL() {
        String columnName = "".equals(this.expression) ? this.name : this.expression;
        String res = columnName + " " + this.operation;
        return res;
    }
}
