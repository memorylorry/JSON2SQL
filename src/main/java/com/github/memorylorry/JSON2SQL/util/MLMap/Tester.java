package com.github.memorylorry.JSON2SQL.util.MLMap;

public class Tester {

    static MLMap<String,Object> res = new MLHashMap();
    static {
        res.put("name","ml");
        res.put("age",23);
    }

    public static void main(String args[]){
        String a = res.get("name","");
        System.out.println(a);
    }
}
