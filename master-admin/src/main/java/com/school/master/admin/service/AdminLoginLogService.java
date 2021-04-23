package com.school.master.admin.service;

import com.school.master.model.AdminLoginLog;

import java.util.List;

public interface AdminLoginLogService {
    void insert(AdminLoginLog adminLoginLog);

    List<AdminLoginLog> getLogsByCriteria(Integer pageSize, Integer pageNum);
}
