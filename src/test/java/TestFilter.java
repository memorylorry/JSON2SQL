import cn.dectfix.converter.impl.SimpleJSON2Slice;
import cn.dectfix.type.Slice;
import cn.dectfix.type.exception.SliceFormatNotSupportedException;
import org.junit.Test;

//database字段暂时未使用，不做测试
public class TestFilter {

    @Test
    public void testFilterIs0Len() throws SliceFormatNotSupportedException {
        String jsonStr = "{\"database\":\"rpt\",\"table\":\"rpt_kpi_sale_shop_day\",\"dimension\":[{\"name\":\"dateMon\",\"verbose\":\"月份\",\"expression\":\"DATE_FORMAT(statistic_date,'%Y-%m')\"}],\"metric\":[{\"name\":\"ThisYear\",\"verbose\":\"当年总和\",\"expression\":\"SUM(index_value)\"},{\"name\":\"LastYear\",\"verbose\":\"上年总和\",\"expression\":\"SUM(last_year_value)\"}],\"filter\":[],\"order\":[{\"name\":\"ThisYear\",\"verbose\":\"当年总和\",\"expression\":\"SUM(index_value)\",\"type\":1,\"sql\":\"#{value} asc\"},{\"name\":\"LastYear\",\"verbose\":\"上年总和\",\"expression\":\"SUM(last_year_value)\",\"type\":1,\"sql\":\"#{value} desc\"}],\"limit\":\"1000\",\"title\":\"报表名称\",\"type\":\"line\"}";

        Slice slice = new SimpleJSON2Slice().format(jsonStr);

        System.out.println(slice.toString());

    }
}
