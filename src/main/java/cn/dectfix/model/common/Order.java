package cn.dectfix.model.common;

public class Order {

    private String name;

    private String dir;

    public Order(){}

    public Order(String name,String dir){
        this.name = name;
        this.dir = dir;
    }

    @Override
    public String toString() {
        return name + " " + dir;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDir() {
        return dir;
    }

    public void setDir(String dir) {
        this.dir = dir;
    }
}
