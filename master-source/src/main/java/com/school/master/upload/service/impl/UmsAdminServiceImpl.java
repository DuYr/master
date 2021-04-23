package com.school.master.upload.service.impl;

import com.school.master.common.exception.UserNotFoundException;
import com.school.master.mapper.UmsAdminDao;
import com.school.master.model.UmsAdmin;
import com.school.master.upload.domain.AdminUserDetails;
import com.school.master.upload.service.UmsAdminService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

@Service("umsAdminService")
public class UmsAdminServiceImpl implements UmsAdminService {
    @Autowired
    private UmsAdminDao umsAdminDao;

    /**
     * 通过用户名获取对象
     *
     * @param username
     * @return
     */

    @Override
    public UserDetails loadUserByUsername(String username) {
        UmsAdmin umsAdmin = umsAdminDao.queryUmsAdminByUsername(username);
        if (umsAdmin != null) {
            return new AdminUserDetails(umsAdmin);
        }
        throw new UserNotFoundException("账号或密码错误");
    }
}
