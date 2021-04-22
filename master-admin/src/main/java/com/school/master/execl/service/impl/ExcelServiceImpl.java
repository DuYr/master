package com.school.master.execl.service.impl;

import cn.hutool.core.date.DateUnit;
import cn.hutool.core.date.DateUtil;
import com.school.master.admin.domain.BeanOperation;
import com.school.master.admin.dto.AbsentDetails;
import com.school.master.admin.dto.Student;
import com.school.master.admin.service.AbsentDetailsService;
import com.school.master.admin.service.SchoolClassService;
import com.school.master.admin.service.StudentService;
import com.school.master.common.exception.Asserts;
import com.school.master.common.utils.DateVaildUtil;
import com.school.master.execl.column.AbsenceColumn;
import com.school.master.execl.service.ExcelService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.support.AbstractApplicationContext;
import org.springframework.stereotype.Service;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;

@Service("excelService")
public abstract class ExcelServiceImpl implements ExcelService {

    @Autowired
    protected AbsentDetailsService absentDetailsService;
    @Autowired
    protected StudentService studentService;
    @Autowired
    protected SchoolClassService schoolClassService;
    @Value("${upload.path.export}")
    public String path;
    @Value("${upload.file.suffixs}")
    public String suffixs;

    @Override
    public List<AbsenceColumn> getExportResult(List<AbsentDetails> detailsList) {
        List<AbsenceColumn> resultList = new ArrayList<>();
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        AbsenceColumn temp = null;
        for (int i = 0; i < detailsList.size(); i++) {
            AbsentDetails absentDetails = detailsList.get(i);
            AbsenceColumn absenceColumn = BeanOperation.beanCopy(absentDetails, new AbsenceColumn());
            absenceColumn.setId(i + 1);
            absenceColumn.setDate(absentDetails.getDate());
            absenceColumn.setCreateDate(dateFormat.format(absentDetails.getCreateDate()));

            if (temp != null && temp.getSid().equals(absenceColumn.getSid())) {
                absenceColumn.setRoomName(temp.getRoomName());
                absenceColumn.setName(temp.getName());
            } else {
                Student student = studentService.getStudentBySid(absenceColumn.getSid());
                String roomName = schoolClassService.getClassRoomName(student.getPid());
                String name = studentService.getStudentBySid(absentDetails.getSid()).getName();
                absenceColumn.setRoomName(roomName);
                absenceColumn.setName(name);
                temp = absenceColumn;
            }
            resultList.add(absenceColumn);
        }
        return resultList;
    }

    @Override
    public String fileGiveName(List<AbsenceColumn> columnList, String roomName, String studentName) {
        String startDate = columnList.get(0).getDate();
        String endDate = columnList.get(columnList.size() - 1).getDate();
        return getTableName(startDate, endDate, roomName, studentName);

    }

    @Override
    public boolean fileSuffixLegal(String fileName) {
        String suffix = StringUtils.substringAfter(fileName, ".");
        return suffixs.contains(suffix);
    }

    @Override
    public long dayDifference(Date start, Date end) {
        if (end == null) {
            return 1;
        }
        long day = DateUtil.between(start, end, DateUnit.DAY);
        return day + 1;
    }

    private String getTableName(String start, String end, String roomName, String studentName) {
        start = start.split(" ")[0];
        end = end.split(" ")[0];
        String fileName = "";
        if (start.equals(end)) {
            if (studentName == "" || studentName == null) {
                fileName = roomName + start + "缺勤记录";
            } else {
                fileName = roomName + studentName + start + "缺勤记录";
            }
        } else {
            if (studentName == "" || studentName == null) {
                fileName = roomName + start + "至" + end + "缺勤记录";
            } else {
                fileName = roomName + studentName + start + "至" + end + "缺勤记录";
            }
        }
        return fileName;
    }

}
