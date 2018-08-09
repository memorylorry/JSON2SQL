package com.github.memorylorry.converter;

import com.github.memorylorry.type.Slice;
import com.github.memorylorry.type.exception.SliceFormatNotSupportedException;
import com.alibaba.fastjson.JSONObject;

public interface JSON2Slice {
    Slice format(String json) throws SliceFormatNotSupportedException;

    Slice format(JSONObject json) throws SliceFormatNotSupportedException;
}
