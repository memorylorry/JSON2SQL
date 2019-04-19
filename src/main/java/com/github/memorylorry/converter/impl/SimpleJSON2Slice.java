package com.github.memorylorry.converter.impl;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.github.memorylorry.converter.JSON2Slice;
import com.github.memorylorry.type.Column;
import com.github.memorylorry.type.ColumnList;
import com.github.memorylorry.type.Filter;
import com.github.memorylorry.type.Order;
import com.github.memorylorry.type.RestrictList;
import com.github.memorylorry.type.SimpleSlice;
import com.github.memorylorry.type.Table;
import com.github.memorylorry.type.exception.SliceFormatNotSupportedException;
import com.github.memorylorry.util.TestErrorUtil;

public class SimpleJSON2Slice implements JSON2Slice {
    public SimpleJSON2Slice() {
    }

    public SimpleSlice format(String json) throws SliceFormatNotSupportedException {
        JSONObject obj = (JSONObject)JSONObject.parse(json);
        return this.format(obj);
    }

    public SimpleSlice format(JSONObject root) throws SliceFormatNotSupportedException {
        SimpleSlice slice = new SimpleSlice();
        String title = root.getString("title");
        TestErrorUtil.testNotNull(title);
        slice.setTitle(title);
        String database = root.getString("database");
        TestErrorUtil.testNotNull(database);
        slice.setDatabase(database);
        String table = root.getString("table");
        TestErrorUtil.testNotNull(table);
        TestErrorUtil.testNotInt(table);
        TestErrorUtil.testNot0Len(table);
        slice.setTable(new Table(table));
        String type = root.getString("type_val");
        TestErrorUtil.testNotNull(type);
        TestErrorUtil.testNot0Len(type);
        slice.setType(new Integer(type));
        JSONArray dimensionJSON = root.getJSONArray("dimension");
        TestErrorUtil.testNotNull(dimensionJSON);
        ColumnList dimension = this.parse2ColumnList(dimensionJSON);
        slice.setDimensions(dimension);
        JSONArray metricJSON = root.getJSONArray("metric");
        TestErrorUtil.testNotNull(metricJSON);
        ColumnList metric = this.parse2ColumnList(metricJSON);
        slice.setMetrics(metric);
        JSONArray filterJSON = root.getJSONArray("filter");
        TestErrorUtil.testNotNull(filterJSON);
        RestrictList<Filter> filterList = this.parse2FilterList(filterJSON);
        slice.setFilters(filterList);
        JSONArray orderJSON = root.getJSONArray("order");
        TestErrorUtil.testNotNull(orderJSON);
        RestrictList<Order> orderList = this.parse2OrderList(orderJSON);
        slice.setOrders(orderList);
        String limit = root.getString("limit");
        TestErrorUtil.testNotNull(limit);
        slice.setLimit(limit);
        return slice;
    }

    private ColumnList<Column> parse2ColumnList(JSONArray array) {
        ColumnList<Column> columns = new ColumnList();

        for(int i = 0; i < array.size(); ++i) {
            JSONObject obj = (JSONObject)array.get(i);
            String name = obj.getString("name");
            String verbose = obj.getString("verbose");
            String expression = obj.getString("expression");
            columns.add(new Column(name, verbose, expression));
        }

        return columns;
    }

    private RestrictList<Filter> parse2FilterList(JSONArray array) {
        RestrictList<Filter> filters = new RestrictList();

        for(int i = 0; i < array.size(); ++i) {
            JSONObject obj = (JSONObject)array.get(i);
            String name = obj.getString("name");
            String verbose = obj.getString("verbose");
            String expression = obj.getString("expression");
            String operation = obj.getString("operation");
            String option = obj.getString("option");
            String optionText = obj.getString("optionText");
            int type = obj.getInteger("type");
            filters.add(new Filter(name, verbose, expression, operation, option,optionText, type));
        }

        return filters;
    }

    private RestrictList<Order> parse2OrderList(JSONArray array) {
        RestrictList<Order> orders = new RestrictList();

        for(int i = 0; i < array.size(); ++i) {
            JSONObject obj = (JSONObject)array.get(i);
            String name = obj.getString("name");
            String verbose = obj.getString("verbose");
            String expression = obj.getString("expression");
            String operation = obj.getString("operation");
            orders.add(new Order(name, verbose, expression, operation));
        }

        return orders;
    }
}
