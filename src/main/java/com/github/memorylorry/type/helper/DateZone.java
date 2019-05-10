package com.github.memorylorry.type.helper;

public class DateZone {
    private String from;
    private String to;
    public DateZone(String from){
        this.from = from;
    }
    public DateZone(String from,String to){
        this.from = from;
        this.to = to;
    }

    public String getFrom() {
        return from;
    }

    public void setFrom(String from) {
        this.from = from;
    }

    public String getTo() {
        return to;
    }

    public void setTo(String to) {
        this.to = to;
    }
}
