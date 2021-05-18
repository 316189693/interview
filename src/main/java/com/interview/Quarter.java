package com.interview;

import java.util.HashMap;
import java.util.Map;

/**
 * Created By willz
 * Date : 2020/7/17
 * Time: 10:55
 */
public enum Quarter {
    INSTANCE;
    private static final Map<Integer, Integer> monthQuarterMap = new HashMap<>();

    static {
        monthQuarterMap.put(1, 1);
        monthQuarterMap.put(2, 1);
        monthQuarterMap.put(3, 1);
        monthQuarterMap.put(4, 2);
        monthQuarterMap.put(5, 2);
        monthQuarterMap.put(6, 2);
        monthQuarterMap.put(7, 3);
        monthQuarterMap.put(8, 3);
        monthQuarterMap.put(9, 3);
        monthQuarterMap.put(10, 4);
        monthQuarterMap.put(11, 4);
        monthQuarterMap.put(12, 4);
    }

    public int getQuarter(int month) {
        return monthQuarterMap.get(month);
    }

}
