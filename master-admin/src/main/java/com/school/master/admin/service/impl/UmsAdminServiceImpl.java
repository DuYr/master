package com.school.master.admin.service.impl;


import com.school.master.admin.domain.AdminTypeEnum;
import com.school.master.admin.domain.CacheKeyEnum;
import com.school.master.admin.service.SchoolClassService;
import com.school.master.common.exception.Asserts;
import com.school.master.common.exception.UserNotFoundException;
import com.school.master.common.utils.BaseVaildUtil;
import com.school.master.mapper.UmsAdminDao;
import com.school.master.model.AdminLoginLog;
import com.school.master.model.UmsAdmin;
import com.school.master.security.util.JwtTokenUtil;
import com.school.master.common.utils.PasswordEnCodeUtil;
import com.school.master.admin.domain.AdminUserDetails;
import com.school.master.admin.dto.*;
import com.school.master.admin.service.AdminLoginLogService;
import com.school.master.admin.service.UmsAdminCacheService;
import com.school.master.admin.service.UmsAdminService;
import com.school.master.admin.domain.BeanOperation;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import java.security.Principal;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service("umsAdminService")
public class UmsAdminServiceImpl implements UmsAdminService {
    @Autowired
    private UmsAdminDao umsAdminDao;

    @Autowired
    private UmsAdminCacheService umsAdminCacheService;
    @Autowired
    private SchoolClassService schoolClassService;
    @Autowired
    private AdminLoginLogService adminLoginLogService;
    @Autowired
    private JwtTokenUtil jwtTokenUtil;
    @Autowired
    private PasswordEnCodeUtil passwordEnCodeUtil;
    @Value("${password.reset}")
    private String RESETPASS;

    @Override
    public String login(String username, String password) {
        UserDetails userDetails = loadUserByUsername(username);
        if (!passwordEnCodeUtil.matches(userDetails.getPassword(), password)) {
            Asserts.fail("账号或密码错误");
        }
        if (!userDetails.isEnabled()) {
            Asserts.fail("该账号未激活");
        }
        UsernamePasswordAuthenticationToken authentication = new UsernamePasswordAuthenticationToken(userDetails, null, userDetails.getAuthorities());
        SecurityContextHolder.getContext().setAuthentication(authentication);
        String token = jwtTokenUtil.generateToken(userDetails);
        //插入登录信息
        insertAdminloginInfo(userDetails);
        return token;
    }

    /**
     * 通过用户名获取对象
     *
     * @param username
     * @return
     */

    @Override
    public UserDetails loadUserByUsername(String username) {
        UmsAdmin umsAdmin = getUmsAdminToCache(username);
        if (umsAdmin != null) {
            return new AdminUserDetails(umsAdmin);
        }
        umsAdmin = getUmsAdminToDao(username);
        if (umsAdmin != null) {
            umsAdminCacheService.setUmsAdmin(umsAdmin);
            return new AdminUserDetails(umsAdmin);
        }
        //账号不存在
        throw new UserNotFoundException("账号或密码错误");
    }

    @Override
    public Map<String, Object> getLoginInfo(String username) {
        AdminUserDetails userDetails = (AdminUserDetails) loadUserByUsername(username);
        UmsAdmin umsAdmin = userDetails.getUmsAdmin();
        Map<String, Object> info = new HashMap<>(4);
        String roomName = schoolClassService.getClassRoomName(umsAdmin.getPid());
        info.put("username", umsAdmin.getUsername());
        info.put("type", AdminTypeEnum.getTypeName(umsAdmin.getType()));
        info.put("roomName", roomName);
        info.put("remark", umsAdmin.getRemark());
        info.put("createTime", umsAdmin.getCreateTime());
        return info;
    }

    @Override
    public void insertAdminloginInfo(UserDetails userDetails) {
        AdminLoginLog adminLoginLog = BeanOperation.beanCopyInLog(userDetails);
        adminLoginLogService.insert(adminLoginLog);
    }

    @Override
    public String logout() {
        return "退出登录成功";
    }

    @Override
    public int addAdmin(CreateUmsAdminParam createUmsAdminParam) {
        String username = createUmsAdminParam.getUsername();
        if (isExists(username)) {
            Asserts.fail("账号已存在");
        }
        if (!passwordEnCodeUtil.isLegal(createUmsAdminParam.getPassword())) {
            Asserts.fail("密码不合法");
        }
        if (!adminVaildType(createUmsAdminParam.getType())) {
            Asserts.fail("管理员类型不存在");
        }
        if (!adminVaildPid(createUmsAdminParam.getPid())) {
            Asserts.fail("班级id不存在");
        }
        return addUmsAdmin(createUmsAdminParam);
    }

    @Override
    public int delAdmin(String username) {
        UserDetails userDetails = loadUserByUsername(username);
        UmsAdmin umsAdmin = ((AdminUserDetails) userDetails).getUmsAdmin();
        delAdminCahce(umsAdmin.getUsername());
        return umsAdminDao.deleteById(umsAdmin.getId());
    }

    /**
     * 重置密码
     *
     * @param username
     * @return
     */

    @Override
    public int rootResetPassword(Principal principal, String username) {
        UmsAdmin umsAdmin = ((AdminUserDetails) loadUserByUsername(username)).getUmsAdmin();
        UpdatePasswordAdminParam resetParam = new UpdatePasswordAdminParam();
        resetParam.setNewPassword(RESETPASS);
        return resetPassword(umsAdmin, resetParam);
    }

