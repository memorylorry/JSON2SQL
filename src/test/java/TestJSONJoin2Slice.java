
import com.github.memorylorry.converter.impl.SimpleJSON2Slice;
import com.github.memorylorry.type.SimpleSlice;
import com.github.memorylorry.type.exception.SliceFormatNotSupportedException;
import org.junit.Test;

public class TestJSONJoin2Slice {

    @Test
    public void fullTest() throws SliceFormatNotSupportedException, InstantiationException, IllegalAccessException {

        String jsonStr = "{\"database\":\"rpt\",\"table_id\":88,\"table\":\"stock_check_warehouseoverall_13m\",\"type\":\"not_paginate_table\",\"type_val\":2,\"dimension\":[{\"expression\":\"calmonth\",\"name\":\"d0\",\"verbose\":\"月份\"}],\"metric\":[],\"filter\":[],\"order\":[{\"name\":\"d0\",\"verbose\":\"\",\"expression\":\"calmonth\",\"operation\":\"asc\"}],\"limit\":5000,\"title\":\"11111111111\",\"href\":{\"title\":\"11111111\",\"val\":\"/portal/explore/dashboard/init?dashboardID=\"},\"join\":[{\"join_method\":\"inner join\",\"slice\":{\"database\":\" rpt\",\"table_id\":142,\"table\":\"rpt_largeinventory\",\"type\":\"pure_number\",\"type_val\":0,\"dimension\":[],\"metric\":[{\"expression\":\"sum(largeinventory_amt)\",\"name\":\"c617\",\"verbose\":\"大库存金额\"}],\"filter\":[{\"expression\":\"channel\",\"name\":\"c611\",\"type\":0,\"operation\":\"in\",\"verbose\":\"渠道\",\"sql\":\"\",\"option\":[\"线下仓\",\"线上仓\"]},{\"name\":\"c620\",\"verbose\":\"分区时间\",\"expression\":\"ds\",\"sql\":\"\",\"type\":0,\"operation\":\"=\",\"option\":\"fun:date_sub(current_date,1)\"}],\"order\":[],\"limit\":\"\",\"title\":\"指标-大库存金额（昨日）\",\"href\":{}},\"associate\":{\"op\":\"=\",\"left\":{\"expression\":\"ID\",\"name\":\"d0\",\"verbose\":\"ID\"},\"right\":{\"expression\":\"calmonth\",\"name\":\"d0\",\"verbose\":\"月份\"}}}],\"appID\":\"1696\",\"appType\":\"slice\"}";

        System.out.println(jsonStr);
        SimpleSlice slice = new SimpleJSON2Slice().format(jsonStr);

        System.out.println(slice.buildBasicSQL());
        System.out.println(slice.buildCountSQL());
    }
}
