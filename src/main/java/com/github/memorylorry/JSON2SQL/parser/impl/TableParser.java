package com.github.memorylorry.JSON2SQL.parser.impl;

import com.alibaba.fastjson.JSONObject;
import com.github.memorylorry.JSON2SQL.parser.JSONParser;
import org.apache.commons.lang.StringUtils;

public class TableParser extends JSONParser {
    @Override
    public String parse(JSONObject json) {
        String database = json.getString("database");
        String table = json.getString("table");

        //若table中存在select关键字
        if(table.toUpperCase().indexOf("SELECT") >= 0){
            return "(" + table + ")";
        }
        return StringUtils.isEmpty(database)?table:database + '.' + table;
    }
}
