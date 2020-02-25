package com.interview;

import java.util.Comparator;

/**
 * Created By willz
 * Date : 2020/2/25
 * Time: 20:25
 */
public class ExtensionUtils {
    static <T> Comparator<T> compare() {
        return (o1, o2) -> 0;
    }
}
