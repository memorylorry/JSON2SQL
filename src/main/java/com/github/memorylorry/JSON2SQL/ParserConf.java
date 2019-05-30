package com.github.memorylorry.JSON2SQL;

import java.util.HashMap;

public class ParserConf<K,V> extends HashMap<K,V>
{
    private ParserConf(){}

    public static ParserConf buildSliceParseConf(){
        ParserConf<String,String> res = new ParserConf<>();
        res.put(ParserType.SLICE_PARSER.getName(),ParserType.SLICE_PARSER.getValue());
        res.put(ParserType.COLUMN_PARSER.getName(),ParserType.COLUMN_PARSER.getValue());
        res.put(ParserType.TABLE_PARSER.getName(),ParserType.TABLE_PARSER.getValue());
        res.put(ParserType.FILTER_PARSER.getName(),ParserType.FILTER_PARSER.getValue());
        res.put(ParserType.ORDER_PARSER.getName(),ParserType.ORDER_PARSER.getValue());
        return res;
    }

    public static ParserConf buildSliceCountParseConf(){
        ParserConf<String,String> res = buildSliceParseConf();
        res.put(ParserType.SLICE_PARSER.getName(),"com.github.memorylorry.JSON2SQL.parser.impl.SliceCountParser");
        return res;
    }
}
