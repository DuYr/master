package com.school.master.execl.service.impl;

import cn.hutool.core.collection.CollectionUtil;
import com.school.master.admin.dto.AbsentDetails;
import com.school.master.admin.dto.Student;
import com.school.master.common.api.ResultStatu;
import com.school.master.common.exception.Asserts;
import com.school.master.common.utils.DateVaildUtil;
import com.school.master.common.utils.ExcelUtils;
import com.school.master.execl.column.AbsenceColumn;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletResponse;
import java.util.Date;
import java.util.List;

@Service("studentExcelService")
public class StudentExcelServiceImpl extends ExcelServiceImpl {

    @Override
    public void exportExcel(HttpServletResponse response, Integer sid, Date start, Date end) {
        Student student = studentService.studentsNotExist(sid);
        String formatStart = DateVaildUtil.formatString(start);
        String formatEnd = DateVaildUtil.formatString(end);
        long day = super.dayDifference(start, end);
        List<AbsentDetails> dataList = super.absentDetailsService.dateRangeStudent(sid,
                formatStart, formatEnd, (int) day + 1, 0);
        if (CollectionUtil.isEmpty(dataList)) {
            Asserts.fail(ResultStatu.SUCCESS, "没有缺勤记录,不需要导出");
        }
        List<AbsenceColumn> exportResult = super.getExportResult(dataList);
        String roomName = schoolClassService.getClassRoomName(student.getPid());
        String fileName = fileGiveName(exportResult, roomName, student.getName());
        ExcelUtils.writeExcel(response, exportResult, AbsenceColumn.class, fileName);
    }


    @Override
    public boolean uploadExcel(MultipartFile file) {
        return false;
    }
}

