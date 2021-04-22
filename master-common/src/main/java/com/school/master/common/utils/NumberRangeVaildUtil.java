package com.school.master.common.utils;

import java.util.regex.Pattern;
/**
 * @FileName: NumberRangeVaild
 * @Author: LeeDream
 * @Date: 2021/1/22 16:48
 * @Description:
 * @Version 1.0.0
 */
public class NumberRangeVaildUtil {
    private static boolean isnum(String s, int len) {
        Pattern p = Pattern.compile("^[0-9]{1," + len + "}$");
        return p.matcher(s).matches();
    }

    public static boolean input(String s, int n, int m) {
        int len = 0;
        for (int i = m; i != 0; i /= 10) {
            len++;
        }
        if (isnum(s, len) && n <= Integer.parseInt(s) && Integer.parseInt(s) <= m) {
            return true;

        } else {
            return false;
        }
    }
}