    private int resetPassword(UmsAdmin umsAdmin, UpdatePasswordAdminParam updatePasswordAdminParam) {
        delAdminCahce(umsAdmin.getUsername());
        return updateUmsAdminToDao(umsAdmin, updatePasswordAdminParam);
    }

    private boolean adminVaildType(Integer type) {
        return BaseVaildUtil.equalsPlus(type, AdminTypeEnum.ROOT.getType(), AdminTypeEnum.ORDINARY.getType());
    }

    /**
     * 验证班级pid合法性
     *
     * @param pid
     * @return
     */

    private boolean adminVaildPid(Integer pid) {
        return schoolClassService.getClassByPid(pid) != null;
    }


    @Override
    public int updateAdmin(UpdateAdminParam updateAdminParam) {
        UserDetails userDetails = loadUserByUsername(updateAdminParam.getUsername());
        UmsAdmin umsAdmin = ((AdminUserDetails) userDetails).getUmsAdmin();
        BeanUtils.copyProperties(updateAdminParam, umsAdmin);
        Integer type = umsAdmin.getType();
        if (type != null) {
            if (!adminVaildType(type)) {
                Asserts.fail("管理员类型不合法");
            }
        }
        Integer pid = umsAdmin.getPid();
        if (pid != null) {
            if (!adminVaildPid(pid)) {
                Asserts.fail("班级不合法");
            }
        }
        Integer enable = umsAdmin.getEnable();
        if (enable != null) {
            if (!adminVaildEnable(enable)) {
                Asserts.fail("账号状态更改失败");
            }
        }
        delAdminCahce(umsAdmin.getUsername());
        return umsAdminDao.update(umsAdmin);
    }

    private boolean adminVaildEnable(Integer enable) {
        return enable == 0 || enable == 1;
    }


    /**
     * @param updatePasswordAdminParam
     * @return status
     * status {
     * -3 参数不合法
     * -2 旧密码相同
     * -1 旧密码错误
     * 1 修改成功
     * }
     */

    @Override
    public int rootResetPassword(UpdatePasswordAdminParam updatePasswordAdminParam) {
        String username = updatePasswordAdminParam.getUsername();
        UserDetails userDetails = loadUserByUsername(username);
        UmsAdmin umsAdmin = ((AdminUserDetails) userDetails).getUmsAdmin();
        String oldPassword = updatePasswordAdminParam.getOldPassword();
        String newPassword = updatePasswordAdminParam.getNewPassword();
        //正则合法判断
        if (!passwordEnCodeUtil.isLegal(newPassword)) {
            return -3;
        }
        //判断旧密码是否正确(经过MD5处理)
        if (!passwordEnCodeUtil.matches(umsAdmin.getPassword(), oldPassword)) {
            return -1;
        }
        //判断旧密码与新密码是否相同
        if (passwordEnCodeUtil.matches(umsAdmin.getPassword(), newPassword)) {
            return -2;
        }
        return resetPassword(umsAdmin, updatePasswordAdminParam);
    }

    @Override
    public List getAdminResult(Long adminsId) {
        List<UmsAdmin> umsAdminList = umsAdminCacheService.getUmsAdminList(adminsId);
        if (umsAdminList != null) {
            return umsAdminList;
        }
        List<UmsAdmin> umsAdmins = umsAdminDao.queryAllByInfo(0, 999);
        if (umsAdmins != null) {
            umsAdminCacheService.setUmsAdminList(adminsId, umsAdmins);
            return umsAdmins;
        }
        return null;
    }

    /**
     * 删除对象集合和登录信息
     *
     * @param username
     */

    @Override
    public void delAdminCahce(String username) {
        umsAdminCacheService.delUmsAdmin(username);
        umsAdminCacheService.delUmsAdminList(CacheKeyEnum.ADMIN_LIST_ID.getKey());
    }


    private boolean isExists(String username) {
        if (getUmsAdminToCache(username) == null) {
            if (getUmsAdminToDao(username) == null) {
                return false;
            }
        }
        return true;
    }

    private int addUmsAdmin(CreateUmsAdminParam createParam) {
        UmsAdmin umsAdmin = BeanOperation.beanCopy(createParam,new UmsAdmin());
        delAdminCahce(umsAdmin.getUsername());
        return getInsterAdminId(umsAdmin);
    }

    private int getInsterAdminId(UmsAdmin umsAdmin) {
        umsAdmin.setPassword(passwordEnCodeUtil.passwordMd5Handle(umsAdmin.getPassword()));
        return umsAdminDao.insert(umsAdmin);
    }

    /**
     * 刷线token
     *
     * @param oldToken
     * @return
     */

    @Override
    public String refreshToken(String oldToken) {
        return jwtTokenUtil.refreshHeadToken(oldToken);
    }


    private UmsAdmin getUmsAdminToCache(String username) {
        UmsAdmin umsAdmin = umsAdminCacheService.getUmsAdmin(username);
        return umsAdmin;
    }

    private UmsAdmin getUmsAdminToDao(String username) {
        UmsAdmin umsAdmin = umsAdminDao.queryUmsAdminByUsername(username);
        return umsAdmin;
    }

    private int updateUmsAdminToDao(UmsAdmin umsAdmin, UpdatePasswordAdminParam updatePasswordAdminParam) {
        String passwordMd5 = passwordEnCodeUtil.passwordMd5Handle(updatePasswordAdminParam.getNewPassword());
        //组合更新对象
        UmsAdmin newPasswordAdmin = BeanOperation.createNewPasswordAdmin
                (umsAdmin.getId(), updatePasswordAdminParam.getUsername(), passwordMd5);
        return umsAdminDao.update(newPasswordAdmin);
    }
}
