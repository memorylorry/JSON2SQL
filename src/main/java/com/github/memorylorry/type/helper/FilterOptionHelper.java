package com.github.memorylorry.type.helper;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.github.memorylorry.type.Filter;
import java.util.ArrayList;

public class FilterOptionHelper {
    private Filter filter;

    private FilterOptionHelper() {
    }

    public static FilterOptionHelper createFilterOptionHelper(Filter filter) {
        FilterOptionHelper filterOptionHelper = new FilterOptionHelper();
        filterOptionHelper.setFilter(filter);
        return filterOptionHelper;
    }

    private String formatByFun(String str, String startWith) {
        return str.startsWith(startWith) && str.length() > startWith.length() ? str.substring(startWith.length(), str.length()) : "'" + str + "'";
    }

    public String getOptionString() {
        Class type = FilterOperationType.getTypeByOperation(this.filter.getOperation());
        String option = "";
        String column = "".equals(this.filter.getExpression()) ? this.filter.getName() : this.filter.getExpression();
        String op = this.filter.getOperation();
        String sql;
        if (type.equals(ArrayList.class)) {
            JSONArray optionsArr = JSON.parseArray(this.filter.getOption());

            for(int i = 0; i < optionsArr.size(); ++i) {
                String o = this.formatByFun(optionsArr.getString(i), "fun:");
                option = option + "," + o;
            }

            if (option.length() > 0) {
                option = "(" + option.substring(1, option.length()) + ")";
            }

            sql = column + " " + op + " " + option;
        } else {
            option = this.formatByFun(this.filter.getOption(), "fun:");
            sql = column + op + option;
        }

        return sql;
    }

    public void setFilter(Filter filter) {
        this.filter = filter;
    }
}
