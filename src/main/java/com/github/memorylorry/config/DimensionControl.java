package com.github.memorylorry.config;

public class DimensionControl {
    /**
     * These variables store all state that
     * Dimensions will or won't be contact
     *
     * 这里使用一个两位二进制数，存储维度的4个状态。
     * 存储形式：[IS_EXSIT][IS_CONCAT]
     * 00 - 维度不存在
     * 10 - 维度存在，产生SQL时不做CONCAT
     * 11 - 维度存在，产生SQL时做CONCAT
     */
    public static int DIMENSIN_NOT_EXSIT = 0;
    public static int DIMENSIN_NOT_CONCAT = 2;
    public static int DIMENSIN_CONCAT = 3;

}
