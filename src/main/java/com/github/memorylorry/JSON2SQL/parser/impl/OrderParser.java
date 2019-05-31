package com.github.memorylorry.JSON2SQL.parser.impl;

import com.alibaba.fastjson.JSONObject;
import com.github.memorylorry.JSON2SQL.parser.JSONParser;
import org.apache.commons.lang.StringUtils;

public class OrderParser extends JSONParser {
    @Override
    public String parse(JSONObject json) {
        String name = json.getString("name");
        String expression = json.getString("expression");
        String operation = json.getString("operation");

        // combine column and operation
        String columnName = StringUtils.isEmpty(expression) ? name : expression;
        String res = columnName + " " + operation;
        return res;
    }
}
