package com.github.memorylorry.converter.impl;

import com.github.memorylorry.converter.JSON2Slice;
import com.github.memorylorry.type.*;
import com.github.memorylorry.type.exception.SliceFormatNotSupportedException;
import com.github.memorylorry.util.TestErrorUtil;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;

public class SimpleJSON2Slice implements JSON2Slice {
    @Override
    public SimpleSlice format(String json) throws SliceFormatNotSupportedException {
        JSONObject obj = (JSONObject) JSONObject.parse(json);
        return format(obj);
    }

    @Override
    public SimpleSlice format(JSONObject root) throws SliceFormatNotSupportedException {
        SimpleSlice slice = new SimpleSlice();

        //[title]
        String title = root.getString("title");
        //###DETECT ERROR###
        TestErrorUtil.testNotNull(title);
        //###END###
        slice.setTitle(title);

        //<database>
        String database = root.getString("database");
        //###DETECT ERROR###
        TestErrorUtil.testNotNull(database);
        //###END###
        slice.setDatabase(database);

        //<table>
        String table = root.getString("table");
        //###DETECT ERROR###
        TestErrorUtil.testNotNull(table);
        TestErrorUtil.testNotInt(table);
        TestErrorUtil.testNot0Len(table);
        //###END###
        slice.setTable(new Table(table));

        //<type>
        String type = root.getString("type_val");
        //###DETECT ERROR###
        TestErrorUtil.testNotNull(type);
        TestErrorUtil.testNot0Len(type);
        //###END###
        slice.setType(new Integer(type));

        //[dimension]
        JSONArray dimensionJSON = root.getJSONArray("dimension");
        //###DETECT ERROR###
        TestErrorUtil.testNotNull(dimensionJSON);
        //###END###
        ColumnList dimension = parse2ColumnList(dimensionJSON);
        slice.setDimensions(dimension);


        //[metric]
        JSONArray metricJSON = root.getJSONArray("metric");
        //###DETECT ERROR###
        TestErrorUtil.testNotNull(metricJSON);
        //###END###
        ColumnList metric = parse2ColumnList(metricJSON);
        slice.setMetrics(metric);

        //[filter]
        JSONArray filterJSON = root.getJSONArray("filter");
        //###DETECT ERROR###
        TestErrorUtil.testNotNull(filterJSON);
        //###END###
        RestrictList<Filter> filterList = parse2FilterList(filterJSON);
        slice.setFilters(filterList);

        //[order]
        JSONArray orderJSON = root.getJSONArray("order");
        //###DETECT ERROR###
        TestErrorUtil.testNotNull(orderJSON);
        RestrictList<Order> orderList = parse2OrderList(orderJSON);
        slice.setOrders(orderList);

        //[limit]
        String limit = root.getString("limit");
        //###DETECT ERROR###
        TestErrorUtil.testNotNull(limit);
        //###END###
        slice.setLimit(limit);

        return slice;
    }

    private ColumnList<Column> parse2ColumnList(JSONArray array){
        ColumnList<Column> columns = new ColumnList();
        for(int i=0;i<array.size();i++){
            JSONObject obj = (JSONObject) array.get(i);
            String name = obj.getString("name");
            String verbose = obj.getString("verbose");
            String expression = obj.getString("expression");
            columns.add(new Column(name,verbose,expression));
        }

        return columns;
    }

    private RestrictList<Filter> parse2FilterList(JSONArray array){
        RestrictList<Filter> filters = new RestrictList();
        for(int i=0;i<array.size();i++){
            JSONObject obj = (JSONObject) array.get(i);
            String name = obj.getString("name");
            String verbose = obj.getString("verbose");
            String expression = obj.getString("expression");
            String operation = obj.getString("operation");
            String option = obj.getString("option");
            int type = obj.getInteger("type");
            filters.add(new Filter(name,verbose,expression,operation,option,type));
        }

        return filters;
    }

    private RestrictList<Order> parse2OrderList(JSONArray array){
        RestrictList<Order> orders = new RestrictList();
        for(int i=0;i<array.size();i++){
            JSONObject obj = (JSONObject) array.get(i);
            String name = obj.getString("name");
            String verbose = obj.getString("verbose");
            String expression = obj.getString("expression");
            String operation = obj.getString("operation");
            orders.add(new Order(name,verbose,expression,operation));
        }

        return orders;
    }

}
