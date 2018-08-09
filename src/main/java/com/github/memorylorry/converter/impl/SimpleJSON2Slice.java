package com.github.memorylorry.converter.impl;

import com.github.memorylorry.converter.JSON2Slice;
import com.github.memorylorry.type.Slice;
import com.github.memorylorry.type.common.*;
import com.github.memorylorry.type.exception.SliceFormatNotSupportedException;
import com.github.memorylorry.util.TestErrorUtil;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class SimpleJSON2Slice implements JSON2Slice {
    @Override
    public Slice format(String json) throws SliceFormatNotSupportedException {
        JSONObject obj = (JSONObject) JSONObject.parse(json);
        return format(obj);
    }

    @Override
    public Slice format(JSONObject root) throws SliceFormatNotSupportedException {
        Slice slice = new Slice();

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
        String type = root.getString("type");
        //###DETECT ERROR###
        TestErrorUtil.testNotNull(type);
        TestErrorUtil.testNotInt(type);
        TestErrorUtil.testNot0Len(type);
        //###END###
        slice.setType(type);

        //[dimension]
        JSONArray dimensionJSON = root.getJSONArray("dimension");
        //###DETECT ERROR###
        TestErrorUtil.testNotNull(dimensionJSON);
        //###END###
        CommonList dimension = parse2ColumnList(dimensionJSON);
        slice.setDimensions(dimension);


        //[metric]
        JSONArray metricJSON = root.getJSONArray("metric");
        //###DETECT ERROR###
        TestErrorUtil.testNotNull(metricJSON);
        //###END###
        CommonList metric = parse2ColumnList(metricJSON);
        slice.setMetrics(metric);

        //[filter]
        JSONArray filterJSON = root.getJSONArray("filter");
        //###DETECT ERROR###
        TestErrorUtil.testNotNull(filterJSON);
        //###END###
        CommonList<Filter> filterList = parse2FilterList(filterJSON);
        slice.setFilters(filterList);

        //[order]
        JSONArray orderJSON = root.getJSONArray("order");
        //###DETECT ERROR###
        TestErrorUtil.testNotNull(orderJSON);
        CommonList<Order> orderList = parse2OrderList(orderJSON);
        slice.setOrders(orderList);

        //[limit]
        String limit = root.getString("limit");
        //###DETECT ERROR###
        TestErrorUtil.testNotNull(limit);
        //###END###
        slice.setLimit(limit);

        return slice;
    }

    private CommonList<Column> parse2ColumnList(JSONArray array){
        List<Column> columns = new ArrayList();
        for(int i=0;i<array.size();i++){
            JSONObject obj = (JSONObject) array.get(i);
            String name = obj.getString("name");
            String verbose = obj.getString("verbose");
            String expression = obj.getString("expression");
            columns.add(new Column(name,verbose,expression));
        }

        CommonList columnList = new CommonList();
        columnList.setColumns(columns);
        return columnList;
    }

    private CommonList<Filter> parse2FilterList(JSONArray array){
        List<Filter> filters = new ArrayList();
        for(int i=0;i<array.size();i++){
            JSONObject obj = (JSONObject) array.get(i);
            String name = obj.getString("name");
            String verbose = obj.getString("verbose");
            String expression = obj.getString("expression");
            int type = obj.getInteger("type");
            String sql = obj.getString("sql");
            filters.add(new Filter(name,verbose,expression,sql,type));
        }

        CommonList columnList = new CommonList();
        columnList.setColumns(filters);
        return columnList;
    }

    private CommonList<Order> parse2OrderList(JSONArray array){
        List<Order> orders = new ArrayList();
        for(int i=0;i<array.size();i++){
            JSONObject obj = (JSONObject) array.get(i);
            String name = obj.getString("name");
            String verbose = obj.getString("verbose");
            String expression = obj.getString("expression");
            int type = obj.getInteger("type");
            String sql = obj.getString("sql");
            orders.add(new Order(name,verbose,expression,sql,type));
        }

        CommonList columnList = new CommonList();
        columnList.setColumns(orders);
        return columnList;
    }

}
