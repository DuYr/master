package com.school.master.mbg.mapper;

import com.school.master.admin.dto.College;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * (TpCollege)表数据库访问层
 *
 * @author makejava
 * @since 2020-12-31 10:40:45
 */
public interface CollegeDao {

    /**
     * 通过ID查询单条数据
     *
     * @param cid 主键
     * @return 实例对象
     */
    College queryById(Integer cid);

    College queryAllByName(String name);

    /**
     * 查询指定行数据
     *
     * @param offset 查询起始位置
     * @param limit  查询条数
     * @return 对象列表
     */
    List<College> queryAllByLimit(@Param("offset") int offset, @Param("limit") int limit);


    /**
     * 通过实体作为筛选条件查询
     *
     * @param college 实例对象
     * @return 对象列表
     */
    List<College> queryAll(College college);

    /**
     * 新增数据
     *
     * @param college 实例对象
     * @return 影响行数
     */
    int insert(College college);

    /**
     * 修改数据
     *
     * @param college 实例对象
     * @return 影响行数
     */
    int update(College college);

    /**
     * 通过学院cid删除数据
     *
     * @param cid 主键
     * @return 影响行数
     */
    int deleteById(Integer cid);

}