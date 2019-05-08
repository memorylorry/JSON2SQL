import com.github.memorylorry.converter.impl.SimpleJSON2Slice;
import com.github.memorylorry.type.SimpleSlice;
import com.github.memorylorry.type.exception.SliceFormatNotSupportedException;

public class TestPreviewSlice {
    public static void main(String[] args) throws SliceFormatNotSupportedException, InstantiationException, IllegalAccessException {
        String jsonStr = "{\"title\":\"preview\",\"database\":\"dm\",\"table\":\"select * from dm.dm_total_user_profile limit 100\",\"type\":\"not_paginate_tablear\",\"type_val\":2,\"dimension\":[{\"expression\":\"age\",\"name\":\"d0\",\"verbose\":\"年龄\"}],\"metric\":[{\"expression\":\"count(member_no)\",\"name\":\"m0\",\"verbose\":\"人数\"}],\"filter\":[],\"order\":[{\"expression\":\"age\",\"name\":\"c4955\",\"type\":0,\"operation\":\"asc\",\"verbose\":\"年龄\",\"sql\":\"\"}],\"limit\":\"100\"}";

        System.out.println(jsonStr);
        SimpleSlice slice = new SimpleJSON2Slice().format(jsonStr);

        System.out.println(slice.buildBasicSQL());
        System.out.println(slice.buildCountSQL());
    }
}
