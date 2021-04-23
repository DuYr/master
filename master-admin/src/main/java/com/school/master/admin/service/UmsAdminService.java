package com.school.master.admin.service;


import com.school.master.admin.dto.CreateUmsAdminParam;
import com.school.master.admin.dto.UpdateAdminParam;
import com.school.master.admin.dto.UpdatePasswordAdminParam;
import org.springframework.security.core.userdetails.UserDetails;

import java.security.Principal;
import java.util.List;
import java.util.Map;

public interface UmsAdminService {
    /**
     * @param username
     * @param password
     * @return jwt
     */

    String login(String username, String password);

    void insertAdminloginInfo(UserDetails userDetails);

    String logout();


    int rootResetPassword(UpdatePasswordAdminParam updatePasswordAdminParam);

    int addAdmin(CreateUmsAdminParam createUmsAdminParam);


    int rootResetPassword(Principal principal, String username);

    List getAdminResult(Long adminsId);

    int updateAdmin(UpdateAdminParam updateAdminParam);

    void delAdminCahce(String username);

    String refreshToken(String oldToken);

    UserDetails loadUserByUsername(String username);

    int delAdmin(String username);

    Map<String, Object> getLoginInfo(String name);
}
