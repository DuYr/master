package com.school.master.admin.aspect.log;

import cn.hutool.core.collection.CollectionUtil;
import com.school.master.admin.domain.LogGenerate;
import com.school.master.admin.dto.*;
import com.school.master.admin.service.AbsentDetailsService;
import com.school.master.admin.service.LogAbsentService;
import com.school.master.common.utils.DateVaildUtil;
import io.swagger.models.auth.In;
import net.logstash.logback.encoder.org.apache.commons.lang3.StringUtils;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.annotation.Order;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * @FileName: LogGenerateAspect
 * @Author: LeeDream
 * @Date: 2021/1/22 15:55
 * @Description: 日志添加切面处理
 * @Version 1.0.0
 */
@Aspect
@Component
@Order(1)
public class LogGenerateAspect {
    private final String ADD = "新增";
    private final String EDIT = "更改";
    private final String DELETE = "删除";
    @Autowired
    private LogGenerate logGenerate;
    @Autowired
    private LogAbsentService logAbsentService;
    @Autowired
    private AbsentDetailsService absentDetailsService;

    @AfterReturning(value = "execution(* com.school.master.admin.service.impl.AbsentDetailsServiceImpl.addAbsent(..))", returning = "returnValue")
    public void addLog(JoinPoint joinPoint, Object returnValue) {
        if ((Integer) returnValue <= 0) {
            return;
        }
        Object[] args = joinPoint.getArgs();
        CreateAbsentParam createParam = (CreateAbsentParam) args[0];
        Integer sid = createParam.getSid();
        String course = createParam.getCourse();
        String date = DateVaildUtil.formatString(createParam.getDate());
        String reason = createParam.getReason();
        String remarks = createParam.getRemarks();
        if (StringUtils.isEmpty(remarks)) {
            remarks = "未备注";
        }
        String behavior = ADD;
        String content = logGenerate.log( sid, course, date, reason, remarks).builder();
        insertLog(behavior, content);
    }

    @AfterReturning(value = "execution(* com.school.master.admin.service.impl.AbsentDetailsServiceImpl.update(..))", returning = "returnValue")
    public void updateLog(JoinPoint joinPoint, Object returnValue) {
        if ((Integer) returnValue <= 0) {
            return;
        }
        Object[] args = joinPoint.getArgs();
        UpdateAbsentDetailsParam update = (UpdateAbsentDetailsParam) args[0];
        Integer sid = update.getSid();
        String course = update.getCourse();
        String date = DateVaildUtil.formatString(update.getDate());
        String reason = update.getReason();
        String remarks = update.getRemarks();
        String behavior = EDIT;
        String content = logGenerate.log( sid, course, date, reason, remarks).builder();
        insertLog(behavior, content);
    }

    @Around(value = "execution(* com.school.master.admin.service.impl.AbsentDetailsServiceImpl.deleteAbsent(..))")
    public Object deleteLog(ProceedingJoinPoint joinPoint) throws Throwable {
        Object[] args = joinPoint.getArgs();
        DeleteAbsentParam deleteParam = (DeleteAbsentParam) args[0];
        String date = DateVaildUtil.formatString(deleteParam.getDate());
        List<AbsentDetails> absentDetailsList = absentDetailsService.dateRangeStudent(deleteParam.getSid(), date, date, 1, 0);
        if (CollectionUtil.isEmpty(absentDetailsList)) {
            return joinPoint.proceed();
        }
        AbsentDetails absentDetails = absentDetailsList.get(0);
        Integer count = (Integer) joinPoint.proceed();
        if (count > 0) {
            Integer sid = absentDetails.getSid();
            String course = absentDetails.getCourse();
            String formartDate = absentDetails.getDate();
            String reason = absentDetails.getReason();
            String remarks = absentDetails.getRemarks();
            String behavior = DELETE;
            String content = logGenerate.log( sid, course, formartDate, reason, remarks).builder();
            insertLog(behavior, content);
        }
        return count;
    }

    private void insertLog(String behavior, String content) {
        String username = getAccessUserName();
        LogAbsent logAbsent = new LogAbsent();
        logAbsent.setUsername(username);
        logAbsent.setBehavior(behavior);
        logAbsent.setContent(content);
        logAbsentService.insert(logAbsent);
    }

    private String getAccessUserName() {
        UserDetails userDetails = (UserDetails)
                SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        return userDetails.getUsername();
    }

}
