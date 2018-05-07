package cn.dectfix.model.common;

public class Filter {

    private String name;

    /**
     * value Range: [>,<,=,!=,>=,<=,like,in,not in]
     */
    private String operate;

    private String value;

    public Filter(){}

    public Filter(String name,String operate,String value){
        this.name = name;
        this.operate = operate;
        this.value = value;
    }

    @Override
    public String toString() {
        if(operate.indexOf("in")>=0){
            return name + " " + operate + " (" + value + ")";
        }

        return name + " " + operate + " '" + value + "'";
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getOperate() {
        return operate;
    }

    public void setOperate(String operate) {
        this.operate = operate;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }
}
