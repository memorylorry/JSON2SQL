package com.github.memorylorry.JSON2SQL.checker;

import com.github.memorylorry.JSON2SQL.exception.JSON2SQLParseException;

public interface JSONChecker {
    void check(String res) throws JSON2SQLParseException;
}
