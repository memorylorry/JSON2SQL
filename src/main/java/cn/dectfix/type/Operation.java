package cn.dectfix.type;

public abstract class Operation {
    /**
     * 返回有AS构成的SQL
     * @return
     */
    public String toSQLWithAS(){
        return "";
    }

    /**
     * 返回没有AS构成的SQL
     * @return
     */
    public String toSQL(){
        return "";
    }
}
