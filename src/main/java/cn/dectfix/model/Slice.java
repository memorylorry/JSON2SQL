package cn.dectfix.model;

import cn.dectfix.model.common.ColumnList;
import cn.dectfix.model.common.FilterList;
import cn.dectfix.model.common.Limit;
import cn.dectfix.model.common.OrderList;
import cn.dectfix.model.common.Table;

public class Slice {
    private Table table;
    private ColumnList dimension;
    private ColumnList metric;
    private FilterList whereFilter;
    private FilterList havingFilter;
    private OrderList order;
    private Limit limit;

    @Override
    public String toString() {
        String sql = "SELECT ";

        //dimension and metric
        if(dimension!=null){
            sql += dimension.toString();
            if(metric!=null){
                sql = sql+","+ metric.toString();
            }
        }else{
            if(metric!=null){
                sql += metric.toString();
            }
        }

        //table
        if(table!=null){
            sql = sql + " FROM "+table.toString();
        }

        //Group
        if(metric!=null&&dimension!=null){
            sql += " GROUP BY "+dimension.toColumnString();
        }

        //where
        if(whereFilter!=null){
            sql += " WHERE " + whereFilter.toString();
        }

        //having
        if(havingFilter!=null){
            sql += " HAVING " + havingFilter.toString();
        }

        //order
        if(order!=null){
            sql += " ORDER BY " + order.toString();
        }

        //limit
        if(limit!=null){
            sql += " "+limit.toString();
        }

        return sql;
    }

    public Table getTable() {
        return table;
    }

    public ColumnList getDimension() {
        return dimension;
    }

    public ColumnList getMetric() {
        return metric;
    }

    public OrderList getOrder() {
        return order;
    }

    public Limit getLimit() {
        return limit;
    }

    public void setTable(Table table) {
        this.table = table;
    }

    public void setDimension(ColumnList dimension) {
        this.dimension = dimension;
    }

    public void setMetric(ColumnList metric) {
        this.metric = metric;
    }

    public void setOrder(OrderList order) {
        this.order = order;
    }

    public void setLimit(Limit limit) {
        this.limit = limit;
    }

    public FilterList getWhereFilter() {
        return whereFilter;
    }

    public void setWhereFilter(FilterList whereFilter) {
        this.whereFilter = whereFilter;
    }

    public FilterList getHavingFilter() {
        return havingFilter;
    }

    public void setHavingFilter(FilterList havingFilter) {
        this.havingFilter = havingFilter;
    }
}
