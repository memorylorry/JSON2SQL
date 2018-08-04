package cn.dectfix.type;

import cn.dectfix.config.ChartType;
import cn.dectfix.type.common.*;

import java.util.List;

public class Slice {
    private String title;
    private String database;//不能为空
    private Table table;//不能为空
    private String type;
    private CommonList<Column> dimensions;
    private CommonList<Column> metrics;
    private CommonList<Filter> filters;
    private CommonList<Order> orders;
    private String limit;

    @Override
    public String toString() {
        //获取图表类型
        int chartType = ChartType.getDimension(type);

        String sql = "SELECT ";

        //dimension and metric!!! IMPORTAUNT
        if(chartType==0){
            sql += metrics.toSQLWithAS();
        }else if(chartType==1){
            int dimensionSize = dimensions.getColumns().size();
            if(dimensionSize==1){
                CommonList<Column> columns = dimensions.addColumnList(metrics);
                sql += columns.toSQLWithAS();
            }else {
                //add dimension
                String concatSQL = dimensions.toSQLDivdByValue(",',',");
                if(concatSQL.length()>0){
                    concatSQL = "CONCAT(" + concatSQL + ") AS CONCAT_NAME";
                    sql += concatSQL;
                }

                //add metric
                String metricSQL = "";
                if(metrics.getColumns().size()>0){
                    metricSQL += "," + metrics.toSQLWithAS();
                    if(concatSQL.length()>0){
                        sql += metricSQL;
                    }else{
                        metricSQL = metricSQL.substring(1,metricSQL.length());
                        sql += metricSQL;
                    }
                }

            }
        }else{
            CommonList<Column> columns = dimensions.addColumnList(metrics);
            sql += columns.toSQLWithAS();
        }

        //table
        sql += " FROM "+table.toSQL();

        //filters are took apart into 2 parts(dimension and metric)!!!
        CommonList<Filter> dimensionFilter = new CommonList<>();
        CommonList<Filter> metricFilter = new CommonList<>();
        List<Filter> res = filters.getColumns();
        for(Filter filter:res){
            if(filter.getType()==0){
                dimensionFilter.addColumn(filter);
            }else{
                metricFilter.addColumn(filter);
            }
        }

        //WHERE
        if(dimensionFilter.getColumns().size()>0){
            sql += " WHERE "+dimensionFilter.toSQLDivdByValue(" AND ");
        }

        //GROUP BY
        if(dimensions.getColumns().size()>0){
            sql += " GROUP BY "+dimensions.toSQL();
        }

        //HAVING
        if(metricFilter.getColumns().size()>0){
            sql += " HAVING "+metricFilter.toSQLDivdByValue(" AND ");
        }

        //ORDER
        if(orders.getColumns().size()>0){
            sql += " ORDER BY "+orders.toSQL();
        }

        //LIMIT
        if(!"".equals(limit)){
            sql += " LIMIT "+limit;
        }

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

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public Table getTable() {
        return table;
    }

    public void setTable(Table table) {
        this.table = table;
    }

    public CommonList<Column> getDimensions() {
        return dimensions;
    }

    public void setDimensions(CommonList<Column> dimensions) {
        this.dimensions = dimensions;
    }

    public CommonList<Column> getMetrics() {
        return metrics;
    }

    public void setMetrics(CommonList<Column> metrics) {
        this.metrics = metrics;
    }

    public CommonList<Filter> getFilters() {
        return filters;
    }

    public void setFilters(CommonList<Filter> filters) {
        this.filters = filters;
    }

    public CommonList<Order> getOrders() {
        return orders;
    }

    public void setOrders(CommonList<Order> orders) {
        this.orders = orders;
    }

    public String getLimit() {
        return limit;
    }

    public void setLimit(String limit) {
        this.limit = limit;
    }
}
