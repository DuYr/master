package com.school.master.common.api;

/**
 * @FileName: ResultMessage
 * @Author: LeeDream
 * @Date: 2020/9/6 18:01
 * @Description: 封装结果集
 * @Version 1.0.0
 */
public enum ResultStatu implements IErrorCode {
    /**
     * 状态码
     */
    SUCCESS(200, "操作成功"),
    FAILED(500, "操作失败"),
    VALIDATE_FAILED(404, "参数检验失败"),
    UNAUTHORIZED(401, "暂未登录或token已经过期"),
    FORBIDDEN(403, "没有相关权限");
    private long code;
    private String message;

    private ResultStatu(long code, String message) {
        this.code = code;
        this.message = message;
    }

    @Override
    public long getCode() {
        return this.code;
    }

    @Override
    public String getMessage() {
        return message;
    }
}
