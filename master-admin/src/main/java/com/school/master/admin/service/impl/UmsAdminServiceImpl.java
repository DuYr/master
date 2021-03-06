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
            Asserts.fail("?????????????????????");
        }
        if (!userDetails.isEnabled()) {
            Asserts.fail("??????????????????");
        }
        UsernamePasswordAuthenticationToken authentication = new UsernamePasswordAuthenticationToken(userDetails, null, userDetails.getAuthorities());
        SecurityContextHolder.getContext().setAuthentication(authentication);
        String token = jwtTokenUtil.generateToken(userDetails);
        //??????????????????
        insertAdminloginInfo(userDetails);
        return token;
    }

    /**
     * ???????????????????????????
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
        //???????????????
        throw new UserNotFoundException("?????????????????????");
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
        return "??????????????????";
    }

    @Override
    public int addAdmin(CreateUmsAdminParam createUmsAdminParam) {
        String username = createUmsAdminParam.getUsername();
        if (isExists(username)) {
            Asserts.fail("???????????????");
        }
        if (!passwordEnCodeUtil.isLegal(createUmsAdminParam.getPassword())) {
            Asserts.fail("???????????????");
        }
        if (!adminVaildType(createUmsAdminParam.getType())) {
            Asserts.fail("????????????????????????");
        }
        if (!adminVaildPid(createUmsAdminParam.getPid())) {
            Asserts.fail("??????id?????????");
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
     * ????????????
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
     * ????????????pid?????????
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
                Asserts.fail("????????????????????????");
            }
        }
        Integer pid = umsAdmin.getPid();
        if (pid != null) {
            if (!adminVaildPid(pid)) {
                Asserts.fail("???????????????");
            }
        }
        Integer enable = umsAdmin.getEnable();
        if (enable != null) {
            if (!adminVaildEnable(enable)) {
                Asserts.fail("????????????????????????");
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
     * -3 ???????????????
     * -2 ???????????????
     * -1 ???????????????
     * 1 ????????????
     * }
     */

    @Override
    public int rootResetPassword(UpdatePasswordAdminParam updatePasswordAdminParam) {
        String username = updatePasswordAdminParam.getUsername();
        UserDetails userDetails = loadUserByUsername(username);
        UmsAdmin umsAdmin = ((AdminUserDetails) userDetails).getUmsAdmin();
        String oldPassword = updatePasswordAdminParam.getOldPassword();
        String newPassword = updatePasswordAdminParam.getNewPassword();
        //??????????????????
        if (!passwordEnCodeUtil.isLegal(newPassword)) {
            return -3;
        }
        //???????????????????????????(??????MD5??????)
        if (!passwordEnCodeUtil.matches(umsAdmin.getPassword(), oldPassword)) {
            return -1;
        }
        //???????????????????????????????????????
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
     * ?????????????????????????????????
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
     * ??????token
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
        //??????????????????
        UmsAdmin newPasswordAdmin = BeanOperation.createNewPasswordAdmin
                (umsAdmin.getId(), updatePasswordAdminParam.getUsername(), passwordMd5);
        return umsAdminDao.update(newPasswordAdmin);
    }
}
