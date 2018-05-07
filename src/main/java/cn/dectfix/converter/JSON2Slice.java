package cn.dectfix.converter;

import cn.dectfix.model.Slice;
import com.alibaba.fastjson.JSONObject;

public interface JSON2Slice {
    Slice format(String json);

    Slice format(JSONObject json);
}
