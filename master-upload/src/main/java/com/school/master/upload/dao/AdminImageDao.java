package com.school.master.upload.dao;

import com.school.master.upload.dto.AdminImage;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * (AdminImage)表数据库访问层
 *
 * @author makejava
 * @since 2021-04-19 14:50:52
 */
public interface AdminImageDao {

    /**
     * 通过ID查询单条数据
     *
     * @param name 主键
     * @return 实例对象
     */
    AdminImage queryById(String name);

    /**
     * 查询指定行数据
     *
     * @param offset 查询起始位置
     * @param limit  查询条数
     * @return 对象列表
     */
    List<AdminImage> queryAllByLimit(@Param("offset") int offset, @Param("limit") int limit);


    /**
     * 通过实体作为筛选条件查询
     *
     * @param adminImage 实例对象
     * @return 对象列表
     */
    List<AdminImage> queryAll(AdminImage adminImage);

    /**
     * 新增数据
     *
     * @param adminImage 实例对象
     * @return 影响行数
     */
    int insert(AdminImage adminImage);

    /**
     * 批量新增数据（MyBatis原生foreach方法）
     *
     * @param entities List<AdminImage> 实例对象列表
     * @return 影响行数
     */
    int insertBatch(@Param("entities") List<AdminImage> entities);

    /**
     * 批量新增或按主键更新数据（MyBatis原生foreach方法）
     *
     * @param entities List<AdminImage> 实例对象列表
     * @return 影响行数
     */
    int insertOrUpdateBatch(@Param("entities") List<AdminImage> entities);

    /**
     * 修改数据
     *
     * @param adminImage 实例对象
     * @return 影响行数
     */
    int update(AdminImage adminImage);

    /**
     * 通过主键删除数据
     *
     * @param name 主键
     * @return 影响行数
     */
    int deleteById(String name);

}

