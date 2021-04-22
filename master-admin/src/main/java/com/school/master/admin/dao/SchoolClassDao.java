package com.school.master.admin.dao;

import com.school.master.admin.dto.SchoolClass;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * (TpClass)表数据库访问层
 *
 * @author makejava
 * @since 2021-01-02 16:17:49
 */
public interface SchoolClassDao {

    /**
     * 通过ID查询单条数据
     *
     * @param pid 主键
     * @return 实例对象
     */
    SchoolClass queryByPid(Integer pid);

    /**
     * 查询指定行数据
     *
     * @param offset 查询起始位置
     * @param limit  查询条数
     * @return 对象列表
     */
    List<SchoolClass> queryAllByLimit(@Param("offset") int offset, @Param("limit") int limit);


    /**
     * 通过实体作为筛选条件查询
     *
     * @param schoolClass 实例对象
     * @return 对象列表
     */
    List<SchoolClass> queryAll(SchoolClass schoolClass);

    /**
     * 新增数据
     *
     * @param schoolClass 实例对象
     * @return 影响行数
     */
    int insert(SchoolClass schoolClass);

    /**
     * 修改数据
     *
     * @param schoolClass 实例对象
     * @return 影响行数
     */
    int update(SchoolClass schoolClass);

    /**
     * 通过主键删除数据
     *
     * @param pid 主键
     * @return 影响行数
     */
    int deleteById(Integer pid);

    int deleteByCid(Integer cid);

    List<SchoolClass> queryByCid(Integer cid);

    List<SchoolClass> queryClassByLikeName(@Param(value = "likeName") String likeName);

     List<SchoolClass> queryClassByLikePid(@Param("likePid") String likePid);
}