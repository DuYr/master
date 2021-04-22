package com.school.master.execl.factory;

import com.school.master.common.exception.Asserts;
import com.school.master.execl.service.ExcelService;
import com.school.master.execl.service.impl.SchoolClassExcelServiceImpl;
import com.school.master.execl.service.impl.StudentExcelServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ExcelUmitFactory {
    @Autowired
    private StudentExcelServiceImpl studentExcelService;
    @Autowired
    private SchoolClassExcelServiceImpl schoolClassExcelService;
    private final String STUDENT = "student", CLASS = "class";

    public ExcelService getExeclService(String umit) {
        if (STUDENT.equals(umit)) {
            return studentExcelService;
        } else if (CLASS.equals(umit)) {
            return schoolClassExcelService;
        }
        Asserts.fail("请求错误路径");
        return null;
    }
}
