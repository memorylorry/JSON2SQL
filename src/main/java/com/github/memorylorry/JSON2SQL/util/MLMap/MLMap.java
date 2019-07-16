package com.github.memorylorry.JSON2SQL.util.MLMap;

import java.util.Map;

public interface MLMap<K,V> extends Map<K,V> {
    <T> T get(K key, T defaultVal);
}
