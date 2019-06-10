package com.github.memorylorry.JSON2SQL.parser.impl;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.github.memorylorry.JSON2SQL.BeanCreator;
import com.github.memorylorry.JSON2SQL.ParserType;
import com.github.memorylorry.JSON2SQL.SQLGeneratorControl;
import com.github.memorylorry.JSON2SQL.exception.JSON2SQLParseException;
import com.github.memorylorry.JSON2SQL.parser.JSONParser;
import com.github.memorylorry.JSON2SQL.parser.SliceParser;
import org.apache.commons.lang.StringUtils;

import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

public class SliceBasicParser extends SliceParser {

    protected boolean useOrderAndLimit = true;
    protected String suffix = "";

    private boolean isMergedMetric = false;

    @Override
    public String parse(JSONObject json) throws JSON2SQLParseException {
        String sql = "SELECT ";

        // read attr
        Integer type = json.getIntValue("type_val");
        JSONArray dimensions = json.getJSONArray("dimension");
        JSONArray metrics = json.getJSONArray("metric");
        JSONArray subMetrics = json.getJSONArray("subMetric");
        JSONArray filters = json.getJSONArray("filter");
        JSONArray orders = json.getJSONArray("order");


        HashMap<String,Object> columnParserParams = new HashMap<>();
        columnParserParams.put("isWithAS",true);
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


        //merge metric with subMetric
        if(!isMergedMetric && metrics!=null && subMetrics!=null){
            metrics.addAll(subMetrics);
            isMergedMetric = true;
        }
        if (type == SQLGeneratorControl.DIMENSIN_NOT_EXSIT) {
            JSONArrayWrapper metricsWrapper = new JSONArrayWrapper(metrics);
            List<String> columnSQL = metricsWrapper.loop(columnParser);
            sql = sql + StringUtils.join(columnSQL,",");
        } else if (type == SQLGeneratorControl.DIMENSIN_CONCAT) {
            int dimensionSize = dimensions.size();
            if (dimensionSize == 1) {
                JSONArrayWrapper columns = new JSONArrayWrapper();
                columns.addAll(dimensions);
                columns.addAll(metrics);
                List<String> res = columns.loop(columnParser);
                sql = sql + StringUtils.join(res,",");
            } else {
                JSONArrayWrapper columns = new JSONArrayWrapper(dimensions);
                List<String> res = columns.loop(groupColumnParser);
                String concatSQL = StringUtils.join(res,",',',");
                if (concatSQL.length() > 0) {
                    concatSQL = "CONCAT(" + concatSQL + ") AS CONCAT_NAME";
                    sql = sql + concatSQL;
                }

                if (metrics.size() > 0) {
                    JSONArrayWrapper columns2 = new JSONArrayWrapper(dimensions);
                    List<String> res2 = columns2.loop(groupColumnParser);
                    String metricSQL = StringUtils.join(res2,suffix);
                    if (concatSQL.length() > 0) {
                        sql = sql + metricSQL;
                    }
                }
            }
        } else {
            JSONArrayWrapper columns = new JSONArrayWrapper();
            columns.addAll(dimensions);
            columns.addAll(metrics);
            List<String> res = columns.loop(columnParser);
            sql = sql + StringUtils.join(res,",");
        }


        // table part
        String tab = tableParser.parse(json);
        sql = sql + " FROM " + tab;
        String tableVerbose = "".equals(suffix)?"root":"inner"+suffix;
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


        // order part
        if (useOrderAndLimit && orders.size() > 0) {
            JSONArrayWrapper ordersWrapper = new JSONArrayWrapper(orders);
            List<String> orderSQL = ordersWrapper.loop(orderParser);
            sql = sql + " ORDER BY " + StringUtils.join(orderSQL,",");
        }


        // limit part
        if (useOrderAndLimit) {
            String limit = json.getString("limit");
            if(!StringUtils.isEmpty(limit))
                sql = sql + " LIMIT " + limit;
        }

        return sql;
    }

    @Override
    public JSONParser bind(Map<String, Object> data) {
        Object param1 = data.get("useOrderAndLimit");
        if(param1!=null){
            useOrderAndLimit = (boolean) param1;
        }
        Object param2 = data.get("suffix");
        if(param2!=null){
            suffix = (String) param2;
        }
        return this;
    }
}
