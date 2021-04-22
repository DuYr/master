package com.school.master.common.api;

import java.io.Serializable;

/**
 * @FileName: CommonResult
 * @Author: LeeDream
 * @Date: 2020/10/22 17:31
 * @Description: 获取公共结果
 * @Version 1.0.0
 */
public class CommonResult<T> implements Serializable {
    private static final long serialVersionUID = 1793736023685808437L;
    private Long code;
    private String message;
    private T data;

    protected CommonResult() {
    }

    protected CommonResult(long code, String message, T data) {
        this.code = code;
        this.message = message;
        this.data = data;
    }

    protected CommonResult(long code, String message) {
        this.code = code;
        this.message = message;
    }

    protected CommonResult(long code) {
        this.code = code;
    }

    protected CommonResult(String message) {
        this.message = message;
    }

    /**
     * 成功返回结果
     */

    public static <T> CommonResult<T> success(T data) {
        ResultStatu success = ResultStatu.SUCCESS;
        return new CommonResult<T>(success.getCode(), success.getMessage(), data);
    }

    public static <T> CommonResult<T> success(String message, T data) {
        ResultStatu success = ResultStatu.SUCCESS;
        return new CommonResult<T>(success.getCode(), message, data);
    }

    public static <T> CommonResult<T> success(String message) {
        ResultStatu success = ResultStatu.SUCCESS;
        return new CommonResult<T>(success.getCode(), message, null);
    }

    /**
     * 失败
     */

    public static <T> CommonResult<T> failed(IErrorCode iErrorCode) {
        return new CommonResult<>(iErrorCode.getCode(), iErrorCode.getMessage(), null);
    }


    public static <T> CommonResult<T> failed(IErrorCode iErrorCode, String message) {
        return new CommonResult<>(iErrorCode.getCode(), message);
    }

    /**
     * 操作失败结果
     *
     * @param message
     * @param <T>
     * @return
     */

    public static <T> CommonResult<T> failed(String message) {
        return new CommonResult<>(ResultStatu.FAILED.getCode(), message);
    }

    /**
     * 参数验证异常
     *
     * @param message
     * @param <T>
     * @return
     */

    public static <T> CommonResult<T> validateFailed(T data) {
        return new CommonResult<>(ResultStatu.VALIDATE_FAILED.getCode(), ResultStatu.VALIDATE_FAILED.getMessage(), data);
    }

    /**
     * 未登录返回结果
     */
    public static <T> CommonResult<T> unauthorized(T data) {
        return new CommonResult<T>(ResultStatu.UNAUTHORIZED.getCode(), ResultStatu.UNAUTHORIZED.getMessage(), data);
    }

    /**
     * 未授权返回结果
     */
    public static <T> CommonResult<T> forbidden(T data) {
        return new CommonResult<T>(ResultStatu.FORBIDDEN.getCode(), ResultStatu.FORBIDDEN.getMessage(), data);
    }


    public long getCode() {
        return code;
    }

    public void setCode(long code) {
        this.code = code;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }
}
