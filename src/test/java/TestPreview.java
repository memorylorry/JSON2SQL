import com.github.memorylorry.converter.impl.SimpleJSON2Slice;
import com.github.memorylorry.type.SimpleSlice;
import com.github.memorylorry.type.exception.SliceFormatNotSupportedException;

public class TestPreview {
    public static void main(String[] args) throws SliceFormatNotSupportedException, InstantiationException, IllegalAccessException {
        String jsonStr = "{\"title\":\"预览\",\"database\":\"rpt\",\"table\":\"select * from rpt.stock_check_warehouseoverall_13m limit 10\",\"type\":\"not_paginate_table\",\"type_val\":2,\"dimension\":[{\"name\":\"d0\",\"verbose\":\"ID\",\"expression\":\"ID\"},{\"name\":\"d1\",\"verbose\":\"月份\",\"expression\":\"calmonth\"}],\"metric\":[{\"name\":\"m0\",\"verbose\":\"仓库商品盘点准确率\",\"expression\":\"inventory_accurate\"},{\"name\":\"m1\",\"verbose\":\"货损率\",\"expression\":\"cargo_damage_rate\"},{\"name\":\"m2\",\"verbose\":\"报损金额\",\"expression\":\"wh_damagd_amt\"}],\"filter\":[],\"order\":[{\"name\":\"d0\",\"verbose\":\"\",\"expression\":\"ID\",\"operation\":\"asc\"}],\"limit\":\"10\",\"appType\":\"slice\"}";
        System.out.println(jsonStr);
        SimpleSlice slice = new SimpleJSON2Slice().format(jsonStr);

        System.out.println(slice.buildBasicSQL());
        System.out.println(slice.buildCountSQL());
    }
}
