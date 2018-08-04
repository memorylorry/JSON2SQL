package cn.dectfix.type.common;

import cn.dectfix.type.Operation;

public class Table extends Operation {

    private String name;//存储表名 或 sql

    public Table(){}

    public Table(String name){
        this.name = name;
    }

    @Override
    public String toSQL() {
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
