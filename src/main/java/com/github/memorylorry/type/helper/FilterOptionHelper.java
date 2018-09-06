package com.github.memorylorry.type.helper;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.github.memorylorry.type.Filter;

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
            JSONArray optionsArr = JSON.parseArray(filter.getOption());
            for(int i=0;i<optionsArr.size();i++){
                String o = optionsArr.getString(i);
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
