package com.school.master.admin.service;

import com.school.master.admin.dto.AdminLoginLog;

import java.util.List;

public interface AdminLoginLogService {
    void insert(AdminLoginLog adminLoginLog);

    List<AdminLoginLog> getLogsByCriteria(Integer pageSize, Integer pageNum);
}
