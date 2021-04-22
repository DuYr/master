package com.school.master.admin.service;

import com.school.master.admin.dto.UmsAdmin;

import java.util.List;

/**
 * @FileName: UmsAdminCacheService
 * @Author: LeeDream
 * @Date: 2020/12/15 16:10
 * @Description: redis操作层
 * @Version 1.0.0
 */

public interface UmsAdminCacheService {

    void delUmsAdmin(String username);

    UmsAdmin getUmsAdmin(String username);

    void setUmsAdmin(UmsAdmin umsAdmin);

    List<UmsAdmin> getUmsAdminList(Long adminsId);

    void setUmsAdminList(Long adminsId,List<UmsAdmin> adminsList);

    void delUmsAdminList(Long adminsId);
}
