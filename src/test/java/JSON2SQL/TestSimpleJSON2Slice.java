package JSON2SQL;

import com.github.memorylorry.JSON2SQL.SQLGenerator;
import com.github.memorylorry.JSON2SQL.exception.JSON2SQLParseException;
import org.junit.Test;

public class TestSimpleJSON2Slice {
    @Test
    public void fullTest() throws JSON2SQLParseException {
        SQLGenerator sqlGenerator = SQLGenerator.build();
        String jsonStr = "{\"database\":\"dbName\",\"table\":\"tableName\",\"dimension\":[{\"name\":\"create_time_day\",\"verbose\":\"创建时间(天)\",\"expression\":\"DATE_FORMAT(create_time,'%Y-%m-%d')\",\"type\":\"1\"}],\"metric\":[{\"name\":\"user_number\",\"verbose\":\"用户数\",\"expression\":\"count(DISTINCT user_code)\"}],\"filter\":[{\"name\":\"create_time_day\",\"verbose\":\"创建时间(天)\",\"expression\":\"DATE_FORMAT(create_time,'%Y-%m-%d')\",\"operation\":\"in\",\"type\":0,\"option\":\"['2018-03-22']\",\"optionText\":\"11,3,4\"},{\"name\":\"user_number\",\"verbose\":\"用户数\",\"expression\":\"count(DISTINCT user_code)\",\"operation\":\"in\",\"type\":1,\"option\":[\"1\",\"2\"],\"optionText\":\"11,2\"}],\"order\":[{\"name\":\"create_time_day\",\"verbose\":\"创建时间(天)\",\"expression\":\"DATE_FORMAT(create_time,'%Y-%m-%d')\",\"operation\":\"asc\"}],\"limit\":\"1000\",\"title\":\"用户创建日期分析\",\"type\":\"table\",\"type_val\":2}";
        System.out.println(sqlGenerator.parse(jsonStr));
    }
}
