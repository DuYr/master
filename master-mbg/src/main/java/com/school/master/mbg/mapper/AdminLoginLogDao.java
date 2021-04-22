package com.school.master.mbg.mapper;

import com.school.master.admin.dto.AdminLoginLog;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * (TpLogLogin)表数据库访问层
 *
 * @author makejava
 * @since 2020-12-30 13:03:58
 */
public interface AdminLoginLogDao {

    /**
     * 通过ID查询单条数据
     *
     * @param id 主键
     * @return 实例对象
     */
    AdminLoginLog queryById(Integer id);

    /**
     * 查询指定行数据
     *
     * @param offset 查询起始位置
     * @param limit  查询条数
     * @return 对象列表
     */
    List<AdminLoginLog> queryAllByLimit(@Param("offset") int offset, @Param("limit") int limit);

    /**
     * 查询所有
     * @return
     */

    List<AdminLoginLog> queryAll();

    /**
     * 通过实体作为筛选条件查询
     *
     * @param adminLoginLog 实例对象
     * @return 对象列表
     */
    List<AdminLoginLog> queryAllByDto(AdminLoginLog adminLoginLog);

    /**
     * 新增数据
     *
     * @param adminLoginLog 实例对象
     * @return 影响行数
     */
    int insert(AdminLoginLog adminLoginLog);

    /**
     * 修改数据
     *
     * @param adminLoginLog 实例对象
     * @return 影响行数
     */
    int update(AdminLoginLog adminLoginLog);

    /**
     * 通过主键删除数据
     *
     * @param id 主键
     * @return 影响行数
     */
    int deleteById(Integer id);

}