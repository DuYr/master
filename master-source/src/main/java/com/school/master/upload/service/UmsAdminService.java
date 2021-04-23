package com.school.master.upload.service;

import org.springframework.security.core.userdetails.UserDetails;

public interface UmsAdminService {
    UserDetails loadUserByUsername(String username);
}
