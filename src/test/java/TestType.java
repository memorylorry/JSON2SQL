import com.github.memorylorry.converter.impl.SimpleJSON2Slice;
import com.github.memorylorry.type.Slice;
import com.github.memorylorry.type.exception.SliceFormatNotSupportedException;

//database字段暂时未使用，不做测试
public class TestType {
    //@Test
    public void testTypeIsNOTInteger() throws SliceFormatNotSupportedException {
        String jsonStr = "{\"database\":32,\"table\":\"rpt_kpi_sale_shop_day\",\"dimension\":[{\"name\":\"dateMon\",\"verbose\":\"月份\",\"expression\":\"DATE_FORMAT(statistic_date,'%Y-%m')\"}],\"metric\":[{\"name\":\"ThisYear\",\"verbose\":\"当年总和\",\"expression\":\"SUM(index_value)\"},{\"name\":\"LastYear\",\"verbose\":\"上年总和\",\"expression\":\"SUM(last_year_value)\"}],\"filter\":[{\"name\":\"dateMon\",\"verbose\":\"月份\",\"expression\":\"DATE_FORMAT(statistic_date,'%Y-%m')\",\"sql\":\"#{value}>'2018-07'\",\"type\":0},{\"name\":\"ThisYear\",\"verbose\":\"当年总和\",\"expression\":\"SUM(index_value)\",\"sql\":\"#{value}>100\",\"type\":1},{\"name\":\"LastYear\",\"verbose\":\"上年总和\",\"expression\":\"SUM(last_year_value)\",\"sql\":\"#{value}>100\",\"type\":1}],\"order\":[{\"name\":\"ThisYear\",\"verbose\":\"当年总和\",\"expression\":\"SUM(index_value)\",\"type\":1,\"sql\":\"#{value} asc\"},{\"name\":\"LastYear\",\"verbose\":\"上年总和\",\"expression\":\"SUM(last_year_value)\",\"type\":1,\"sql\":\"#{value} desc\"}],\"limit\":\"1000\",\"title\":\"报表名称\",\"type\":13}";

        Slice slice = new SimpleJSON2Slice().format(jsonStr);

        System.out.println(slice.toString());
    }

    //@Before
    public void testTypeIsNotExisted() throws SliceFormatNotSupportedException {
        String jsonStr = "{\"database\":32,\"table\":\"rpt_kpi_sale_shop_day\",\"dimension\":[{\"name\":\"dateMon\",\"verbose\":\"月份\",\"expression\":\"DATE_FORMAT(statistic_date,'%Y-%m')\"}],\"metric\":[{\"name\":\"ThisYear\",\"verbose\":\"当年总和\",\"expression\":\"SUM(index_value)\"},{\"name\":\"LastYear\",\"verbose\":\"上年总和\",\"expression\":\"SUM(last_year_value)\"}],\"filter\":[{\"name\":\"dateMon\",\"verbose\":\"月份\",\"expression\":\"DATE_FORMAT(statistic_date,'%Y-%m')\",\"sql\":\"#{value}>'2018-07'\",\"type\":0},{\"name\":\"ThisYear\",\"verbose\":\"当年总和\",\"expression\":\"SUM(index_value)\",\"sql\":\"#{value}>100\",\"type\":1},{\"name\":\"LastYear\",\"verbose\":\"上年总和\",\"expression\":\"SUM(last_year_value)\",\"sql\":\"#{value}>100\",\"type\":1}],\"order\":[{\"name\":\"ThisYear\",\"verbose\":\"当年总和\",\"expression\":\"SUM(index_value)\",\"type\":1,\"sql\":\"#{value} asc\"},{\"name\":\"LastYear\",\"verbose\":\"上年总和\",\"expression\":\"SUM(last_year_value)\",\"type\":1,\"sql\":\"#{value} desc\"}],\"limit\":\"1000\",\"title\":\"报表名称\"}";

        Slice slice = new SimpleJSON2Slice().format(jsonStr);

        System.out.println(slice.toString());
    }
}
