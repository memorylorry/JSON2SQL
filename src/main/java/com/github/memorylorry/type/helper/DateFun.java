package com.github.memorylorry.type.helper;

public class DateFun {
    private String op;
    private String val;

    public DateFun(String op,String val){
        this.op = op;
        this.val = val;
    }

    public String getOp() {
        return op;
    }

    public void setOp(String op) {
        this.op = op;
    }

    public String getVal() {
        return val;
    }

    public void setVal(String val) {
        this.val = val;
    }
}
