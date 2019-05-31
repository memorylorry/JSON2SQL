package com.github.memorylorry.JSON2SQL.func;

import com.github.memorylorry.JSON2SQL.func.funBox.PrestoFunBox;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class LgDateFunction {

    private static Map<String,DateZone> funs;

    private LgDateFunction(){}

    /**
     * 创建函数解析器
     * @return
     */
    public static LgDateFunction create(){
        LgDateFunction lgDateFunction = new LgDateFunction();
        funs = new PrestoFunBox();
        return lgDateFunction;
    }

    /**
     * 配置函数库
     * @param funs
     * @return
     */
    public LgDateFunction conf(HashMap funs){
        this.funs = funs;
        return this;
    }

    /**
     * 解析字符串
     * @param val
     * @return
     */
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
