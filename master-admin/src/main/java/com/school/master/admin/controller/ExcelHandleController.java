package com.school.master.admin.controller;

import com.school.master.execl.factory.ExcelUmitFactory;
import com.school.master.execl.service.ExcelService;
import com.school.master.common.api.CommonResult;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletResponse;
import javax.validation.constraints.Past;
import java.util.Date;

@Api(description = "execl处理")
@RestController
@RequestMapping("/execl")
public class ExcelHandleController {
    @Autowired
    private ExcelUmitFactory excelUmitFactory;

    @GetMapping("/export/{umit}")
    @ApiImplicitParam(name = "umit", value = "student->学生;class->班级;")
    public CommonResult exportExecl(HttpServletResponse response,
                                    @PathVariable String umit,
                                    @RequestParam Integer id,
                                    @RequestParam @Past @DateTimeFormat(pattern = "yyyy-MM-dd") Date start,
                                    @RequestParam @Past @DateTimeFormat(pattern = "yyyy-MM-dd") Date end) {
        ExcelService excelService = excelUmitFactory.getExeclService(umit);
        excelService.exportExcel(response, id, start, end);
        return null;
    }

    //    @PostMapping("/upload/{umit}")
    public CommonResult uploadExecl(@PathVariable String umit, @RequestParam MultipartFile file) {
        ExcelService excelService = excelUmitFactory.getExeclService(umit);
        return excelService.uploadExcel(file) ?
                CommonResult.success("文件" + file.getOriginalFilename() + " 上传成功") :
                CommonResult.failed("文件" + file.getOriginalFilename() + "上传失败");
    }


}
