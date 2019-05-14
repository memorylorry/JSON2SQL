package com.github.memorylorry.type.helper;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class LgDateFunction {

    private static final Map<String,DateZone> funs = new HashMap<>();

    static {
        funs.put("yesterday",new DateZone("date_sub(current_date,1)"));
        funs.put("this_week",new DateZone("date_sub(CURRENT_DATE,cast(case when pmod(datediff(date_sub(CURRENT_DATE,1),'1920-01-01')-3,7)>0 then pmod(datediff(date_sub(CURRENT_DATE,1),'1920-01-01')-3,7) else 7 end as INT))"));
        funs.put("last_week",new DateZone(
                     "date_sub(CURRENT_DATE,7+cast(case when pmod(datediff(date_sub(CURRENT_DATE,1),'1920-01-01')-3,7)>0 then pmod(datediff(date_sub(CURRENT_DATE,1),'1920-01-01')-3,7) else 7 end as INT))"
                    ,"date_sub(CURRENT_DATE,1+cast(case when pmod(datediff(date_sub(CURRENT_DATE,1),'1920-01-01')-3,7)>0 then pmod(datediff(date_sub(CURRENT_DATE,1),'1920-01-01')-3,7) else 7 end as INT))"
                )
        );
        funs.put("n day ago",new DateZone("date_sub(CURRENT_DATE,1+%s)"));
        funs.put("this_month",new DateZone("concat(substring(date_sub(CURRENT_DATE,1),1,7),'-01')"));
        funs.put("this_season",new DateZone("date_sub(date_trunc('quarter',CURRENT_DATE),0)"));
        funs.put("this_year",new DateZone("concat(substring(date_sub(CURRENT_DATE,1),1,4),'-01-01')"));
    }

    public List<DateFun> format(String val){

        List<DateFun> res = new ArrayList<>();

        //n day ago 走此处
        if(val.matches("[0-9]+ day ago")){
            DateZone dz1 = funs.get("n day ago");
            String dayNum = val.split(" ")[0];

            DateZone dz = funs.get("n day ago");
            if(dz.getFrom()!=null){
                res.add(new DateFun(">=",String.format(dz.getFrom(),dayNum)));
            }

            return res;
        }

        //非动态参数名，走下面
        DateZone dz = funs.get(val);
        if(dz.getFrom()!=null){
            res.add(new DateFun(">=",dz.getFrom()));
        }
        if(dz.getTo()!=null){
            res.add(new DateFun("<=",dz.getTo()));
        }

        return res;
    }

}
