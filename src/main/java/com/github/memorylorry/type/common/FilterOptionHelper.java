package com.github.memorylorry.type.common;

import com.github.memorylorry.config.FilterOperationType;

import java.util.ArrayList;

public class FilterOptionHelper {

    private Filter filter;

    private FilterOptionHelper(){}

    public static FilterOptionHelper createFilterOptionHelper(Filter filter){
        FilterOptionHelper filterOptionHelper = new FilterOptionHelper();
        filterOptionHelper.setFilter(filter);
        return filterOptionHelper;
    }

    public String getOptionString(){
        Class type = FilterOperationType.getTypeByOperation(filter.getOperation());
        String option = "";
        String sql;
        String column = "".equals(filter.getExpression())?filter.getName():filter.getExpression();
        String op = filter.getOperation();
        if(type.equals(ArrayList.class)){
            String[] options = filter.getOption().split(",");
            for(String o:options){
                option += ",'"+o+"'";
            }
            if(option.length()>0)
                option = "("+option.substring(1,option.length())+")";
            sql = column+" "+op+" "+option;
        }else{
            option = "'"+filter.getOption()+"'";
            sql = column+op+option;
        }
        return sql;
    }

    public void setFilter(Filter filter) {
        this.filter = filter;
    }
}
