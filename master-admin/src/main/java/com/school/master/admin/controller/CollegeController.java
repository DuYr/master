package com.school.master.admin.controller;

import com.school.master.admin.dto.CreateCollegeParam;
import com.school.master.common.api.CommonResult;
import com.school.master.admin.dto.UpdateCollegeParam;
import com.school.master.admin.service.CollegeService;
import com.school.master.common.api.PageResult;
import com.school.master.model.College;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.constraints.NotNull;
import java.util.List;


@Api(description = "学院管理")

@RestController
@RequestMapping("/college")
public class CollegeController {
    @Autowired
    private CollegeService collegeService;

    @ApiOperation("新增学院")
    @PostMapping("/add")
    public CommonResult addCollege(@RequestBody @Validated List<CreateCollegeParam> collegeList) {
        int count = collegeService.addCollegeList(collegeList);
        if (count > 0) {
            return CommonResult.success("添加成功", count);
        }
        return CommonResult.failed("添加失败");
    }

    @ApiOperation("获取学院信息")
    @GetMapping("/getCollegeList/{pageSize}/{pageNum}")
    public CommonResult<PageResult<College>> getCollegeList(@PathVariable Integer pageSize,
                                                            @PathVariable Integer pageNum) {
        List<College> colleges = collegeService.pageQueryAll(pageSize, pageNum);
        return CommonResult.success(PageResult.restPage(colleges));
    }

    @ApiOperation("更新学院")
    @PostMapping("/update")
    public CommonResult updateCollege(@RequestBody @Validated UpdateCollegeParam updateParam) {
        int count = collegeService.updateColloege(updateParam);
        if (count > 0) {
            return CommonResult.success("更新完成", count);
        }
        return CommonResult.failed("更新失败");
    }

    @ApiOperation("删除学院及班级")
    @DeleteMapping("/delete/{cid}")
    public CommonResult updateCollege(@PathVariable @NotNull Integer cid) {
        int count = collegeService.deleteCollege(cid);
        if (count > 0) {
            return CommonResult.success("删除完成", count);
        }
        return CommonResult.failed("学院不存在删除失败 cid:" + cid);
    }


}
