package com.school.master.admin.dao;

import com.school.master.admin.dto.LogAbsent;

import java.util.List;

/**
 * (TpLogAbsent)表数据库访问层
 *
 * @author makejava
 * @since 2021-01-13 14:13:23
 */
public interface LogAbsentDao {

    /**
     * 通过ID查询单条数据
     *
     * @param id 主键
     * @return 实例对象
     */
    LogAbsent queryById(Integer id);


    List<LogAbsent> queryAll();

    /**
     * 新增数据
     *
     * @param logAbsent 实例对象
     * @return 影响行数
     */
    int insert(LogAbsent logAbsent);

    /**
     * 修改数据
     *
     * @param logAbsent 实例对象
     * @return 影响行数
     */
    int update(LogAbsent logAbsent);

    /**
     * 通过主键删除数据
     *
     * @param id 主键
     * @return 影响行数
     */
    int deleteById(Integer id);

}