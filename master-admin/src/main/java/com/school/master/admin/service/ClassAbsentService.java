package com.school.master.admin.service;

import com.school.master.admin.dto.ClassAbsent;
import com.school.master.admin.vo.ClassAbsentFullVo;

import java.util.Date;
import java.util.List;

/**
 * (TpAbsent)表服务接口
 *
 * @author makejava
 * @since 2021-03-14 22:08:33
 */
public interface ClassAbsentService {


    List<ClassAbsentFullVo> dateRange(Integer pid, Date start, Date end, Integer pageSize, Integer pageNum);

    List<ClassAbsentFullVo> transform(List<ClassAbsent> classAbsentList);

    /**
     * 查询多条数据
     *
     * @param offset 查询起始位置
     * @param limit  查询条数
     * @return 对象列表
     */
    List<ClassAbsent> queryAllByLimit(int offset, int limit);


    int addFullAttendance(ClassAbsent classAbsent);


    int updateFullAttendance(Integer pid, String today, boolean full);

    /**
     * 通过主键删除数据
     *
     * @param pid 主键
     * @return 是否成功
     */
    boolean deleteById(Integer pid);

    int addOrChange(Integer pid, String today, boolean full);

    int addFullAttendance(Integer pid, String date, boolean full);

    List<ClassAbsent> getClassAbsernt(Integer pid, String date);
}
