package com.school.master.upload.controller;


import com.school.master.common.api.CommonResult;
import com.school.master.upload.service.AvatarService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.security.Principal;

@Api(description = "admin头像上传")
@RestController
@RequestMapping("/img")
public class AdminImageController {
    @Autowired
    private AvatarService avatarService;

    @ApiOperation("上传")
    @PostMapping("/upload")
    public CommonResult upload(Principal principal, MultipartFile fileUpload) {
        int count = avatarService.uploadImage(principal, fileUpload);
        if (count == 1) {
            return CommonResult.success("上传头像成功");
        } else if (count == 2) {
            return CommonResult.success("更新头像成功");
        }
        return CommonResult.success("上传头像失败");
    }

    @ApiOperation("获取头像url")
    @GetMapping("/url")
    public CommonResult getImageUrl(Principal principal) {
        String url = avatarService.getImageUrl(principal);
        return CommonResult.success(url);
    }

}
