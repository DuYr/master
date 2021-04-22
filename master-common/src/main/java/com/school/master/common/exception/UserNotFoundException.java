package com.school.master.common.exception;


import com.school.master.common.api.IErrorCode;

/**
 * @FileName: UserNotFoundException
 * @Author: LeeDream
 * @Date: 2020/10/25 13:44
 * @Description: 用户不存在
 * @Version 1.0.0
 */
public class UserNotFoundException extends ApiException {
    public UserNotFoundException(IErrorCode iErrorCode) {
        super(iErrorCode);
    }

    public UserNotFoundException(String msg) {
        super(msg);
    }

}
