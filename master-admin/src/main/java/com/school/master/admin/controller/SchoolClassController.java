package com.school.master.admin.controller;

import com.school.master.common.api.CommonResult;
import com.school.master.common.api.PageResult;
import com.school.master.admin.dto.CreateClassParam;
import com.school.master.admin.dto.UpdateClassParam;
import com.school.master.admin.service.SchoolClassService;
import com.school.master.model.SchoolClass;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.constraints.NotNull;
import java.util.List;


@Api(description = "班级管理")
@RestController
@RequestMapping("/class")
public class SchoolClassController {

    @Autowired
    private SchoolClassService schoolClassService;

    @ApiOperation("添加班级")
    @PostMapping("/add")
    public CommonResult addClass(@RequestBody @Validated CreateClassParam createClassParam) {
        int count = schoolClassService.addClass(createClassParam.getPid(), createClassParam.getCid(), createClassParam.getName());
        if (count > 0) {
            return CommonResult.success("添加成功", count);
        }
        return CommonResult.failed("添加失败");
    }

    @ApiOperation("查询班级")
    @GetMapping("/getClassList")
    public CommonResult<PageResult<SchoolClass>> getClassList(@RequestParam(value = "pid", required = false) Integer pid,
                                                              @RequestParam(value = "cid", required = false) Integer cid,
                                                              @RequestParam(value = "name", required = false) String name,
                                                              @RequestParam(value = "pageSize", defaultValue = "10") Integer pageSize,
                                                              @RequestParam(value = "pageNum", defaultValue = "0") Integer pageNum) {
        List<SchoolClass> classList = schoolClassService.getCriteriaClass(pid, cid, name, pageSize, pageNum);
        return CommonResult.success(PageResult.restPage(classList));
    }


    /**
     * @param pid 班级pid
     * @return
     */

    @ApiOperation("删除班级和班级学生")
    @DeleteMapping("/delete/{pid}")
    public CommonResult deleteClass(@PathVariable @NotNull Integer pid) {
        int count = schoolClassService.deleteClassByPid(pid);
        if (count > 0) {
            return CommonResult.success("删除完成", count);
        }
        return CommonResult.failed("删除失败，请重试");
    }

    @ApiOperation("更新班级信息")
    @PostMapping("/update")
    public CommonResult updateClass(@RequestBody @Validated UpdateClassParam updateParam) {
        int count = schoolClassService.updateClass(
                updateParam);
        if (count > 0) {
            return CommonResult.success("修改成功", count);
        }
        return CommonResult.failed("修改失败");
    }

}