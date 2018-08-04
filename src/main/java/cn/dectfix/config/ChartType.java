package cn.dectfix.config;

public enum ChartType {
    LINE("line",1),
    PIE("pie",1),
    BAR("bar",1),
    TABLE("table",100),
    BIG_NUMBER("big_number",1),
    PURE_NUMBER("pure_number",0),
    RADAR("radar",1),
    GAUGE("gauge",0);

    //图表名
    private String name;
    /**
     *目标维度数
     * 100 对应n
     */
    private int dimension;

    ChartType(String name,int dimension) {
        this.name = name;
        this.dimension = dimension;
    }

    public static int getDimension(String name) {
        for (ChartType ct : ChartType.values()) {
            if (ct.getName().equals(name)) {
                return ct.dimension;
            }
        }
        return 101;
    }

    public String getName() {
        return name;
    }
}
