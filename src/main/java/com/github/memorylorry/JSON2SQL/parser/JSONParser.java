package com.github.memorylorry.JSON2SQL.parser;

import com.alibaba.fastjson.JSONObject;

import java.util.Map;

public abstract class JSONParser {
    /**
     * 用于将json转化为SQL
     * @param json
     * @return
     */
    public abstract String parse(JSONObject json);

    /**
     * 用于给parser绑定附加的属性
     * @param data
     */
    public JSONParser bind(Map<String,Object> data){
        return this;
    }

}
