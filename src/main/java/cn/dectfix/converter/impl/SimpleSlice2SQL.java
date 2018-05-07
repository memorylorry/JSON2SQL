package cn.dectfix.converter.impl;

import cn.dectfix.converter.Slice2SQL;
import cn.dectfix.model.Slice;

public class SimpleSlice2SQL implements Slice2SQL {
    @Override
    public String format(Slice slice) {
        return slice.toString();
    }
}
