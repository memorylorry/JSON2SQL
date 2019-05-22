
import com.github.memorylorry.converter.impl.SimpleJSON2Slice;
import com.github.memorylorry.type.SimpleSlice;
import com.github.memorylorry.type.exception.SliceFormatNotSupportedException;
import org.junit.Test;

public class TestSimpleJSON2Slice {

    @Test
    public void fullTest() throws SliceFormatNotSupportedException, InstantiationException, IllegalAccessException {

        String jsonStr = "{\"database\":\"rpt\",\"table\":\"sys_user\",\"dimension\":[{\"name\":\"create_time_day\",\"verbose\":\"创建时间(天)\",\"expression\":\"DATE_FORMAT(create_time,'%Y-%m-%d')\",\"type\":\"1\"}],\"metric\":[{\"name\":\"user_number\",\"verbose\":\"用户数\",\"expression\":\"count(DISTINCT user_code)\"}],\"filter\":[{\"name\":\"create_time_day\",\"verbose\":\"创建时间(天)\",\"expression\":\"DATE_FORMAT(create_time,'%Y-%m-%d')\",\"operation\":\"in\",\"type\":0,\"option\":\"['2018-03-22']\",\"optionText\":\"11,3,4\"},{\"name\":\"user_number\",\"verbose\":\"用户数\",\"expression\":\"count(DISTINCT user_code)\",\"operation\":\"in\",\"type\":1,\"option\":[\"1\",\"2\"],\"optionText\":\"11,2\"}],\"order\":[{\"name\":\"create_time_day\",\"verbose\":\"创建时间(天)\",\"expression\":\"DATE_FORMAT(create_time,'%Y-%m-%d')\",\"operation\":\"asc\"}],\"limit\":\"1000\",\"title\":\"用户创建时间分析\",\"type\":\"table\",\"type_val\":2}";

        System.out.println(jsonStr);
        SimpleSlice slice = new SimpleJSON2Slice().format(jsonStr);

        System.out.println(slice.buildBasicSQL());
        System.out.println(slice.buildCountSQL());
    }
}
