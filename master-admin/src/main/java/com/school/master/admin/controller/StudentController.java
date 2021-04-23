package com.school.master.admin.controller;

import com.school.master.common.api.CommonResult;
import com.school.master.common.api.PageResult;
import com.school.master.admin.dto.CreateStudentParam;
import com.school.master.admin.dto.UpdateStudentParam;
import com.school.master.admin.service.StudentService;
import com.school.master.model.Student;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.Arrays;
import java.util.List;

@Api(description = "学生管理")
@RestController
@RequestMapping("/students")
public class StudentController {
    @Autowired
    private StudentService studentService;


    @ApiOperation("查询学生")
    @GetMapping("/getStudentList")
    public CommonResult<PageResult<Student>> getStudentList(@RequestParam(value = "sid", required = false) Integer sid,
                                                            @RequestParam(value = "pid", required = false) Integer pid,
                                                            @RequestParam(value = "name", required = false) String name,
                                                            @RequestParam(value = "pageSize", defaultValue = "10") Integer pageSize,
                                                            @RequestParam(value = "pageNum", defaultValue = "0") Integer pageNum) {
        List<Student> studentList = studentService.getStudentsByCriteria(sid, pid, name, pageSize, pageNum);
        return CommonResult.success(PageResult.restPage(studentList));
    }


    @ApiOperation("添加学生")
    @PostMapping("/add")
    public CommonResult addStudent(@RequestBody @Validated List<CreateStudentParam> studentsParamList) {
        int count = studentService.addStudentList(studentsParamList);
        if (count > 0) {
            return CommonResult.success("添加成功", count);
        }
        return CommonResult.failed("添加失败");
    }

    @ApiOperation("删除学生")
    @DeleteMapping("/delete")
    public CommonResult deleteStudent(@RequestParam("sid") Integer[] sid) {
        int count = studentService.deleteStudentList(Arrays.asList(sid));
        if (count > 0) {
            return CommonResult.success("删除完成", count);
        }
        return CommonResult.failed("删除失败");
    }

    @ApiOperation("更新学生信息")
    @PostMapping("/update")
    public CommonResult updateStudent(@RequestBody @Validated UpdateStudentParam updateStudentParam) {
        int count = studentService.updateStudent(updateStudentParam);
        if (count > 0) {
            return CommonResult.success("更新成功", count);
        }
        return CommonResult.failed("更新失败");
    }

}

