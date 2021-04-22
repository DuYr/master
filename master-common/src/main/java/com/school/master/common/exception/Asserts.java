package com.school.master.common.exception;


import com.school.master.common.api.IErrorCode;

/**
 * @FileName: Asserts
 * @Author: LeeDream
 * @Date: 2020/10/25 13:55
 * @Description: 错误断言
 * @Version 1.0.0
 */
public class Asserts {
    public static void fail(String msg) {
        throw new ApiException(msg);
    }

    public static void fail(IErrorCode iErrorCode,String msg) {
        throw new ApiException(iErrorCode,msg);
    }


}
