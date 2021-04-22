package com.school.master.common.utils;

/**
 * @FileName: BaseVaildUtil
 * @Author: LeeDream
 * @Date: 2020/12/28 14:33
 * @Description:
 * @Version 1.0.0
 */
public class BaseVaildUtil<T> {

    public static <T> boolean equalsPlus(T vaild, T... target) {

        if (target.length != 0) {
            for (T s : target) {
                if (vaild.getClass() != s.getClass()) {
                    return false;
                }
                if (s.equals(vaild)) {
                    return true;
                }
            }
        }
        return false;
    }
}
