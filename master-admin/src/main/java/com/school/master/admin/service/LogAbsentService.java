package com.school.master.admin.service;

import com.school.master.model.LogAbsent;

import java.util.List;

/**
 * (LogAbsent)表服务接口
 *
 * @author makejava
 * @since 2021-01-31 16:19:03
 */
public interface LogAbsentService {

    /**
     * 通过ID查询单条数据
     *
     * @param id 主键
     * @return 实例对象
     */
    LogAbsent queryById(Integer id);

    /**
     * 新增数据
     *
     * @param LogAbsent 实例对象
     * @return 实例对象
     */
    LogAbsent insert(LogAbsent LogAbsent);

    /**
     * 通过主键删除数据
     *
     * @param id 主键
     * @return 是否成功
     */
    boolean deleteById(Integer id);

    List<LogAbsent> getLogsByCriteria(Integer pageSize, Integer pageNum);
}