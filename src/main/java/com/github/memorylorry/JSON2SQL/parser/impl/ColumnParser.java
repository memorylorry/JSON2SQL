package com.github.memorylorry.JSON2SQL.parser.impl;

import com.alibaba.fastjson.JSONObject;
import com.github.memorylorry.JSON2SQL.parser.JSONParser;
import org.apache.commons.lang.StringUtils;

import java.util.Map;

public class ColumnParser extends JSONParser {

    private boolean isWithAS = false;
    private String suffix;

    @Override
    public String parse(JSONObject json) {
        String name = json.getString("name");
        String expression = json.getString("expression");

        String columnName = StringUtils.isEmpty(expression) ? name : expression;

        if (isWithAS) {
            columnName += " AS " + name;
            if(!StringUtils.isEmpty(suffix))
                columnName += suffix;
        }
        return columnName;
    }

    @Override
    public JSONParser bind(Map<String, Object> data) {
        Object param1 = data.get("isWithAs");
        if(param1!=null){
            isWithAS = (boolean) param1;
        }

        Object param2 = data.get("isWithAs");
        if(param2!=null){
            suffix = (String) param2;
        }
        return this;
    }
}
