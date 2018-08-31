package com.github.memorylorry.type;

public class Table {

    private String name;//存储表名 或 sql

    public Table(){}

    public Table(String name){
        this.name = name;
    }

    public String buildSQL() {
        if(name.toUpperCase().indexOf("SELECT")>=0){
            return "("+name+") t";
        }
        return name;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

}
