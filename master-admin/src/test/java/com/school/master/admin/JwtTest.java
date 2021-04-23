package com.school.master.admin;

import com.school.master.model.UmsAdmin;
import com.school.master.security.util.JwtTokenUtil;
import com.school.master.admin.domain.AdminUserDetails;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.testng.AbstractTestNGSpringContextTests;
import org.testng.annotations.Test;

@SpringBootTest
public class JwtTest extends AbstractTestNGSpringContextTests {
    @Autowired
    JwtTokenUtil jwtTokenUtil;

    @Test
    public void jwtTest() {
        UmsAdmin umsAdmin = new UmsAdmin();
        umsAdmin.setUsername("admin");
        AdminUserDetails adminUserDetails = new AdminUserDetails(umsAdmin);
        String token = jwtTokenUtil.generateToken(adminUserDetails);
        String userNameFromToken = jwtTokenUtil.getUserNameFromToken(token);
        System.out.println(userNameFromToken);
    }

}
