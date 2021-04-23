package com.school.master.admin.service.impl;

import com.school.master.common.service.RedisService;
import com.school.master.admin.service.UmsAdminCacheService;
import com.school.master.model.UmsAdmin;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @FileName: UmsAdminCacheServiceImpl
 * @Author: LeeDream
 * @Date: 2020/12/15 17:33
 * @Description:
 * @Version 1.0.0
 */
@Service("umsAdminCacheService")
public class UmsAdminCacheServiceImpl implements UmsAdminCacheService {
    @Autowired
    private RedisService redisService;
    @Value("${redis.database}")
    private String REDIS_DATABASE;
    @Value("${redis.key.admin}")
    private String REDIS_KEY_ADMIN;
    @Value("${redis.key.sourceList}")
    private String REDIS_KEY_SOURCELIST;
    @Value("${redis.expire.time}")
    private Long REDIS_EXPIRE_TIME;

    @Override
    public UmsAdmin getUmsAdmin(String username) {
        String key = REDIS_DATABASE + ":" + REDIS_KEY_ADMIN + ":" + username;
        return (UmsAdmin) redisService.get(key);
    }

    @Override
    public void setUmsAdmin(UmsAdmin umsAdmin) {
        String key = REDIS_DATABASE + ":" + REDIS_KEY_ADMIN + ":" + umsAdmin.getUsername();
        redisService.set(key, umsAdmin, REDIS_EXPIRE_TIME);
    }

    @Override
    public void delUmsAdmin(String username) {
        String key = REDIS_DATABASE + ":" + REDIS_KEY_ADMIN + ":" + username;
        redisService.del(key);
    }

    @Override
    public List<UmsAdmin> getUmsAdminList(Long adminsId) {
        String key = REDIS_DATABASE + ":" + REDIS_KEY_SOURCELIST + ":" + adminsId;
        return (List<UmsAdmin>) redisService.get(key);
    }

    @Override
    public void setUmsAdminList(Long adminsId, List<UmsAdmin> adminsList) {
        String key = REDIS_DATABASE + ":" + REDIS_KEY_SOURCELIST + ":" + adminsId;
        redisService.set(key, adminsList, REDIS_EXPIRE_TIME);
    }

    @Override
    public void delUmsAdminList(Long adminsId) {
        String key = REDIS_DATABASE + ":" + REDIS_KEY_SOURCELIST + ":" + adminsId;
        redisService.del(key);
    }

}
