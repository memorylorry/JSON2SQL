package com.github.memorylorry.type.common;

import com.github.memorylorry.type.Operation;

import java.util.ArrayList;
import java.util.List;

public class CommonList<T> extends Operation {

    protected List<T> columns = new ArrayList();

    public void addColumn(T column){
        columns.add(column);
    }

    /**
     * Doesn't change data itself, return a new result
     * @param commonList
     * @return
     */
    public CommonList<T> addColumnList(CommonList<T> commonList){
        CommonList<T> res = copy();

        List<T> list = commonList.getColumns();
        for(T o:list){
            res.addColumn(o);
        }
        return res;
    }

    /**
     * copy one new itself
     * @return
     */
    public CommonList<T> copy(){
        CommonList<T> res = new CommonList<>();
        for(T column:columns){
            res.addColumn(column);
        }
        return res;
    }

    /**
     * @return columns with {,} delimeter
     */
    @Override
    public String toSQL() {
        return toSQLDivdByValue(",");
    }

    /**
     * @return columns with {,} delimeter
     */
    @Override
    public String toSQLWithAS() {
        return toSQLWithASDivdByValue(",");
    }

    /**
     * @return columns with {value} delimeter
     */
    public String toSQLDivdByValue(String value) {
        value = value.toUpperCase();

        String res = "";
        for(T column:columns){
            Operation op = (Operation) column;
            res = res + value + op.toSQL();
        }

        if(res.length()>0) {
            int len = value.length();
            res = res.substring(len, res.length());
        }

        return res;
    }

    /**
     * @return columns with {value} delimeter
     */
    public String toSQLWithASDivdByValue(String value) {
        value = value.toUpperCase();

        String res = "";
        for(T column:columns){
            Operation op = (Operation) column;
            res = res + value + op.toSQLWithAS();
        }

        if(res.length()>0) {
            int len = value.length();
            res = res.substring(len, res.length());
        }

        return res;
    }

    public void setColumns(List<T> columns) {
        this.columns = columns;
    }

    public List<T> getColumns() {
        return columns;
    }

}
