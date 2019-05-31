package com.github.memorylorry.JSON2SQL.parser.impl;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.github.memorylorry.JSON2SQL.exception.JSON2SQLParseException;
import com.github.memorylorry.JSON2SQL.parser.JSONParser;

import java.util.ArrayList;
import java.util.List;

public class JSONArrayWrapper extends JSONArray {

    public JSONArrayWrapper(){}

    public JSONArrayWrapper(JSONArray json){
        this.addAll(json);
    }

    public List<String> loop(JSONParser parser) throws JSON2SQLParseException {
        List<String> res = new ArrayList<>();

        for(int i=0;i<this.size();i++){
            JSONObject obj = getJSONObject(i);
            String sql = parser.parse(obj);
            res.add(sql);
        }
        return res;
    }
}
