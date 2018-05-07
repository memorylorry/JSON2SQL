package cn.dectfix.model.common;

public class Table {

    private String name;

    private int isSQL;

    public Table(){}

    public Table(String name,int isSQL){
        this.name = name;
        this.isSQL = isSQL;
    }

    @Override
    public String toString() {
        if(isSQL>0){
            return "(" + name + ") t";
        }
        return name;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getIsSQL() {
        return isSQL;
    }

    public void setIsSQL(int isSQL) {
        this.isSQL = isSQL;
    }
}
