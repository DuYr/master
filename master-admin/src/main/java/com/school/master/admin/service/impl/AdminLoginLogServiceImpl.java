package com.school.master.admin.service.impl;

import com.github.pagehelper.PageHelper;
import com.school.master.admin.dao.AdminLoginLogDao;
import com.school.master.admin.dto.AdminLoginLog;
import com.school.master.admin.service.AdminLoginLogService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service("adminLoginLogService")
public class AdminLoginLogServiceImpl implements AdminLoginLogService {
    @Autowired
    private AdminLoginLogDao adminLoginLogDao;

    @Override
    public void insert(AdminLoginLog adminLoginLog) {
        adminLoginLogDao.insert(adminLoginLog);
    }

    @Override
    public List<AdminLoginLog> getLogsByCriteria(Integer pageSize, Integer pageNum) {
        PageHelper.startPage(pageNum, pageSize);
        List<AdminLoginLog> adminLoginLogList = adminLoginLogDao.queryAll();
        return adminLoginLogList;
    }
}
