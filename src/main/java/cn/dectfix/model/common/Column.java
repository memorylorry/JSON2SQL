package cn.dectfix.model.common;

public class Column {

    private String name;
    private String verbose;

    public Column(){}

    public Column(String name,String verbose){
        this.name = name;
        this.verbose = verbose;
    }

    @Override
    public String toString() {
        return name + " AS " + verbose;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getVerbose() {
        return verbose;
    }

    public void setVerbose(String verbose) {
        this.verbose = verbose;
    }
}
