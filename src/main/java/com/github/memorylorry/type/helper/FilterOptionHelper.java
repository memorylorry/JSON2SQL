package com.github.memorylorry.type.helper;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.github.memorylorry.type.Filter;
import org.apache.commons.lang.StringUtils;

import java.util.ArrayList;
import java.util.List;

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
        String option = filter.getOperation().contains("like")?
                "'%"+str+"%'": // like '%key%'
                filter.getType()==1?
                str: //若为指标
                str.startsWith(startWith) && str.length() > startWith.length() ?
                        str.substring(startWith.length(), str.length()) : "'" + str + "'";
        return option;
    }

    public String getOptionString() {
        Class type = FilterOperationType.getTypeByOperation(this.filter.getOperation());
        String option = "";
        String column = "".equals(this.filter.getExpression()) ? this.filter.getName() : this.filter.getExpression();
        String op = this.filter.getOperation();
        String sql;
        if (type.equals(ArrayList.class)) {
            //optionText
            String optionText = this.filter.getOptionText();
            if(optionText!=null && optionText.length()>0){
                optionText = filter.getType()==1?
                        optionText.replaceAll("\n",""):
                        "'" + optionText.replaceAll(",","','").replaceAll("\n","") + "'";
            }else{
                optionText = "";
            }

            //option处理位置
            JSONArray optionsArr = JSON.parseArray(this.filter.getOption());

            for(int i = 0; i < optionsArr.size(); ++i) {
                String o = this.formatByFun(optionsArr.getString(i), "fun:");
                option = option + "," + o;
            }

            if (option.length() > 0) {
                option = option.substring(1, option.length());
                if(optionText.length()>0)option = option + "," + optionText;
            }else{
                option = optionText;
            }

            sql = column + " " + op + " (" + option +")";
        }else if(type.equals(LgDateFunction.class)){
            List<DateFun> conditions = new LgDateFunction().format(this.filter.getOption());
            List<String> res = new ArrayList<>();
            for(DateFun df:conditions){
                res.add(column + " "+df.getOp()+" "+df.getVal());
            }

            return StringUtils.join(res," and ");
        }else {
            option = this.formatByFun(this.filter.getOption(), "fun:");
            sql = column + " " + op + " " + option;
        }

        return sql;
    }

    public void setFilter(Filter filter) {
        this.filter = filter;
    }
}
