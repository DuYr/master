package com.school.master.admin.aspect.inspection;

import cn.hutool.core.collection.CollectionUtil;
import com.school.master.admin.dto.AbsentDetails;
import com.school.master.admin.dto.CreateAbsentParam;
import com.school.master.admin.dto.DeleteAbsentParam;
import com.school.master.admin.service.AbsentDetailsService;
import com.school.master.admin.service.ClassAbsentService;
import com.school.master.admin.service.StudentService;
import com.school.master.common.utils.DateVaildUtil;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

import java.util.List;

@Aspect
@Component
@Order(2)
public class FullAutomaticDetectionAspect {
    @Autowired
    private ClassAbsentService classAbsentService;
    @Autowired
    private AbsentDetailsService absentDetailsService;
    @Autowired
    private StudentService studentService;

    @AfterReturning(value = "execution( * com.school.master.admin.service.impl.AbsentDetailsServiceImpl.addAbsent(..)) || execution(* com.school.master.admin.service.impl.AbsentDetailsServiceImpl.deleteAbsent(..))", returning = "returnValue")
    public void updateClassAbsent(JoinPoint joinPoint, Object returnValue) {
        if ((Integer) returnValue > 0) {
            Object[] args = joinPoint.getArgs();
            Object[] sidAndDate = getSidAndDate(args[0]);
            if (sidAndDate != null) {
                Integer sid = (Integer) sidAndDate[0];
                String date = (String) sidAndDate[1];
                updateClassFull(sid, date);
            }
        }
    }

    /**
     * 获取sid和date
     *
     * @param args
     * @return
     */

    private Object[] getSidAndDate(Object args) {
        Object[] objects = null;
        if (args instanceof DeleteAbsentParam) {
            DeleteAbsentParam deleteParam = (DeleteAbsentParam) args;
            objects = new Object[]{deleteParam.getSid(), DateVaildUtil.formatString(deleteParam.getDate())};
        }
        if (args instanceof CreateAbsentParam) {
            CreateAbsentParam createParam = (CreateAbsentParam) args;
            objects = new Object[]{createParam.getSid(), DateVaildUtil.formatString(createParam.getDate())};
        }
        return objects;
    }

    public void updateClassFull(Integer sid, String date) {
        int length = sid.toString().length();
        String pid = sid.toString().substring(0, length - 2);
        List<AbsentDetails> absentDetailsList = absentDetailsService.queryByLikeSidAndDate(Integer.valueOf(pid), date, 1, 0);
        if (!CollectionUtil.isEmpty(absentDetailsList)) {
            classAbsentService.addOrChange(Integer.valueOf(pid), date, false);
            return;
        }
        classAbsentService.addOrChange(Integer.valueOf(pid), date, true);
    }
}
