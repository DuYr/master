package com.school.master.admin.service.impl;

import com.school.master.admin.service.UmsAccessPowerService;
import com.school.master.mapper.UmsAccessPowerDao;
import com.school.master.model.UmsAccessPower;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * (UmsAccessPower)表服务实现类
 *
 * @author makejava
 * @since 2020-12-28 11:03:38
 */
@Service("umsAccessPowerService")
public class UmsAccessPowerServiceImpl implements UmsAccessPowerService {
    @Autowired
    private UmsAccessPowerDao umsAccessPowerDao;

    /**
     * 通过ID查询单条数据
     *
     * @param id 主键
     * @return 实例对象
     */
    @Override
    public UmsAccessPower queryById(Integer id) {
        return this.umsAccessPowerDao.selectByPrimaryKey(id);
    }

    @Override
    public List<UmsAccessPower> queryAll() {
        return this.umsAccessPowerDao.queryAllList();
    }

    /**
     * 新增数据
     *
     * @param UmsAccessPower 实例对象
     * @return 实例对象
     */
    @Override
    public UmsAccessPower insert(UmsAccessPower UmsAccessPower) {
        this.umsAccessPowerDao.insert(UmsAccessPower);
        return UmsAccessPower;
    }

    /**
     * 修改数据
     *
     * @param UmsAccessPower 实例对象
     * @return 实例对象
     */
    @Override
    public UmsAccessPower update(UmsAccessPower UmsAccessPower) {
        this.umsAccessPowerDao.updateByPrimaryKey(UmsAccessPower);
        return this.queryById(UmsAccessPower.getId());
    }

    /**
     * 通过主键删除数据
     *
     * @param id 主键
     * @return 是否成功
     */
    @Override
    public boolean deleteById(Integer id) {
        return this.umsAccessPowerDao.deleteByPrimaryKey(id) > 0;
    }
}