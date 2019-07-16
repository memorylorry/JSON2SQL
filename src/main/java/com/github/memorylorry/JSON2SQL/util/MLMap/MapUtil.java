package com.github.memorylorry.JSON2SQL.util.MLMap;

import org.apache.commons.lang.StringUtils;

import java.util.Map;

/**
 * 这个类用于获取Map中的数据，若params或key为空、空串，则返回默认值。
 * 经过测试，这个类可以读取包括类、字符串、整数、布尔类型的数据。很好用，强烈推荐。
 */
public class MapUtil {

    public static <T> T get(Map<String,Object> params,String key,T defaultVal){
        // default case
        if (params==null || StringUtils.isEmpty(key) || params.get(key)==null) return defaultVal;
        return (defaultVal instanceof Integer)?(T)Integer.valueOf(params.get(key).toString()):
                (T) params.get(key);
    }

}
