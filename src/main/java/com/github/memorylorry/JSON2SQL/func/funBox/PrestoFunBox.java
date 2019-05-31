package com.github.memorylorry.JSON2SQL.func.funBox;

import com.github.memorylorry.JSON2SQL.func.DateZone;

import java.util.HashMap;

/**
 * 本类默认为Presto支持的函数
 */
public class PrestoFunBox extends HashMap {
    public PrestoFunBox(){
        put("yesterday",new DateZone("date_sub(current_date,1)"));
        put("this_week",new DateZone("date_sub(CURRENT_DATE,cast(case when pmod(datediff(date_sub(CURRENT_DATE,1),'1920-01-01')-3,7)>0 then pmod(datediff(date_sub(CURRENT_DATE,1),'1920-01-01')-3,7) else 7 end as INT))"));
        put("last_week",new DateZone(
                        "date_sub(CURRENT_DATE,7+cast(case when pmod(datediff(date_sub(CURRENT_DATE,1),'1920-01-01')-3,7)>0 then pmod(datediff(date_sub(CURRENT_DATE,1),'1920-01-01')-3,7) else 7 end as INT))"
                        ,"date_sub(CURRENT_DATE,1+cast(case when pmod(datediff(date_sub(CURRENT_DATE,1),'1920-01-01')-3,7)>0 then pmod(datediff(date_sub(CURRENT_DATE,1),'1920-01-01')-3,7) else 7 end as INT))"
                )
        );
        put("n day ago",new DateZone("date_sub(CURRENT_DATE,%s)"));
        put("this_month",new DateZone("concat(substring(date_sub(CURRENT_DATE,1),1,7),'-01')"));
        put("this_season",new DateZone("date_sub(date_trunc('quarter',CURRENT_DATE),0)"));
        put("this_year",new DateZone("concat(substring(date_sub(CURRENT_DATE,1),1,4),'-01-01')"));
    }
}
