package com.school.master.common.utils;

import cn.hutool.core.convert.Convert;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class MyStringUils {
    public static int findStringCount(String source, String child) {
        int count = 0;
        Pattern p = Pattern.compile(source);
        Matcher m = p.matcher(child);
        while (m.find()) {
            count++;
        }
        return count;
    }

    /**
     * 全角转半角
     *
     * @param em
     * @return
     */

    public static String convertHalfSize(String em) {
        return Convert.toDBC(em);
    }

    /**
     * 半角转全角
     *
     * @param em
     * @return
     */

    public static String convertEmQuad(String em) {
        return Convert.toSBC(em);
    }

}
