package com.school.master.mapper;

import com.school.master.model.ClassAbsent;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * (TpAbsent)表数据库访问层
 *
 * @author makejava
 * @since 2021-03-14 22:08:33
 */
public interface ClassAbsentDao {

    /**
     * 通过ID查询单条数据
     *
     * @param pid 主键
     * @return 实例对象
     */
    List<ClassAbsent> queryById(Integer pid);

    /**
     * 查询指定行数据
     *
     * @param offset 查询起始位置
     * @param limit  查询条数
     * @return 对象列表
     */
    List<ClassAbsent> queryAllByLimit(@Param("offset") int offset, @Param("limit") int limit);


    /**
     * 通过实体作为筛选条件查询
     *
     * @param classAbsent 实例对象
     * @return 对象列表
     */
    List<ClassAbsent> queryAll(ClassAbsent classAbsent);

    /**
     * 新增数据
     *
     * @param classAbsent 实例对象
     * @return 影响行数
     */
    int insert(ClassAbsent classAbsent);

    /**
     * 批量新增数据（MyBatis原生foreach方法）
     *
     * @param entities List<TpAbsent> 实例对象列表
     * @return 影响行数
     */
    int insertBatch(@Param("entities") List<ClassAbsent> entities);

    /**
     * 批量新增或按主键更新数据（MyBatis原生foreach方法）
     *
     * @param entities List<TpAbsent> 实例对象列表
     * @return 影响行数
     */
    int insertOrUpdateBatch(@Param("entities") List<ClassAbsent> entities);

    /**
     * 修改数据
     *
     * @param classAbsent 实例对象
     * @return 影响行数
     */
    int update(ClassAbsent classAbsent);

    /**
     * 通过主键删除数据
     *
     * @param pid 主键
     * @return 影响行数
     */
    int deleteById(Integer pid);

    List<ClassAbsent> queryByRange(@Param("pid") Integer pid, @Param("start") String start, @Param("end") String end);

}

