package com.github.memorylorry.type;

public class Column implements ColumnOperate {
    private String name;
    private String verbose;
    private String expression;

    public Column() {
    }

    public Column(String name, String verbose, String expression) {
        this.name = name;
        this.verbose = verbose;
        this.expression = expression;
    }

    public String buildSQL(boolean isWithAS) {
        if (isWithAS) {
            return "".equals(this.expression) ? this.name : this.expression + " AS " + this.name;
        } else {
            return this.buildSQL();
        }
    }

    public String buildSQL() {
        return "".equals(this.expression) ? this.name : this.expression;
    }

    public String getName() {
        return this.name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getVerbose() {
        return this.verbose;
    }

    public void setVerbose(String verbose) {
        this.verbose = verbose;
    }

    public String getExpression() {
        return this.expression;
    }

    public void setExpression(String expression) {
        this.expression = expression;
    }
}
