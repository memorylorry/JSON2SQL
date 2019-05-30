package com.github.memorylorry.JSON2SQL;

public enum ParserType {

    SLICE_PARSER("com.github.memorylorry.JSON2SQL.parser.impl.SliceBasicParser"),
    COLUMN_PARSER("com.github.memorylorry.JSON2SQL.parser.impl.ColumnParser"),
    TABLE_PARSER("com.github.memorylorry.JSON2SQL.parser.impl.TableParser"),
    FILTER_PARSER("com.github.memorylorry.JSON2SQL.parser.impl.FilterParser"),
    ORDER_PARSER("com.github.memorylorry.JSON2SQL.parser.impl.OrderParser");

    private String value;

    private ParserType(String value){
        this.value = value;
    }

    public String getName() {
        return name();
    }

    public String getValue() {
        return value;
    }

}
