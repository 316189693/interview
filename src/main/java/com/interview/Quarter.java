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
    private static final Map<Integer, Integer> monthQuoarterMap = new HashMap<>();

    static {
        monthQuoarterMap.put(1, 1);
        monthQuoarterMap.put(2, 1);
        monthQuoarterMap.put(3, 1);
        monthQuoarterMap.put(4, 2);
        monthQuoarterMap.put(5, 2);
        monthQuoarterMap.put(6, 2);
        monthQuoarterMap.put(7, 3);
        monthQuoarterMap.put(8, 3);
        monthQuoarterMap.put(9, 3);
        monthQuoarterMap.put(10, 4);
        monthQuoarterMap.put(11, 4);
        monthQuoarterMap.put(12, 4);
    }

    public int getQuarter(int month) {
        return monthQuoarterMap.get(month);
    }

}
