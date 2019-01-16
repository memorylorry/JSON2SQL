package com.github.memorylorry.type;

import com.github.memorylorry.config.SQLGenerateControl;
import java.util.Iterator;

public class SimpleSlice implements Slice {
    private String title;
    private String database;
    private Table table;
    private int type;
    private ColumnList<Column> dimensions;
    private ColumnList<Column> metrics;
    private RestrictList<Filter> filters;
    private RestrictList<Order> orders;
    private String limit;

    public SimpleSlice() {
    }

    public String buildBasicSQL() throws IllegalAccessException, InstantiationException {
        String sql = "SELECT ";
        if (this.type == SQLGenerateControl.DIMENSIN_NOT_EXSIT) {
            sql = sql + this.metrics.buildSQL(true);
        } else if (this.type == SQLGenerateControl.DIMENSIN_CONCAT) {
            int dimensionSize = this.dimensions.size();
            if (dimensionSize == 1) {
                ColumnList<Column> columns = (ColumnList)this.dimensions.addList(this.metrics, ColumnList.class);
                sql = sql + columns.buildSQL(true);
            } else {
                String concatSQL = this.dimensions.buildSQLDivdByValue(",',',");
                if (concatSQL.length() > 0) {
                    concatSQL = "CONCAT(" + concatSQL + ") AS CONCAT_NAME";
                    sql = sql + concatSQL;
                }

                String metricSQL = "";
                if (this.metrics.size() > 0) {
                    metricSQL = metricSQL + "," + this.metrics.buildSQL(true);
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
            sql = sql + columns.buildSQL(true);
        }

        String dbName = this.database != null && !"".equals(this.database) ? this.database + "." : "";
        sql = sql + " FROM " + dbName + this.table.buildSQL();
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

        if (this.orders.size() > 0) {
            sql = sql + " ORDER BY " + this.orders.buildSQL();
        }

        if (!"".equals(this.limit)) {
            sql = sql + " LIMIT " + this.limit;
        }

        return sql;
    }

    public String buildCountSQL() {
        String sql = "SELECT count(1) FROM ";
        String dbName = this.database != null && !"".equals(this.database) ? this.database + "." : "";
        sql = sql + dbName + this.table.buildSQL();
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
}
