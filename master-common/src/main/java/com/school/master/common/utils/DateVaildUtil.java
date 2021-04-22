package com.school.master.common.utils;

import cn.hutool.core.date.DateUtil;
import com.fasterxml.jackson.core.SerializableString;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * @FileName: DateVaildUtil
 * @Author: LeeDream
 * @Date: 2021/1/22 16:18
 * @Description: 日期判断处理
 * @Version 1.0.0
 */

public class DateVaildUtil {
    private static DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");

    public static boolean isValidDate(String format, String date) {
        SimpleDateFormat dateFormat = new SimpleDateFormat(format);
        dateFormat.setLenient(false);
        try {
            dateFormat.parse(date);
        } catch (ParseException e) {
            return false;
        }
        return true;
    }

    public static boolean isVaildYear(Integer year) {
        return (String.valueOf(year).length() == 4) && (year > 2000);
    }

    public static boolean isVaildMonth(Integer month) {
        return (month > 0 && month < 13);
    }

    public static boolean isVaildDay(Integer day) {
        return (day > 0 && day < 32);
    }

    public static String[] getNowSpecificDate() {
        String[] dateSplit = DateUtil.today().split("-");
        return dateSplit;
    }

    public static Date formatDate(String date) {
        try {
            return dateFormat.parse(date);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return null;
    }

    public static String formatString(Date date) {
        if (date == null) {
            return "";
        }
        return dateFormat.format(date);
    }

}
