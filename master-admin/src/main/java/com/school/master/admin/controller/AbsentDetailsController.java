package com.school.master.admin.controller;

import com.school.master.admin.dto.*;
import com.school.master.admin.service.AbsentDetailsService;
import com.school.master.admin.vo.StudentAbsentVo;
import com.school.master.admin.vo.StudentVo;
import com.school.master.common.api.CommonResult;
import com.school.master.common.api.PageResult;
import com.school.master.common.utils.DateVaildUtil;
import com.school.master.model.AbsentDetails;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiOperation;
import org.hibernate.validator.constraints.Length;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.constraints.Past;
import java.util.Date;
import java.util.List;

/**
 * 缺勤详情(AbsentDetails)表控制层
 *
 * @author makejava
 * @since 2021-01-09 19:00:36
 */
@Api(description = "缺勤管理")
@RestController
@RequestMapping("/absent")
public class AbsentDetailsController {
    @Autowired
    private AbsentDetailsService absentDetailsService;


    @ApiOperation("添加缺勤记录")
    @PostMapping("/add")
    public CommonResult addAbsent(@RequestBody @Validated List<CreateAbsentParam> createParamList) {
        int count = absentDetailsService.addAbsentList(createParamList);
        if (count > 0) {
            return CommonResult.success("添加成功", count);
        }
        return CommonResult.failed("添加失败");
    }

    @ApiOperation("删除缺勤记录")
    @DeleteMapping("/delete")
    public CommonResult deleteList(@RequestBody @Validated List<DeleteAbsentParam> deleteParams) {
        int count = absentDetailsService.deleteList(deleteParams);
        if (count > 0) {
            return CommonResult.success("删除成功", count);
        }
        return CommonResult.failed("删除失败");
    }

    @ApiOperation("更改缺勤信息")
    @PostMapping("/update")
    public CommonResult update(@RequestBody @Validated UpdateAbsentDetailsParam updateParam) {
        int count = absentDetailsService.update(updateParam);
        if (count > 0) {
            return CommonResult.success("更新完成", count);
        }
        return CommonResult.failed("更新失败");
    }

    @ApiOperation("条件搜索")
    @GetMapping("/search")
    @ApiImplicitParam(name = "umit", value = "0->学生;1->班级;",
            defaultValue = "0", allowableValues = "0,1", paramType = "query", dataType = "integer", dataTypeClass = Integer.class)
    public CommonResult<PageResult<StudentVo>> search(@RequestParam(required = false, defaultValue = "0") Integer umit,
                                                            @RequestParam(required = false) Integer id,
                                                            @RequestParam(required = false) String name,
                                                            @RequestParam(required = false, defaultValue = "10") Integer pageSize,
                                                            @RequestParam(required = false, defaultValue = "0") Integer pageNum) {
        List<StudentVo> studentVoList = absentDetailsService.search(umit, id, name, pageSize, pageNum);
        return CommonResult.success(PageResult.restPage(studentVoList));
    }

    @ApiOperation("获取学生日期范围内缺勤信息")
    @GetMapping("/range/student/{sid}")
    public CommonResult<PageResult<AbsentDetails>> dateRangeStudent(@PathVariable Integer sid,
                                                                    @RequestParam @Past @DateTimeFormat(pattern = "yyyy-MM-dd") Date startDate,
                                                                    @RequestParam @Past @DateTimeFormat(pattern = "yyyy-MM-dd") Date endDate,
                                                                    @RequestParam(required = false, defaultValue = "10") Integer pageSize,
                                                                    @RequestParam(required = false, defaultValue = "0") Integer pageNum) {
        List<AbsentDetails> detailsList = absentDetailsService.dateRangeStudent(sid, DateVaildUtil.formatString(startDate), DateVaildUtil.formatString(endDate), pageSize, pageNum);
        return CommonResult.success(PageResult.restPage(detailsList));
    }


    @ApiOperation("学生学期缺勤记录")
    @GetMapping("/semester/student/{sid}")
    @ApiImplicitParam(name = "choice", value = "1->上学期;2->下学期;",
            defaultValue = "1", allowableValues = "1,2", paramType = "query", dataType = "integer", dataTypeClass = Integer.class)
    public CommonResult semesterStudent(@PathVariable Integer sid,
                                        @RequestParam(required = false, defaultValue = "1") Integer choice,
                                        @RequestParam @Length(min = 4, max = 4) Integer startYear,
                                        @RequestParam @Length(min = 4, max = 4) Integer endYear,
                                        @RequestParam(required = false, defaultValue = "10") Integer pageSize,
                                        @RequestParam(required = false, defaultValue = "0") Integer pageNum) {
        List<AbsentDetails> detailsList = absentDetailsService.semesterStudent(sid, choice, startYear, endYear, pageSize, pageNum);
        return CommonResult.success(detailsList);
    }

    @ApiOperation("获取班级日期范围缺勤信息")
    @GetMapping("/range/class/{pid}")
    @ApiImplicitParam(name = "startDate", value = "yyyy-MM-dd 开始日期")
    public CommonResult<PageResult<StudentAbsentVo>> dateRangeClass(@PathVariable Integer pid,
                                                                         @RequestParam(required = false) @Past @DateTimeFormat(pattern = "yyyy-MM-dd") Date startDate,
                                                                         @RequestParam(required = false) @Past @DateTimeFormat(pattern = "yyyy-MM-dd") Date endDate,
                                                                         @RequestParam(required = false, defaultValue = "10") Integer pageSize,
                                                                         @RequestParam(required = false, defaultValue = "0") Integer pageNum) {
        List<StudentAbsentVo> detailsList = absentDetailsService.dateRangeClass(pid, DateVaildUtil.formatString(startDate), DateVaildUtil.formatString(endDate), pageSize, pageNum);
        return CommonResult.success(PageResult.restPage(detailsList));

    }

    @ApiOperation("班级学期缺勤记录")
    @GetMapping("/semester/class/{pid}")
    @ApiImplicitParam(name = "choice", value = "1->上学期;2->下学期;",
            defaultValue = "1", allowableValues = "1,2", paramType = "query", dataType = "integer", dataTypeClass = Integer.class)
    public CommonResult<PageResult<StudentAbsentVo>> semesterClass(@PathVariable Integer pid,
                                                           @RequestParam(required = false, defaultValue = "1") Integer choice,
                                                           @RequestParam @Length(min = 4, max = 4) Integer startYear,
                                                           @RequestParam @Length(min = 4, max = 4) Integer endYear,
                                                           @RequestParam(required = false, defaultValue = "10") Integer pageSize,
                                                           @RequestParam(required = false, defaultValue = "0") Integer pageNum) {
        List<StudentAbsentVo> detailsList = absentDetailsService.semesterClass(pid, choice, startYear, endYear, pageSize, pageNum);
        return CommonResult.success(PageResult.restPage(detailsList));
    }

}