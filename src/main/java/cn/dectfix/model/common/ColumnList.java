package cn.dectfix.model.common;

import java.util.ArrayList;
import java.util.List;

public class ColumnList {

    private List<Column> columns = new ArrayList();

    public void setColumns(List<Column> columns) {
        this.columns = columns;
    }

    public void addColumn(Column column){
        columns.add(column);
    }

    @Override
    public String toString() {
        String res = "";

        for(Column column:columns){
            res = res + "," + column.toString();
        }

        if(res.length()>0)
            res = res.substring(1,res.length());

        return res;
    }

    public String toColumnString(){
        String res = "";
        for(Column column:columns){
            res = res + "," + column.getName();
        }

        if(res.length()>0)
            res = res.substring(1,res.length());

        return res;
    }
}
