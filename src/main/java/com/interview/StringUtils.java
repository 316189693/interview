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

    public static int compareString(String str1, String str2) {
        return StringUtils.trimStr(str2).compareTo(StringUtils.trimStr(str1));
    }
}
