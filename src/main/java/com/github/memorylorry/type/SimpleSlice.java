package com.github.memorylorry.type;

import com.github.memorylorry.config.DimensionControl;

public class SimpleSlice implements Slice{

    private String title;
    private String database;//不能为空
    private Table table;//不能为空
    private int type;
    private ColumnList<Column> dimensions;
    private ColumnList<Column> metrics;
    private RestrictList<Filter> filters;
    private RestrictList<Order> orders;
    private String limit;

    @Override
    public String buildBasicSQL() throws IllegalAccessException, InstantiationException {

        String sql = "SELECT ";

        //dimension and metric!!! IMPORTAUNT
        if(type == DimensionControl.DIMENSIN_NOT_EXSIT){
            sql += metrics.buildSQL(true);
        }else if(type == DimensionControl.DIMENSIN_CONCAT){
            int dimensionSize = dimensions.size();
            if(dimensionSize==1){
                ColumnList<Column> columns = dimensions.addList(metrics,ColumnList.class);
                sql += columns.buildSQL(true);
            }else {
                //add dimension
                String concatSQL = dimensions.buildSQLDivdByValue(",',',");
                if(concatSQL.length()>0){
                    concatSQL = "CONCAT(" + concatSQL + ") AS CONCAT_NAME";
                    sql += concatSQL;
                }

                //add metric
                String metricSQL = "";
                if(metrics.size()>0){
                    metricSQL += "," + metrics.buildSQL(true);
                    if(concatSQL.length()>0){
                        sql += metricSQL;
                    }else{
                        metricSQL = metricSQL.substring(1,metricSQL.length());
                        sql += metricSQL;
                    }
                }

            }
        }else{
            ColumnList<Column> columns = (ColumnList<Column>) dimensions.addList(metrics,ColumnList.class);
            sql += columns.buildSQL(true);
        }

        //table
        String dbName = (database!=null&&(!"".equals(database)))?(database+"."):"";
        sql += " FROM "+ dbName + table.buildSQL();

        //filters are took apart into 2 parts(dimension and metric)!!!
        RestrictList<Filter> dimensionFilter = new RestrictList<>();
        RestrictList<Filter> metricFilter = new RestrictList<>();
        for(Filter filter:filters){
            if(filter.getType()==0){
                dimensionFilter.add(filter);
            }else{
                metricFilter.add(filter);
            }
        }

        //WHERE
        if(dimensionFilter.size()>0){
            sql += " WHERE "+dimensionFilter.buildSQLDivdByValue(" AND ");
        }

        //GROUP BY
        if(dimensions.size()>0){
            sql += " GROUP BY "+dimensions.buildSQL();
        }

        //HAVING
        if(metricFilter.size()>0){
            sql += " HAVING "+metricFilter.buildSQLDivdByValue(" AND ");
        }

        //ORDER
        if(orders.size()>0){
            sql += " ORDER BY "+orders.buildSQL();
        }

        //LIMIT
        if(!"".equals(limit)){
            sql += " LIMIT "+limit;
        }

        return sql;
    }

    public String buildCountSQL(){
        String sql = "SELECT count(1) FROM ";
        //table
        String dbName = (database!=null&&(!"".equals(database)))?(database+"."):"";
        sql += dbName+table.buildSQL();

        //filters are took apart into 2 parts(dimension and metric)!!!
        RestrictList<Filter> dimensionFilter = new RestrictList<>();
        RestrictList<Filter> metricFilter = new RestrictList<>();
        for(Filter filter:filters){
            if(filter.getType()==0){
                dimensionFilter.add(filter);
            }else{
                metricFilter.add(filter);
            }
        }

        //WHERE
        if(dimensionFilter.size()>0){
            sql += " WHERE "+dimensionFilter.buildSQLDivdByValue(" AND ");
        }

        //GROUP BY
        if(dimensions.size()>0){
            sql += " GROUP BY "+dimensions.buildSQL();
            //HAVING
            if(metricFilter.size()>0){
                sql += " HAVING "+metricFilter.buildSQLDivdByValue(" AND ");
            }
        }

        sql = "SELECT COUNT(1) total FROM ("+sql+") tmp";

        return sql;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDatabase() {
        return database;
    }

    public void setDatabase(String database) {
        this.database = database;
    }

    public Table getTable() {
        return table;
    }

    public void setTable(Table table) {
        this.table = table;
    }

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }

    public ColumnList<Column> getDimensions() {
        return dimensions;
    }

    public void setDimensions(ColumnList<Column> dimensions) {
        this.dimensions = dimensions;
    }

    public ColumnList<Column> getMetrics() {
        return metrics;
    }

    public void setMetrics(ColumnList<Column> metrics) {
        this.metrics = metrics;
    }

    public RestrictList<Filter> getFilters() {
        return filters;
    }

    public void setFilters(RestrictList<Filter> filters) {
        this.filters = filters;
    }

    public RestrictList<Order> getOrders() {
        return orders;
    }

    public void setOrders(RestrictList<Order> orders) {
        this.orders = orders;
    }

    public String getLimit() {
        return limit;
    }

    public void setLimit(String limit) {
        this.limit = limit;
    }
}
