package com.school.master.common.exception;

import com.school.master.common.api.CommonResult;
import io.lettuce.core.RedisCommandTimeoutException;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.io.InputStream;
import java.net.URISyntaxException;

/**
 * @FileName: GlobalExceptionHandle
 * @Author: LeeDream
 * @Date: 2020/10/26 8:55
 * @Description: 全局异常处理
 * @Version 1.0.0
 */
@RestControllerAdvice
public class GlobalExceptionHandler {
    /**
     * 业务异常
     *
     * @param e
     * @return
     */
    @ExceptionHandler(value = ApiException.class)
    public CommonResult handle(ApiException e) {
        if (e.getiErrorCode() != null) {
            return CommonResult.failed(e.getiErrorCode(), e.getMsg());
        }
        return CommonResult.failed(e.getMessage());
    }


    /**
     * 逻辑异常
     *
     * @param e
     * @return
     */

    @ExceptionHandler(value = MethodArgumentNotValidException.class)
    public CommonResult handle(MethodArgumentNotValidException e) {
        BindingResult bindingResult = e.getBindingResult();
        //结合所有异常
        String message = "";
        if (bindingResult.hasErrors()) {
            FieldError fieldError = bindingResult.getFieldError();
            if (fieldError != null) {
                //参数名+异常信息
                message = (fieldError.getField() + fieldError.getDefaultMessage());
            }
        }
        return CommonResult.validateFailed(message);
    }

    @ExceptionHandler(RedisCommandTimeoutException.class)
    public CommonResult handle(RedisCommandTimeoutException e) {
        return CommonResult.failed("响应超时,请重试");
    }


    @ExceptionHandler(URISyntaxException.class)
    public CommonResult handle(URISyntaxException e) {
        return CommonResult.forbidden("抱歉，您没有权限访问");
    }

    @ExceptionHandler(HttpMessageNotReadableException.class)
    public CommonResult handle(HttpMessageNotReadableException e) {
        return CommonResult.validateFailed("非法参数");
    }
}
