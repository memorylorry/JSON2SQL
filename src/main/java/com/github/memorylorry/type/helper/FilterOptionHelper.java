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

    /**
     * 根据字符串，处理是否需要携带引号
     * @param str  如果以fun:开头-不要引号；否则要引号
     * @param startWith 开始字符
     * @return
     */
    private String formatByFun(String str,String startWith){
        if(str.startsWith(startWith)&&str.length()>startWith.length()){
            return str.substring(startWith.length(),str.length());
        }else{
            return "'"+str+"'";
        }
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
                String o = formatByFun(optionsArr.getString(i),"fun:");
                option += ","+o;
            }
            if(option.length()>0)
                option = "("+option.substring(1,option.length())+")";
            sql = column+" "+op+" "+option;
        }else{
            option = formatByFun(filter.getOption(),"fun:");
            sql = column+op+option;
        }
        return sql;
    }

    public void setFilter(Filter filter) {
        this.filter = filter;
    }
}
