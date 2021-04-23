package com.school.master.execl.service.impl;

import cn.hutool.core.collection.CollectionUtil;
import com.school.master.admin.service.AbsentDetailsService;
import com.school.master.admin.service.SchoolClassService;
import com.school.master.common.api.ResultStatu;
import com.school.master.common.exception.Asserts;
import com.school.master.common.utils.DateVaildUtil;
import com.school.master.common.utils.ExcelUtils;
import com.school.master.execl.column.AbsenceColumn;
import com.school.master.model.AbsentDetails;
import com.school.master.model.Student;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletResponse;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Service("schoolClassExcelService")
public class SchoolClassExcelServiceImpl extends ExcelServiceImpl {
    @Autowired
    private SchoolClassService schoolClassService;
    @Autowired
    private AbsentDetailsService absentDetailsService;

    @Override
    public void exportExcel(HttpServletResponse response, Integer pid, Date start, Date end) {
        long day = super.dayDifference(start, end);
        List<Student> studentList = studentService.getAllStudentByPid(pid);
        List<AbsentDetails> dataList = new ArrayList<>();
        studentList.forEach(student -> {
            List<AbsentDetails> detailsList = absentDetailsService
                    .dateRangeStudent(student.getSid(), DateVaildUtil.formatString(start)
                            , DateVaildUtil.formatString(end), (int) day, 0);
            if (!CollectionUtil.isEmpty(detailsList)) {
                dataList.addAll(detailsList);
            }
        });
        if (CollectionUtil.isEmpty(dataList)) {
            Asserts.fail(ResultStatu.SUCCESS, "没有缺勤记录,不需要导出");
        }
        String roomName = schoolClassService.getClassRoomName(pid);
        List<AbsenceColumn> exportResult = super.getExportResult(dataList);
        String fileName = fileGiveName(exportResult, roomName,null);
        ExcelUtils.writeExcel(response, exportResult, AbsenceColumn.class, fileName);
    }

    @Override
    public boolean uploadExcel(MultipartFile file) {
        return false;
    }


}

