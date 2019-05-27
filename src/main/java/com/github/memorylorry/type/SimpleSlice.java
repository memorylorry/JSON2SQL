package com.github.memorylorry.type;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.github.memorylorry.config.SQLGenerateControl;
import com.github.memorylorry.converter.JSON2Slice;
import com.github.memorylorry.converter.impl.SimpleJSON2Slice;
import com.github.memorylorry.type.exception.SliceFormatNotSupportedException;

import java.util.Iterator;

public class SimpleSlice implements Slice {
    private String title;
    private String database;
    private Table table;
    private int type;
    private ColumnList<Column> dimensions;
    private ColumnList<Column> metrics;
    private ColumnList<Column> subMetrics;
    private RestrictList<Filter> filters;
    private RestrictList<Order> orders;
    private String limit;
    private JSONArray join;

    private boolean isMergedMetric = false;

    public SimpleSlice() {
    }

    public String buildBasicSQL() throws IllegalAccessException, InstantiationException {
        return buildBasicSQL("",true);
    }
    @Override
    public String buildBasicSQL(String suffix,boolean useOrderAndLimit) throws IllegalAccessException, InstantiationException {
        String sql = "SELECT ";

        //metric和subMetric合并
        if(!isMergedMetric && this.metrics!=null && this.subMetrics!=null){
            this.metrics = this.metrics.addList(this.subMetrics, ColumnList.class);
            isMergedMetric = true;
        }
        if (this.type == SQLGenerateControl.DIMENSIN_NOT_EXSIT) {
            sql = sql + this.metrics.buildSQL(true,suffix);
        } else if (this.type == SQLGenerateControl.DIMENSIN_CONCAT) {
            int dimensionSize = this.dimensions.size();
            if (dimensionSize == 1) {
                ColumnList<Column> columns = (ColumnList)this.dimensions.addList(this.metrics, ColumnList.class);
                sql = sql + columns.buildSQL(true,suffix);
            } else {
                String concatSQL = this.dimensions.buildSQLDivdByValue(",',',");
                if (concatSQL.length() > 0) {
                    concatSQL = "CONCAT(" + concatSQL + ") AS CONCAT_NAME";
                    sql = sql + concatSQL;
                }

                String metricSQL = "";
                if (this.metrics.size() > 0) {
                    metricSQL = metricSQL + "," + this.metrics.buildSQL(true,suffix);
                    if (concatSQL.length() > 0) {
                        sql = sql + metricSQL;
                    } else {
                        metricSQL = metricSQL.substring(1, metricSQL.length());
                        sql = sql + metricSQL;
                    }
                }
            }
        } else {
            ColumnList<Column> columns = (ColumnList)this.dimensions.addList(this.metrics, ColumnList.class);
            sql = sql + columns.buildSQL(true,suffix);
        }

        String dbName = this.database != null && !"".equals(this.database) ? this.database + "." : "";
        //衔接表名
        String tab = this.table.buildSQL();
        sql = sql + " FROM " + (tab.toUpperCase().indexOf("SELECT") >= 0?
                                tab:dbName + tab);
        String tableVerbose = "".equals(suffix)?"root":"inner"+suffix;
        sql += " " + tableVerbose;

        //增加join的操作
        if(this.join!=null){
            for(int i=0;i<this.join.size();i++){
                JSONObject aJoin = this.join.getJSONObject(i);
                sql += " "+aJoin.getString("join_method");

                //(tbl) t
                JSONObject slice = aJoin.getJSONObject("slice");
                SimpleJSON2Slice json2Slice = new SimpleJSON2Slice();
                try {
                    SimpleSlice ss = json2Slice.format(slice);
                    sql += " ("+ss.buildBasicSQL("_"+i,false)+") st"+i;
                } catch (SliceFormatNotSupportedException e) {
                    e.printStackTrace();
                    sql += e.getMessage();
                }

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

        //衔接过滤器
        RestrictList<Filter> dimensionFilter = new RestrictList();
        RestrictList<Filter> metricFilter = new RestrictList();
        Iterator var5 = this.filters.iterator();

        while(var5.hasNext()) {
            Filter filter = (Filter)var5.next();
            if (filter.getType() == 0) {
                dimensionFilter.add(filter);
            } else {
                metricFilter.add(filter);
            }
        }

        if (dimensionFilter.size() > 0) {
            sql = sql + " WHERE " + dimensionFilter.buildSQLDivdByValue(" AND ");
        }

        if (this.dimensions.size() > 0) {
            sql = sql + " GROUP BY " + this.dimensions.buildSQL();
        }

        if (metricFilter.size() > 0) {
            sql = sql + " HAVING " + metricFilter.buildSQLDivdByValue(" AND ");
        }

        if (useOrderAndLimit && this.orders.size() > 0) {
            sql = sql + " ORDER BY " + this.orders.buildSQL();
        }

        if (useOrderAndLimit && !"".equals(this.limit)) {
            sql = sql + " LIMIT " + this.limit;
        }

        return sql;
    }

    public String buildCountSQL() {
        return buildCountSQL("",true);
    }
    @Override
    public String buildCountSQL(String suffix,boolean useOrderAndLimit) {
        String sql = "SELECT count(1) FROM ";

        //衔接表名
        String dbName = this.database != null && !"".equals(this.database) ? this.database + "." : "";
        String tab = this.table.buildSQL();
        sql = sql + (tab.toUpperCase().indexOf("SELECT") >= 0?
                tab:dbName + tab);

        String tableVerbose = "".equals(suffix)?"root":"inner_"+suffix;
        sql += " " + tableVerbose;

        //增加join的操作
        if(this.join!=null){
            for(int i=0;i<this.join.size();i++){
                JSONObject aJoin = this.join.getJSONObject(i);
                sql += " "+aJoin.getString("join_method");

                //(tbl) t
                JSONObject slice = aJoin.getJSONObject("slice");
                SimpleJSON2Slice json2Slice = new SimpleJSON2Slice();
                try {
                    SimpleSlice ss = json2Slice.format(slice);
                    sql += " ("+ss.buildBasicSQL("_"+i,false)+") st"+i;
                } catch (SliceFormatNotSupportedException e) {
                    e.printStackTrace();
                    sql += e.getMessage();
                } catch (IllegalAccessException|InstantiationException e) {
                    e.printStackTrace();
                }

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

        RestrictList<Filter> dimensionFilter = new RestrictList();
        RestrictList<Filter> metricFilter = new RestrictList();
        Iterator var5 = this.filters.iterator();

        while(var5.hasNext()) {
            Filter filter = (Filter)var5.next();
            if (filter.getType() == 0) {
                dimensionFilter.add(filter);
            } else {
                metricFilter.add(filter);
            }
        }

        if (dimensionFilter.size() > 0) {
            sql = sql + " WHERE " + dimensionFilter.buildSQLDivdByValue(" AND ");
        }

        if (this.dimensions.size() > 0) {
            sql = sql + " GROUP BY " + this.dimensions.buildSQL();
            if (metricFilter.size() > 0) {
                sql = sql + " HAVING " + metricFilter.buildSQLDivdByValue(" AND ");
            }
        }

        sql = "SELECT COUNT(1) total FROM (" + sql + ") tmp";
        return sql;
    }

    public String getTitle() {
        return this.title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDatabase() {
        return this.database;
    }

    public void setDatabase(String database) {
        this.database = database;
    }

    public Table getTable() {
        return this.table;
    }

    public void setTable(Table table) {
        this.table = table;
    }

    public int getType() {
        return this.type;
    }

    public void setType(int type) {
        this.type = type;
    }

    public ColumnList<Column> getDimensions() {
        return this.dimensions;
    }

    public void setDimensions(ColumnList<Column> dimensions) {
        this.dimensions = dimensions;
    }

    public ColumnList<Column> getMetrics() {
        return this.metrics;
    }

    public void setMetrics(ColumnList<Column> metrics) {
        this.metrics = metrics;
    }

    public RestrictList<Filter> getFilters() {
        return this.filters;
    }

    public void setFilters(RestrictList<Filter> filters) {
        this.filters = filters;
    }

    public RestrictList<Order> getOrders() {
        return this.orders;
    }

    public void setOrders(RestrictList<Order> orders) {
        this.orders = orders;
    }

    public String getLimit() {
        return this.limit;
    }

    public void setLimit(String limit) {
        this.limit = limit;
    }

    public JSONArray getJoin() {
        return join;
    }

    public void setJoin(JSONArray join) {
        this.join = join;
    }

    public ColumnList<Column> getSubMetrics() {
        return subMetrics;
    }

    public void setSubMetrics(ColumnList<Column> subMetrics) {
        this.subMetrics = subMetrics;
    }
}
