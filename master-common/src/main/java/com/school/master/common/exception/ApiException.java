package com.school.master.common.exception;


import com.school.master.common.api.IErrorCode;

/**
 * @FileName: ApiException
 * @Author: LeeDream
 * @Date: 2020/10/25 14:17
 * @Description: 业务api异常
 * @Version 1.0.0
 */
public class ApiException extends RuntimeException {
    private IErrorCode iErrorCode;
    private String msg;
    public ApiException(IErrorCode iErrorCode) {
        super(iErrorCode.getMessage());
        this.iErrorCode = iErrorCode;
    }

    public ApiException(IErrorCode iErrorCode, String msg) {
        super(msg);
        this.iErrorCode=iErrorCode;
        this.msg=msg;
    }


    /**
     * 操作异常
     *
     * @param message
     */

    public ApiException(String message) {
        super(message);
    }

    public IErrorCode getiErrorCode() {
        return iErrorCode;
    }

    public void setiErrorCode(IErrorCode iErrorCode) {
        this.iErrorCode = iErrorCode;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }
}
