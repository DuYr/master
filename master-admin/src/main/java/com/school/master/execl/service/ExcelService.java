package com.school.master.execl.service;


import com.school.master.admin.dto.AbsentDetails;
import com.school.master.admin.dto.Student;
import com.school.master.execl.column.AbsenceColumn;
import org.apache.poi.ss.formula.functions.T;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletResponse;
import java.util.Date;
import java.util.List;


public interface ExcelService {
    /**
     * 按条件查询需要导出所有学生缺勤数据
     *
     * @param response
     * @param id
     * @param start
     * @param end
     * @return
     */

    void exportExcel(HttpServletResponse response, Integer id, Date start, Date end);

    boolean uploadExcel(MultipartFile file);

     List<AbsenceColumn> getExportResult(List<AbsentDetails> detailsList);

    String fileGiveName(List<AbsenceColumn> columnList, String roomName,String studentName);

    boolean fileSuffixLegal(String fileName);

    long dayDifference(Date start, Date end);

}
