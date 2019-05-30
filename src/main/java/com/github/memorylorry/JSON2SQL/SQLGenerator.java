package com.github.memorylorry.JSON2SQL;

import com.alibaba.fastjson.JSONObject;
import com.github.memorylorry.JSON2SQL.exception.JSON2SQLParseException;
import com.github.memorylorry.JSON2SQL.parser.SliceParser;

public class SQLGenerator {

    private ParserConf<String,String> parserConf;

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
    public String parse(String json) throws JSON2SQLParseException {
        return parse(JSONObject.parseObject(json));
    }
    public String parse(JSONObject json) throws JSON2SQLParseException {

        String classname = parserConf.get(ParserType.SLICE_PARSER.getName());

        // create a new slice parser
        SliceParser sliceParser = null;
        try {
            Class sliceClass = Class.forName(classname);
            sliceParser = (SliceParser) sliceClass.newInstance();
        } catch (ClassNotFoundException | InstantiationException | IllegalAccessException e) {
            throw new JSON2SQLParseException(e.getMessage());
        }

        // bing the parserConf to slice parser
        sliceParser.setParserConf(parserConf);

        return sliceParser.parse(json);
    }

}
