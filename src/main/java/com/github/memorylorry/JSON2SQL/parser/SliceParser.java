package com.github.memorylorry.JSON2SQL.parser;

import com.github.memorylorry.JSON2SQL.ParserConf;

public abstract class SliceParser extends JSONParser {

    protected ParserConf parserConf;

    public SliceParser conf(ParserConf parserConf){
        this.parserConf = parserConf;
        return this;
    }

}
