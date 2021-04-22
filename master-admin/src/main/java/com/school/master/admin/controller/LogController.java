package com.school.master.admin.controller;

import com.school.master.common.api.CommonResult;
import com.school.master.common.api.PageResult;
import com.school.master.admin.dto.AdminLoginLog;
import com.school.master.admin.dto.LogAbsent;
import com.school.master.admin.service.AdminLoginLogService;
import com.school.master.admin.service.LogAbsentService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.constraints.NotNull;
import java.util.List;

@Api(description = "日志管理")
@RestController
@RequestMapping("/log")
public class LogController {
    @Autowired
    private AdminLoginLogService adminLoginLogService;
    @Autowired
    private LogAbsentService logAbsentService;

    @ApiOperation("管理员登录日志")
    @GetMapping("/admin/{pageSize}/{pageNum}")
    public CommonResult<PageResult<AdminLoginLog>> getAdminLogs(@PathVariable @NotNull Integer pageSize, @PathVariable @NotNull Integer pageNum) {
        List<AdminLoginLog> adminLoginLogList = adminLoginLogService.getLogsByCriteria(pageSize, pageNum);
        return CommonResult.success(PageResult.restPage(adminLoginLogList));
    }

    @ApiOperation("管理员考勤操作日志")
    @GetMapping("/absent/{pageSize}/{pageNum}")
    public CommonResult<PageResult<LogAbsent>> getAbsentLogs(@PathVariable @NotNull Integer pageSize, @PathVariable @NotNull Integer pageNum) {
        List<LogAbsent> absentLogList = logAbsentService.getLogsByCriteria(pageSize, pageNum);
        return CommonResult.success(PageResult.restPage(absentLogList));
    }
}
