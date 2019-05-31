package com.github.memorylorry.JSON2SQL.parser.impl;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.github.memorylorry.JSON2SQL.func.DateFun;
import com.github.memorylorry.JSON2SQL.func.LgDateFunction;
import com.github.memorylorry.JSON2SQL.parser.JSONParser;
import org.apache.commons.lang.StringUtils;

import java.util.ArrayList;
import java.util.List;

public class FilterParser extends JSONParser {

    JSONObject filter;

    @Override
    public String parse(JSONObject filter) {
        this.filter = filter;

        String name = filter.getString("name");
        String expression = filter.getString("expression");
        String operation = filter.getString("operation");
        String optionStr = filter.getString("option");
        Class opType = FilterOperationType.getTypeByOperation(operation);
        Integer filterType = filter.getIntValue("type");


        String option = "";
        String column = StringUtils.isEmpty(expression) ? name : expression;
        String sql;
        //处理数组类型的操作符
        if (opType.equals(ArrayList.class)) {
            //optionText
            String optionText = filter.getString("optionText");
            if(!StringUtils.isEmpty(optionText)){
                optionText = filterType==1?
                        optionText.replaceAll("\n",""):
                        "'" + optionText.replaceAll(",","','").replaceAll("\n","") + "'";
            }

            //option处理
            JSONArray optionsArr = JSON.parseArray(optionStr);

            for(int i = 0; i < optionsArr.size(); ++i) {
                String o = this.formatByFun(optionsArr.getString(i), "fun:");
                option = option + "," + o;
            }

            if (option.length() > 0) {
                option = option.substring(1, option.length());
                if(!StringUtils.isEmpty(optionText))option = option + "," + optionText;
            }else if(!StringUtils.isEmpty(optionText)){
                option = optionText;
            }

            sql = column + " " + operation + " (" + option +")";

            //处理日期函数类型的操作符
        }else if(opType.equals(LgDateFunction.class)){
            List<DateFun> conditions = LgDateFunction.create().format(optionStr);
            List<String> res = new ArrayList<>();
            for(DateFun df:conditions){
                res.add(column + " "+df.getOp()+" "+df.getVal());
            }

            return StringUtils.join(res," and ");

            //处理默认值
        }else {
            option = this.formatByFun(optionStr, "fun:");
            sql = column + " " + operation + " " + option;
        }

        return sql;
    }

    /**
     * 格式化filter的value
     * @param prefix 过滤器的值是否以某字符串开始，若以该串开始则不加引号
     * @return filter被格式化后的value
     */
    private String formatByFun(String str, String prefix) {
        Integer type = filter.getIntValue("type");
        String operation = filter.getString("operation");

        String option = type==1 && operation!=null && !operation.contains("like")?
                str:
                str.startsWith(prefix) && str.length() > prefix.length() ? str.substring(prefix.length(), str.length()) : "'" + str + "'";
        return option;
    }

}
