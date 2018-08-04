package cn.dectfix.converter;

import cn.dectfix.type.Slice;
import cn.dectfix.type.exception.SliceFormatNotSupportedException;
import com.alibaba.fastjson.JSONObject;

public interface JSON2Slice {
    Slice format(String json) throws SliceFormatNotSupportedException;

    Slice format(JSONObject json) throws SliceFormatNotSupportedException;
}
