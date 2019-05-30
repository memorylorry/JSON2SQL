package com.github.memorylorry.JSON2SQL.formater;

import com.alibaba.fastjson.JSONObject;
import com.github.memorylorry.JSON2SQL.exception.SliceFormartNotSupportException;

public interface JSONFormarter {
    void format(JSONObject json) throws SliceFormartNotSupportException;
}
