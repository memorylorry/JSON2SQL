package com.github.memorylorry.type;

public class Table {
    private String name;

    public Table() {
    }

    public Table(String name) {
        this.name = name;
    }

    public String buildSQL() {
        return this.name.toUpperCase().indexOf("SELECT") >= 0 ? "(" + this.name + ") t" : this.name;
    }

    public String getName() {
        return this.name;
    }

    public void setName(String name) {
        this.name = name;
    }
}