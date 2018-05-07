
import cn.dectfix.converter.impl.SimpleJSON2Slice;
import cn.dectfix.model.Slice;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import org.junit.Test;

public class TestSimpleJSON2Slice {

    @Test
    public void fullTest(){
        String json = "{\"table\":{\"name\":\"job\",\"isSQL\":0},\"dimension\":[{\"name\":\"job_id\",\"verbose\":\"id\"},{\"name\":\"job_time\",\"verbose\":\"time\"}],\"metric\":[{\"name\":\"count(1)\",\"verbose\":\"num\"},{\"name\":\"count(2)\",\"verbose\":\"2xnum\"}],\"whereFilter\":[{\"name\":\"job_id\",\"op\":\">\",\"value\":\"90\"}],\"havingFilter\":[{\"name\":\"count(1)\",\"op\":\">=\",\"value\":\"80\"}],\"order\":[{\"name\":\"id\",\"dir\":\"asc\"},{\"name\":\"num\",\"dir\":\"asc\"}],\"limit\":{\"length\":1000}}";
        Slice slice = new SimpleJSON2Slice().format(json);

        System.out.println(slice.toString());
    }

    public static void main(String args[]){
        new TestSimpleJSON2Slice().fullTest();
    }
}
