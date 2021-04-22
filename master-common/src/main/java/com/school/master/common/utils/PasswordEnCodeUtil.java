package com.school.master.common.utils;

import cn.hutool.core.util.ReUtil;
import cn.hutool.crypto.digest.MD5;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

/**
 * @FileName: PasswordEnCode
 * @Author: LeeDream
 * @Date: 2020/10/25 13:58
 * @Description: 密码校验
 * @Version 1.0.0
 */
public class PasswordEnCodeUtil {

    @Value("${password.reg}")
    private String reg;
    @Autowired
    private MD5 md5;

    public boolean matches(String pass, String newPass) {
        String passwordMd5 = passwordMd5Handle(newPass);
        return pass.equals(passwordMd5);
    }

    public boolean isLegal(String newPass) {
        return ReUtil.contains("^[\\w_]{6,18}$", newPass);
    }

    public String passwordMd5Handle(String password) {
        String passwordMd5 = md5.digestHex(password);
        return passwordMd5;
    }

    public MD5 getMd5() {
        return md5;
    }

}
