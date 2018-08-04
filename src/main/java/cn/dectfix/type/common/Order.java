package cn.dectfix.type.common;

import cn.dectfix.type.Operation;

public class Order extends Operation {

    private String name;//不允许有空格
    private String verbose;
    private String expression;//如果expression为空串，则用name
    /**
     * sql is used to filter
     * 其中关键字，一律用#{value},后端去决定用name还是用expression
     */
    private String sql;
    /**
     * type 0-dimension(where) 1-metric(having)
     */
    private int type;

    public Order(){}

    public Order(String name,String verbose,String expression,String sql,int type){
        this.name = name;
        this.verbose = verbose;
        this.expression = expression;
        this.sql = sql;
        this.type = type;
    }

    @Override
    public String toSQL() {
        String res = "";
        String regx = "#\\{value}";
        if("".equals(expression)){
            res = sql.replaceAll(regx,name);
        }else{
            res = sql.replaceAll(regx,expression);
        }
        return res;
    }

}
