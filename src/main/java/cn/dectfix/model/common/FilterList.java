package cn.dectfix.model.common;

import java.util.ArrayList;
import java.util.List;

public class FilterList {

    private List<Filter> filters = new ArrayList<>();

    private boolean isAndNotOr = true;

    public void addFilter(Filter filter){
        filters.add(filter);
    }

    public void setFilters(List<Filter> filters){
        this.filters = filters;
    }

    @Override
    public String toString() {
        String res = "";
        String filterMethod = isAndNotOr?"AND":"OR";

        for(Filter filter:filters){
            res = res + " "
                    +filterMethod +" "
                    +filter.toString();
        }

        if(res.length()>0) {
            int cutPos = isAndNotOr?4:3;
            res = res.substring(cutPos,res.length());
        }

        return res;
    }
}
