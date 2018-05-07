package cn.dectfix.converter.impl;

import cn.dectfix.converter.JSON2Slice;
import cn.dectfix.model.Slice;
import cn.dectfix.model.common.Column;
import cn.dectfix.model.common.ColumnList;
import cn.dectfix.model.common.*;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;

import java.util.ArrayList;
import java.util.List;
/*
{
	"table": {
		"name": "job",
		"isSQL": 0
	},
	"dimension": [{
		"name": "job_id",
		"verbose": "id"
	}, {
		"name": "job_time",
		"verbose": "time"
	}],
	"metric": [{
		"name": "count(1)",
		"verbose": "num"
	}, {
		"name": "count(2)",
		"verbose": "2xnum"
	}],
	"whereFilter": [{
		"name": "job_id",
		"op": ">",
		"value": "90"
	}],
	"havingFilter": [

		{
			"name": "count(1)",
			"op": ">=",
			"value": "80"
		}
	],
	"order": [{
		"name": "id",
		"dir": "asc"
	}, {
		"name": "num",
		"dir": "asc"
	}],
	"limit": {
		"length": 1000
	}
}
*/

public class SimpleJSON2Slice implements JSON2Slice {
    @Override
    public Slice format(String json) {
        JSONObject obj = (JSONObject) JSONObject.parse(json);
        return format(obj);
    }

    @Override
    public Slice format(JSONObject root) {
        Slice slice = new Slice();

        //<table>
        JSONObject tableJSON = root.getJSONObject("table");
        if(tableJSON!=null){
            String tableName = tableJSON.getString("name");
            int isSQL = tableJSON.getInteger("isSQL");
            Table table = new Table(tableName,isSQL);
            slice.setTable(table);
        }

        //[dimension]
        JSONArray dimensionJSON = root.getJSONArray("dimension");
        if(dimensionJSON!=null){
            ColumnList dimension = parse2ColumnList(dimensionJSON);
            slice.setDimension(dimension);
        }

        //[metric]
        JSONArray metricJSON = root.getJSONArray("metric");
        if(metricJSON!=null) {
            ColumnList metric = parse2ColumnList(metricJSON);
            slice.setMetric(metric);
        }

        //[whereFilter]
        JSONArray whereFilterJSON = root.getJSONArray("whereFilter");
        if(whereFilterJSON!=null) {
            FilterList whereFilter = parse2FilterList(whereFilterJSON);
            slice.setWhereFilter(whereFilter);
        }

        //[havingFilter]
        JSONArray havingFilterJSON = root.getJSONArray("havingFilter");
        if(havingFilterJSON!=null) {
            FilterList havingFilter = parse2FilterList(havingFilterJSON);
            slice.setHavingFilter(havingFilter);
        }

        //[order]
        JSONArray orderJSON = root.getJSONArray("order");
        if(orderJSON!=null){
            OrderList order = parse2OrderList(orderJSON);
            slice.setOrder(order);
        }

        //[limit]
        JSONObject limitJSON = root.getJSONObject("limit");
        if(limitJSON!=null){
            int limitLength = limitJSON.getInteger("length");
            Limit limit = new Limit(limitLength);
            slice.setLimit(limit);
        }

        return slice;
    }

    private ColumnList parse2ColumnList(JSONArray array){
        List<Column> columns = new ArrayList();
        for(int i=0;i<array.size();i++){
            JSONObject obj = (JSONObject) array.get(i);
            String name = obj.getString("name");
            String verbose = obj.getString("verbose");
            columns.add(new Column(name,verbose));
        }

        ColumnList columnList = new ColumnList();
        columnList.setColumns(columns);
        return columnList;
    }

    private FilterList parse2FilterList(JSONArray array){
        List<Filter> filters = new ArrayList();
        for(int i=0;i<array.size();i++){
            JSONObject obj = (JSONObject) array.get(i);
            String name = obj.getString("name");
            String op = obj.getString("op");
            String verbose = obj.getString("value");
            filters.add(new Filter(name,op,verbose));
        }

        FilterList filterList = new FilterList();
        filterList.setFilters(filters);
        return filterList;
    }

    private OrderList parse2OrderList(JSONArray array){
        List<Order> orders = new ArrayList();
        for(int i=0;i<array.size();i++){
            JSONObject obj = (JSONObject) array.get(i);
            String name = obj.getString("name");
            String dir = obj.getString("dir");
            orders.add(new Order(name,dir));
        }

        OrderList orderList = new OrderList();
        orderList.setOrders(orders);
        return orderList;
    }

}
