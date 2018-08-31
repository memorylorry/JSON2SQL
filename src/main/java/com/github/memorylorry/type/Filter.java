package com.github.memorylorry.type;

import com.github.memorylorry.type.helper.FilterOptionHelper;

public class Filter implements RestrictOperate {

    private String name;//不允许有空格
    private String verbose;
    private String expression;//如果expression为空串，则用name
    private String operation;
    private String option;
    /**
     * type 0-dimension(where) 1-metric(having)
     */
    private int type;

    public Filter(){}

    public Filter(String name,String verbose,String expression,String operation,String option,int type){
        this.name = name;
        this.verbose = verbose;
        this.expression = expression;
        this.operation = operation;
        this.option = option;
        this.type = type;
    }

    @Override
    public String buildSQL() {
        return FilterOptionHelper.createFilterOptionHelper(this).getOptionString();
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

    public String getOperation() {
        return operation;
    }

    public void setOperation(String operation) {
        this.operation = operation;
    }

    public String getOption() {
        return option;
    }

    public void setOption(String option) {
        this.option = option;
    }

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }
}
