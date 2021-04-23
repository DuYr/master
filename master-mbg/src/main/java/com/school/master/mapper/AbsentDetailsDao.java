package com.school.master.mapper;

import com.school.master.model.AbsentDetails;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * 缺勤详情(TpAbsentDetails)表数据库访问层
 *
 * @author makejava
 * @since 2021-01-09 19:00:35
 */
public interface AbsentDetailsDao {

    /**
     * 通过ID查询单条数据
     *
     * @param id 主键
     * @return 实例对象
     */
    AbsentDetails queryById(Integer id);

    /**
     * 查询指定行数据
     *
     * @param offset 查询起始位置
     * @param limit  查询条数
     * @return 对象列表
     */
    List<AbsentDetails> queryAllByLimit(@Param("offset") int offset, @Param("limit") int limit);


    /**
     * 通过实体作为筛选条件查询
     *
     * @param absentDetails 实例对象
     * @return 对象列表
     */
    List<AbsentDetails> queryAll(AbsentDetails absentDetails);

    /**
     * 新增数据
     *
     * @param absentDetails 实例对象
     * @return 影响行数
     */
    int insert(AbsentDetails absentDetails);

    /**
     * 修改数据
     *
     * @param absentDetails 实例对象
     * @return 影响行数
     */
    int update(AbsentDetails absentDetails);

    /**
     * 通过主键删除数据
     *
     * @param id 主键
     * @return 影响行数
     */
    int deleteById(Integer id);

    int deleteBySidAndDate(@Param("sid") Integer sid, @Param("date") String date);

    /**
     * 通过开始和结束日期查询
     *
     * @param sid
     * @param startDate
     * @param endDate
     * @return
     */

    List<AbsentDetails> queryAllByDateRange(@Param("sid") Integer sid, @Param("startDate") String startDate, @Param("endDate") String endDate);

    /**
     * 查询日期所在周
     *
     * @param sid
     * @param date
     * @return
     */

    List<AbsentDetails> queryAllByDateWeek(@Param("sid") Integer sid, @Param("date") String date);

    /**
     * 查询日期月
     *
     * @param sid
     * @param date
     * @return
     */

    List<AbsentDetails> queryAllByDateMonth(@Param("sid") Integer sid, @Param("date") String date);

    List<AbsentDetails> queryAllByLikeSidAndDate(@Param("likeSid") String likeSid, @Param("date") String date);
}