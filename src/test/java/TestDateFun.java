import com.github.memorylorry.converter.impl.SimpleJSON2Slice;
import com.github.memorylorry.type.SimpleSlice;
import com.github.memorylorry.type.exception.SliceFormatNotSupportedException;

public class TestDateFun {
    public static void main(String[] args) throws InstantiationException, IllegalAccessException, SliceFormatNotSupportedException {
        String jsonStr = "{\"title\":\"a\",\"database\":\"dm\",\"table\":\"dm_total_user_profile\",\"type\":\"bar\",\"type_val\":3,\"dimension\":[{\"expression\":\"age\",\"name\":\"d0\",\"verbose\":\"年龄\"}],\"metric\":[{\"expression\":\"count(member_no)\",\"name\":\"m0\",\"verbose\":\"人数\"}],\"filter\":[{\"expression\":\"age\",\"name\":\"c4955\",\"type\":0,\"optionText\":\"\",\"operation\":\"lg_date_fun\",\"verbose\":\"年龄\",\"sql\":\"\",\"option\":\"last_week\"}],\"order\":[{\"expression\":\"age\",\"name\":\"c4955\",\"type\":0,\"operation\":\"asc\",\"verbose\":\"年龄\",\"sql\":\"\"}],\"limit\":\"100\"}";
        System.out.println(jsonStr);
        SimpleSlice slice = new SimpleJSON2Slice().format(jsonStr);

        System.out.println(slice.buildBasicSQL());
        System.out.println(slice.buildCountSQL());
    }
}
