package com.github.memorylorry.JSON2SQL.parser;

import com.alibaba.fastjson.JSONObject;

public interface JSONParser {
    String parse(JSONObject json);
}
