package cn.dectfix.model.common;

public class Limit {
    private int length;

    public Limit(int length){
        this.length = length;
    }

    @Override
    public String toString() {
        return "LIMIT "+length;
    }

    public int getLength() {
        return length;
    }

    public void setLength(int length) {
        this.length = length;
    }
}
