package com.github.memorylorry.JSON2SQL.checker;

import com.github.memorylorry.JSON2SQL.exception.JSON2SQLParseException;

public interface JSONChecker {
    //TODO 去实现对JSON格式化要求的控制
    void check(String res) throws JSON2SQLParseException;
}
