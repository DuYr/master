package com.school.master.upload.controller;


import com.school.master.common.api.CommonResult;
import com.school.master.upload.service.AdminImageService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.security.Principal;

@Api(description = "admin头像上传")
@RestController
@RequestMapping("/img")
public class AdminImageController {
    @Autowired
    private AdminImageService adminImageService;

    @ApiOperation("上传")
    @RequestMapping("/upload")
    public CommonResult upload(Principal principal, MultipartFile fileUpload) {
        int count = adminImageService.uploadImage(principal, fileUpload);
        if (count == 1) {
            return CommonResult.success("上传头像成功");
        } else if (count == 2) {
            return CommonResult.success("更新头像成功");
        }
        return CommonResult.success("上传头像失败");
    }

    @ApiOperation("获取头像url")
    @RequestMapping("/url")
    public CommonResult getImageUrl(Principal principal) {
        String url = adminImageService.getImageUrl(principal);
        return CommonResult.success(url);
    }

}
