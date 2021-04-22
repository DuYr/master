package com.school.master.mbg.mapper;


import com.school.master.mbg.model.UmsAccessPower;

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