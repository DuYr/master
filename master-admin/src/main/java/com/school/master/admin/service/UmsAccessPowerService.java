package com.school.master.admin.service;


import com.school.master.admin.dto.UmsAccessPower;

import java.util.List;

/**
 * (UmsAccessPower)表服务接口
 *
 * @author makejava
 * @since 2020-12-28 11:03:38
 */
public interface UmsAccessPowerService {

    /**
     * 通过ID查询单条数据
     *
     * @param id 主键
     * @return 实例对象
     */
    UmsAccessPower queryById(Integer id);

    List<UmsAccessPower> queryAll();

    /**
     * 新增数据
     *
     * @param UmsAccessPower 实例对象
     * @return 实例对象
     */
    UmsAccessPower insert(UmsAccessPower UmsAccessPower);

    /**
     * 修改数据
     *
     * @param UmsAccessPower 实例对象
     * @return 实例对象
     */
    UmsAccessPower update(UmsAccessPower UmsAccessPower);

    /**
     * 通过主键删除数据
     *
     * @param id 主键
     * @return 是否成功
     */
    boolean deleteById(Integer id);

}