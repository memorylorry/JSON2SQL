package com.github.memorylorry.converter.impl;

import com.github.memorylorry.converter.Slice2SQL;
import com.github.memorylorry.type.Slice;

public class SimpleSlice2SQL implements Slice2SQL {
    @Override
    public String format(Slice slice) {
        return slice.toString();
    }
}
