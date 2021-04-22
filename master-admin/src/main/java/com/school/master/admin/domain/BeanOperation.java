package com.school.master.admin.domain;

import com.school.master.admin.dto.AdminLoginLog;
import com.school.master.admin.dto.Student;
import com.school.master.admin.dto.UmsAdmin;
import com.school.master.admin.dto.UpdateStudentParam;
import org.springframework.beans.BeanUtils;
import org.springframework.security.core.userdetails.UserDetails;

/**
 * @FileName: AdminOperation
 * @Author: LeeDream
 * @Date: 2020/12/16 16:53
 * @Description: dto层对象转换
 * @Version 1.0.0
 */
public class BeanOperation {
    private static final String FAIL = "fail";
    private static final String SUCCESS = "success";

    public static UmsAdmin createNewPasswordAdmin(Integer id, String username, String newPassword) {
        return new UmsAdmin(id, username, newPassword, null, null, null, null, null);
    }


    public static AdminLoginLog beanCopyInLog(UserDetails userDetails) {
        boolean enabled = userDetails.isEnabled();
        AdminLoginLog adminLoginLog = new AdminLoginLog();
        adminLoginLog.setUsername(userDetails.getUsername());
        adminLoginLog.setPassword(userDetails.getPassword());
        if (enabled) {
            adminLoginLog.setResult(SUCCESS);
        } else {
            adminLoginLog.setResult(FAIL);
        }
        return adminLoginLog;
    }


    public static <T> T beanCopy(Object source,T target) {
        BeanUtils.copyProperties(source, target);
        return target;
    }
}
