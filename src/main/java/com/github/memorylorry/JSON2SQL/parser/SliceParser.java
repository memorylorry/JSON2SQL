package com.github.memorylorry.JSON2SQL.parser;

import com.github.memorylorry.JSON2SQL.ParserConf;

public abstract class SliceParser implements JSONParser {

    protected ParserConf parserConf;

    public void setParserConf(ParserConf parserConf){
        this.parserConf = parserConf;
    }

}
