package com.interview;

/**
 * Created By willz
 * Date : 2020/2/19
 * Time: 17:55
 */
public class StringUtils {
    public static String trimStr(String str) {
        if (null == str || str.trim().length() <= 0) {
            return "";
        }
        return str.trim();
    }

    public static int compare(String str1, String str2) {
        return trimStr(str2).compareTo(trimStr(str1));
    }
}
