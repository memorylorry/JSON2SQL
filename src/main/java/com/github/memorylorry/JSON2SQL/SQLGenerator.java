package com.github.memorylorry.JSON2SQL;

import com.alibaba.fastjson.JSONObject;
import com.github.memorylorry.JSON2SQL.exception.JSON2SQLParseException;
import com.github.memorylorry.JSON2SQL.parser.SliceParser;
import com.github.memorylorry.JSON2SQL.util.BeanCreator;

public class SQLGenerator {

    public enum SLICE_TYPE{BASIC,COUNT};

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

    public static SQLGenerator build(SLICE_TYPE type){
        SQLGenerator sqlGenerator = new SQLGenerator();
        sqlGenerator.setParserConf(type);
        return sqlGenerator;
    }

    public SQLGenerator setParserConf(SLICE_TYPE type){
        if(type.equals(SLICE_TYPE.COUNT)){
            //count
            parserConf = ParserConf.buildSliceCountParseConf();
        }else{
            //basic and other
            parserConf = ParserConf.buildSliceParseConf();
        }
        return this;
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
        SliceParser sliceParser = (SliceParser) BeanCreator.create(classname);

        // bind the parserConf to slice parser
        sliceParser.conf(parserConf);

        return sliceParser.parse(json);
    }

}
