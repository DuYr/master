package com.school.master.admin.controller;


import com.school.master.admin.domain.CacheKeyEnum;
import com.school.master.common.api.CommonResult;
import com.school.master.common.api.PageResult;
import com.school.master.admin.dto.*;
import com.school.master.admin.service.UmsAdminService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import springfox.documentation.annotations.ApiIgnore;

import javax.servlet.http.HttpServletRequest;
import java.security.Principal;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @FileName: UmsUserControler
 * @Author: LeeDream
 * @Date: 2020/11/29 17:02
 * @Description:
 * @Version 1.0.0
 */
@Api(description = "管理员基本操作")
@RestController
@RequestMapping("/admin")
public class UmsAdminController {
    @Autowired
    private UmsAdminService umsAdminService;
    @Value("${jwt.tokenHeader}")
    private String tokenHeader;
    @Value("${jwt.tokenHead}")
    private String tokenHead;
    /**
     * 旧密码错误
     **/
    private static final int OLD_PASSWORD_ERROR = -1;
    /**
     * 新旧密码相同
     **/
    private static final int NEWANDOLD_EQUAL = -2;
    /**
     * 参数不合法
     **/
    private static final int WRONGFUL = -3;

    @ApiOperation("登录获取token")
    @PostMapping("/login")
    public CommonResult login(@RequestBody @Validated AdminLoginParam adminLoginParam) {
        String username = adminLoginParam.getUsername();
        String password = adminLoginParam.getPassword();
        String token = umsAdminService.login(username, password);
        Map<String, String> tokenInfo = new HashMap<>(2);
        tokenInfo.put("tokenHead", tokenHead);
        tokenInfo.put("token", token);
        return CommonResult.success("登录成功", tokenInfo);
    }

    @ApiOperation("获取登录用户信息")
    @GetMapping("/info")
    public CommonResult info(@ApiIgnore Principal principal) {
        if (principal == null) {
            return CommonResult.unauthorized(null);
        }
        Map<String, Object> info = umsAdminService.getLoginInfo(principal.getName());
        return CommonResult.success(info);
    }

    @ApiOperation("刷新token")
    @GetMapping("/refreshToken")
    public CommonResult refreshToken(HttpServletRequest request) {
        String token = request.getHeader(tokenHeader);
        String refreshToken = umsAdminService.refreshToken(token);
        if (refreshToken == null) {
            return CommonResult.failed("token已过期");
        }
        Map<String, String> tokenInfo = new HashMap<>(2);
        tokenInfo.put("tokenHead", tokenHead);
        tokenInfo.put("token", refreshToken);
        return CommonResult.success("已成功刷新token", tokenInfo);
    }

    @ApiOperation("退出")
    @PostMapping("logout")
    public CommonResult logout() {
        return CommonResult.success(umsAdminService.logout());
    }

    @ApiOperation("修改密码")
    @PostMapping("/revise")
    public CommonResult revisePassword(@RequestBody @Validated UpdatePasswordAdminParam updatePasswordAdminParam) {
        int status = umsAdminService.rootResetPassword(updatePasswordAdminParam);
        if (status > 0) {
            return CommonResult.success("修改成功");
        }
        if (status == WRONGFUL) {
            return CommonResult.failed("参数不合法");
        } else if (status == OLD_PASSWORD_ERROR) {
            return CommonResult.failed("旧密码错误");
        } else if (status == NEWANDOLD_EQUAL) {
            return CommonResult.failed("新旧密码相同");
        } else {
            return CommonResult.failed("请重试");
        }
    }

    @ApiOperation("更改信息")
    @PostMapping("/update")
    public CommonResult updateAdmin(@RequestBody @Validated UpdateAdminParam updateAdminParam) {
        int count = umsAdminService.updateAdmin(updateAdminParam);
        if (count > 0) {
            return CommonResult.success("更新成功", count);
        }
        return CommonResult.failed("更新失败");
    }


    /**
     * 超级管理员操作 type==1
     *
     * @param createUmsAdminParam
     * @return
     */

    @ApiOperation("添加账号")
    @PostMapping("/add")
    public CommonResult addAdmin(@RequestBody @Validated CreateUmsAdminParam createUmsAdminParam) {
        int count = umsAdminService.addAdmin(createUmsAdminParam);
        if (count > 0) {
            return CommonResult.success("添加成功", count);
        }
        return CommonResult.failed("添加失败");

    }

    @ApiOperation("删除admin")
    @DeleteMapping("/delete/{username}")
    public CommonResult deleteAdmin(@PathVariable String username) {
        int count = umsAdminService.delAdmin(username);
        if (count > 0) {
            return CommonResult.success("删除成功", 1);
        }
        return CommonResult.failed("删除失败");
    }

    /**
     * 超级管理员操作 type==1
     *
     * @param username
     * @return
     */

    @ApiOperation("重置密码")
    @PostMapping("/reset/{username}")
    public CommonResult resetPassword(@ApiIgnore Principal principal, @PathVariable String username) {
        int count = umsAdminService.rootResetPassword(principal, username);
        if (count > 0) {
            return CommonResult.success("密码重置成功", count);
        }
        return CommonResult.success("密码重置失败");
    }

    /**
     * 超级管理员操作 type==1
     *
     * @return
     */
    @ApiOperation("查看admin")
    @GetMapping("/getUserList")
    public CommonResult<PageResult<UmsAdmin>> showAdmin() {
        List<UmsAdmin> umsAdmins = umsAdminService.getAdminResult(CacheKeyEnum.ADMIN_LIST_ID.getKey());
        return CommonResult.success(PageResult.restPage(umsAdmins));
    }

}
