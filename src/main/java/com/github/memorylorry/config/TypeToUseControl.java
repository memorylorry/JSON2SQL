package com.github.memorylorry.config;

import java.util.Iterator;
import java.util.Map;
import java.util.Properties;

public class TypeToUseControl {
    public static Properties  getDefault(){
        Properties status = new Properties();
        status.put("dimension",true);
        status.put("metric",true);
        status.put("filter",true);
        status.put("order",true);
        status.put("limit",true);
        status.put("database",true);
        status.put("table",true);
        return status;
    }

    public static Properties getDefault(Properties custom){
        Properties status = getDefault();
        Iterator iterator = custom.entrySet().iterator();
        while (iterator.hasNext()){
            Map.Entry<String, Object> entry=(Map.Entry<String, Object>) iterator.next();
            status.put(entry.getKey(),entry.getValue());
        }
        return status;
    }

    public static void main(){

    }
}
