package com.school.master.admin.domain;

import com.school.master.model.UmsAdmin;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

/**
 * @FileName: AdminUserDetails
 * @Author: LeeDream
 * @Date: 2020/12/27 9:53
 * @Description:
 * @Version 1.0.0
 */


public class AdminUserDetails implements UserDetails {
    private UmsAdmin umsAdmin;

    /**
     * 返回当前用户角色类型
     *
     * @return
     */
    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        List<GrantedAuthority> authorities = new ArrayList<>();
        authorities.add(new SimpleGrantedAuthority(String.valueOf(umsAdmin.getType())));
        //返回当前用户的管理员类型
        return authorities;
    }

    public AdminUserDetails(UmsAdmin umsAdmin) {
        this.umsAdmin = umsAdmin;
    }

    public UmsAdmin getUmsAdmin() {
        return umsAdmin;
    }

    public void setUmsAdmin(UmsAdmin umsAdmin) {
        this.umsAdmin = umsAdmin;
    }

    @Override
    public String getPassword() {
        return umsAdmin.getPassword();
    }

    @Override
    public String getUsername() {
        return umsAdmin.getUsername();
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    /**
     * 1激活
     *
     * @return
     */

    @Override
    public boolean isEnabled() {
        return umsAdmin.getEnable().equals(1);
    }
}
