package com.github.memorylorry.JSON2SQL.util.MLMap;

import java.util.HashMap;

public class MLHashMap<K,V> extends HashMap<K,V> implements MLMap<K,V> {
    @Override
    public <T> T get(K key, T defaultVal) {
        if (key==null || get(key)==null) return defaultVal;
        return (defaultVal instanceof Integer)?(T)Integer.valueOf(get(key).toString()):
                (T) get(key);
    }
}
