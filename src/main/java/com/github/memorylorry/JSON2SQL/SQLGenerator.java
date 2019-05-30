package com.github.memorylorry.JSON2SQL;

import com.alibaba.fastjson.JSONObject;

public class SQLGenerator {

    private ParserConf parserConf;

    private SQLGenerator(){}

    /**
     * 构造sql构造器
     * @return
     */
    public static SQLGenerator build(){
        return build(ParserConf.buildSliceParseConf());
    }

    public static SQLGenerator build(ParserConf parserConf){
        SQLGenerator sqlGenerator = new SQLGenerator();
        sqlGenerator.parserConf = parserConf;
        return sqlGenerator;
    }

    /**
     * sql产生器
     * @param json
     * @return
     */
    public String parse(String json){
        return parse(JSONObject.parseObject(json));
    }
    public String parse(JSONObject json){
        return null;
    }

}
