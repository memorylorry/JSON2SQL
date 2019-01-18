
import com.github.memorylorry.converter.impl.SimpleJSON2Slice;
import com.github.memorylorry.type.SimpleSlice;
import com.github.memorylorry.type.exception.SliceFormatNotSupportedException;
import org.junit.Test;

public class TestLikeJSON2Slice {

    @Test
    public void fullTest() throws SliceFormatNotSupportedException, InstantiationException, IllegalAccessException {

        String jsonStr = "{\"database\":\"rpt\",\"table\":\"sys_user\",\"dimension\":[{\"name\":\"create_time_day\",\"verbose\":\"创建时间(天)\",\"expression\":\"DATE_FORMAT(create_time,'%Y-%m-%d')\"}],\"metric\":[{\"name\":\"user_number\",\"verbose\":\"用户数\",\"expression\":\"count(DISTINCT user_code)\"}],\"filter\":[{\"name\":\"create_time_day\",\"verbose\":\"创建时间(天)\",\"expression\":\"DATE_FORMAT(create_time,'%Y-%m-%d')\",\"operation\":\">\",\"type\":0,\"option\":\"2018-03-22\"},{\"name\":\"user_number\",\"verbose\":\"用户数\",\"expression\":\"count(DISTINCT user_code)\",\"operation\":\"like\",\"type\":1,\"option\":[\"1\",\"2\"]}],\"order\":[{\"name\":\"create_time_day\",\"verbose\":\"创建时间(天)\",\"expression\":\"DATE_FORMAT(create_time,'%Y-%m-%d')\",\"operation\":\"asc\"}],\"limit\":\"1000\",\"title\":\"用户创建时间分析\",\"type\":\"table\",\"type_val\":2}";

        SimpleSlice slice = new SimpleJSON2Slice().format(jsonStr);

        System.out.println(slice.buildBasicSQL());
        System.out.println(slice.buildCountSQL());
    }
}
