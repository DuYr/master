package com.school.master.admin.controller;

import com.school.master.admin.service.ClassAbsentService;
import com.school.master.admin.vo.ClassAbsentFullVo;
import com.school.master.common.api.CommonResult;
import com.school.master.common.api.PageResult;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.bind.annotation.*;

import javax.validation.constraints.Past;
import java.util.Date;
import java.util.List;

/**
 * (TpAbsent)表控制层
 *
 * @author makejava
 * @since 2021-03-14 22:08:34
 */
//@Api(description = "班级全勤情况")
//@RestController
////@RequestMapping("/full")
public class ClassAbsentController {
    /**
     * 服务对象
     */
    @Autowired
    private ClassAbsentService classAbsentService;

    /**
     * 通过主键查询单条数据
     *
     * @param pid
     * @return
     */
//    @ApiOperation("班级缺勤查询")
//    @GetMapping("/class")
    public CommonResult<PageResult<ClassAbsentFullVo>> dateRange(@RequestParam(required = false) Integer pid,
                                                                 @RequestParam(required = false) @Past @DateTimeFormat(pattern = "yyyy-MM-dd") Date start,
                                                                 @RequestParam(required = false) @Past @DateTimeFormat(pattern = "yyyy-MM-dd") Date end,
                                                                 @RequestParam(required = false, defaultValue = "0") Integer pageNum,
                                                                 @RequestParam(required = false, defaultValue = "10") Integer pageSize) {
        List<ClassAbsentFullVo> absentFullList = classAbsentService.dateRange(pid, start, end, pageSize, pageNum);
        return CommonResult.success(PageResult.restPage(absentFullList));
    }
}
