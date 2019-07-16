package com.github.memorylorry.JSON2SQL.parser.impl;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.github.memorylorry.JSON2SQL.util.BeanCreator;
import com.github.memorylorry.JSON2SQL.ParserType;
import com.github.memorylorry.JSON2SQL.exception.JSON2SQLParseException;
import com.github.memorylorry.JSON2SQL.parser.JSONParser;
import com.github.memorylorry.JSON2SQL.parser.SliceParser;
import org.apache.commons.lang.StringUtils;

import java.util.HashMap;
import java.util.Iterator;
import java.util.List;

public class SliceCountParser extends SliceParser {

    private String suffix = "";
    private boolean useOrderAndLimit = true;

    @Override
    public String parse(JSONObject json) throws JSON2SQLParseException {
        String sql = "SELECT COUNT(1) FROM ";


        // read attr
        Integer type = json.getIntValue("type_val");
        JSONArray dimensions = json.getJSONArray("dimension");
        JSONArray metrics = json.getJSONArray("metric");
        JSONArray subMetrics = json.getJSONArray("subMetric");
        JSONArray filters = json.getJSONArray("filter");
        JSONArray orders = json.getJSONArray("order");

        HashMap<String,Object> columnParserParams = new HashMap<>();
        columnParserParams.put("isWithAS",false);
        columnParserParams.put("suffix",suffix);

        HashMap<String,Object> groupColumnParserParams = new HashMap<>();
        groupColumnParserParams.put("isWithAS",false);
        // form parser
        String COLUMN_PARSER = (String) parserConf.get(ParserType.COLUMN_PARSER.getName());
        String TABLE_PARSER = (String) parserConf.get(ParserType.TABLE_PARSER.getName());
        String FILTER_PARSER = (String) parserConf.get(ParserType.FILTER_PARSER.getName());
        String ORDER_PARSER = (String) parserConf.get(ParserType.ORDER_PARSER.getName());

        JSONParser columnParser = ((JSONParser) BeanCreator.create(COLUMN_PARSER)).bind(columnParserParams);
        JSONParser groupColumnParser = ((JSONParser) BeanCreator.create(COLUMN_PARSER)).bind(groupColumnParserParams);
        JSONParser tableParser = (JSONParser) BeanCreator.create(TABLE_PARSER);
        JSONParser filterParser = (JSONParser) BeanCreator.create(FILTER_PARSER);
        JSONParser orderParser = (JSONParser) BeanCreator.create(ORDER_PARSER);


        // table part
        String tab = tableParser.parse(json);
        sql += tab;
        String tableVerbose = "".equals(suffix)?"root":"inner_"+suffix;
        sql += " " + tableVerbose;


        // join part
        JSONArray join = json.getJSONArray("join");
        if(join!=null && join.size()>0){
            // subQuery parser
            SliceBasicParser subParser = new SliceBasicParser();
            subParser.useOrderAndLimit = false;

            for(int i=0;i<join.size();i++){
                JSONObject aJoin = join.getJSONObject(i);
                sql += " "+aJoin.getString("join_method");

                //(tbl) t
                JSONObject slice = aJoin.getJSONObject("slice");
                subParser.suffix = "_"+i;
                String subQuery = subParser.parse(slice);
                sql += " ("+subQuery+") st"+i;

                JSONObject condition = aJoin.getJSONObject("associate");
                JSONObject left = condition.getJSONObject("left");
                JSONObject right = condition.getJSONObject("right");

                String lfOP = left.getString("expression");
                String regex = ".*(\\+|-|\\*|/|\\(|\\)|\\[|\\]|\\{|\\}|\\ ).*";
                if(!lfOP.matches(regex)){
                    lfOP = tableVerbose+"."+lfOP;
                }

                sql += " on "+ lfOP + " " + condition.getString("op") + " " + right.getString("name")+"_"+i;
            }
        }


        // divide filter into two part
        JSONArrayWrapper dimensionFilter = new JSONArrayWrapper();
        JSONArrayWrapper metricFilter = new JSONArrayWrapper();
        JSONArrayWrapper filterWrapper = new JSONArrayWrapper(filters);
        Iterator filtersIterator = filterWrapper.iterator();

        while(filtersIterator.hasNext()) {
            JSONObject filter = (JSONObject)filtersIterator.next();
            int filterType = filter.getIntValue("type");
            if (filterType == 0) {
                dimensionFilter.add(filter);
            } else {
                metricFilter.add(filter);
            }
        }

        if (dimensionFilter.size() > 0) {
            List<String> res = dimensionFilter.loop(filterParser);
            sql = sql + " WHERE " + StringUtils.join(res," AND ");
        }

        if (dimensions.size() > 0) {
            List<String> res = dimensionFilter.loop(groupColumnParser);
            sql = sql + " GROUP BY " + StringUtils.join(res,",");
        }

        if (metricFilter.size() > 0) {
            List<String> res = metricFilter.loop(filterParser);
            sql = sql + " HAVING " + StringUtils.join(res," AND ");
        }


        sql = "SELECT COUNT(1) total FROM (" + sql + ") tmp";
        return sql;
    }
}
