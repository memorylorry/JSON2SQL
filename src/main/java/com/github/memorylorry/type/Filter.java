package com.github.memorylorry.type;

import com.github.memorylorry.type.helper.FilterOptionHelper;

public class Filter implements RestrictOperate {
    private String name;
    private String verbose;
    private String expression;
    private String operation;
    private String option;
    private String optionText;
    private int type;

    public Filter() {
    }

    public Filter(String name, String verbose, String expression, String operation, String option, String optionText, int type) {
        this.name = name;
        this.verbose = verbose;
        this.expression = expression;
        this.operation = operation;
        this.option = option;
        this.optionText = optionText;
        this.type = type;
    }

    public String buildSQL() {
        return FilterOptionHelper.createFilterOptionHelper(this).getOptionString();
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

    public String getOperation() {
        return this.operation;
    }

    public void setOperation(String operation) {
        this.operation = operation;
    }

    public String getOption() {
        return this.option;
    }

    public void setOption(String option) {
        this.option = option;
    }

    public int getType() {
        return this.type;
    }

    public void setType(int type) {
        this.type = type;
    }

    public String getOptionText() {
        return optionText;
    }

    public void setOptionText(String optionText) {
        this.optionText = optionText;
    }
}
