package com.github.memorylorry.JSON2SQL.func.funBox;

import com.github.memorylorry.JSON2SQL.func.DateZone;

import java.util.HashMap;

/**
 * 本类默认为Presto支持的函数
 */
public class MySQLFunBox extends HashMap {
    public MySQLFunBox(){
        put("yesterday",new DateZone("date_sub(current_date,interval 1 day))"));
        put("this_week",new DateZone("date_sub(curdate(),INTERVAL WEEKDAY(date_sub(curdate(),interval 1 day)) + 1 DAY)"));
        put("last_week",new DateZone(
                        "date_sub(curdate(),INTERVAL WEEKDAY(date_sub(curdate(),interval 1 day)) + 8 DAY)"
                        ,"date_sub(curdate(),INTERVAL WEEKDAY(date_sub(curdate(),interval 1 day)) + 2 DAY)"
                )
        );
        put("n day ago",new DateZone("date_sub(CURRENT_DATE,interval %s day)"));
        put("this_month",new DateZone("concat(substring(date_sub(CURRENT_DATE,interval 1 day),1,7),'-01')"));
        put("this_season",new DateZone("concat(date_format(LAST_DAY(MAKEDATE(EXTRACT(YEAR FROM  CURDATE()),1) + interval QUARTER(CURDATE())*3-3 month),'%Y-%m-'),'01')"));
        put("this_year",new DateZone("concat(substring(date_sub(CURRENT_DATE,interval 1 day),1,4),'-01-01')"));
    }
}
