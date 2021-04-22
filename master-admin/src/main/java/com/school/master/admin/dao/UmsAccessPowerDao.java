package com.school.master.admin.dao;

import com.school.master.admin.dto.UmsAccessPower;

import java.util.List;

public interface UmsAccessPowerDao {
    int deleteByPrimaryKey(Integer id);

    int insert(UmsAccessPower record);

    int insertSelective(UmsAccessPower record);

    UmsAccessPower selectByPrimaryKey(Integer id);

    List<UmsAccessPower> queryAllList();

    int updateByPrimaryKeySelective(UmsAccessPower record);

    int updateByPrimaryKey(UmsAccessPower record);
}