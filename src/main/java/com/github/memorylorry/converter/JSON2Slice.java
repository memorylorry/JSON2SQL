package com.github.memorylorry.converter;

import com.github.memorylorry.type.SimpleSlice;
import com.github.memorylorry.type.exception.SliceFormatNotSupportedException;
import com.alibaba.fastjson.JSONObject;

public interface JSON2Slice {
    SimpleSlice format(String json) throws SliceFormatNotSupportedException;

    SimpleSlice format(JSONObject json) throws SliceFormatNotSupportedException;
}
