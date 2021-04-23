package com.school.master.admin.task;

import cn.hutool.core.collection.CollectionUtil;
import cn.hutool.core.date.DateUtil;
import com.school.master.admin.service.AbsentDetailsService;
import com.school.master.admin.service.ClassAbsentService;
import com.school.master.admin.service.SchoolClassService;
import com.school.master.admin.service.StudentService;
import com.school.master.common.utils.DateVaildUtil;
import com.school.master.model.AbsentDetails;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

/**
 * 每晚24点记录今日班级考勤全到情况
 */

@Service
public class FullAttendanceTask {
    @Autowired
    private ClassAbsentService classAbsentService;
    @Autowired
    private AbsentDetailsService absentDetailsService;
    @Autowired
    private SchoolClassService schoolClassService;
    @Autowired
    private StudentService studentService;

    @Scheduled(cron = "0 59 23 * * ? ")
    public void fullRecording() {
        Date today = DateVaildUtil.formatDate(DateUtil.today());
        allTo(today);
    }

    public void allTo(Date today) {
        String formatToday = DateVaildUtil.formatString(today);
        schoolClassService.getAll()
                .forEach(schoolClass -> {
                            Integer pid = schoolClass.getPid();
                            studentService.getStudentsByPid(pid, 100, 0).forEach(student -> {
                                        List<AbsentDetails> absentDetailsList = absentDetailsService.dateRangeStudent(student.getSid(), formatToday, formatToday, 1, 0);
                                        if (!CollectionUtil.isEmpty(absentDetailsList)) {
                                            classAbsentService.addOrChange(pid, formatToday, false);
                                            return;
                                        }
                                    }
                            );
                            classAbsentService.addOrChange(pid, formatToday, true);
                        }
                );
    }


}
