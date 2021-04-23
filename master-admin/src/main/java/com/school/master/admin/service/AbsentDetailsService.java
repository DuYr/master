package com.school.master.admin.service;

import com.school.master.admin.dto.*;
import com.school.master.admin.vo.StudentAbsentVo;
import com.school.master.admin.vo.StudentVo;
import com.school.master.model.AbsentDetails;
import com.school.master.model.Student;

import java.util.List;

/**
 * 缺勤详情(AbsentDetails)表服务接口
 *
 * @author makejava
 * @since 2021-01-09 19:00:36
 */
public interface AbsentDetailsService {

    /**
     * 通过ID查询单条数据
     *
     * @param id 主键
     * @return 实例对象
     */
    AbsentDetails queryById(Integer id);


    int deleteAbsent(DeleteAbsentParam deleteParam);

    int update(UpdateAbsentDetailsParam updateAbsentDetailsParam);

    int deleteList(List<DeleteAbsentParam> deleteParams);


    int addAbsent(CreateAbsentParam createAbsentParam);


    List<AbsentDetails> queryByLikeSidAndDate(Integer pid, String date, Integer pageSize, Integer pageNum);


    List<AbsentDetails> getAllBySid(Integer sid, Integer pageNum, Integer pageSize);

    List<StudentVo> search(Integer umit, Integer id, String name, Integer pageSize, Integer pageNum);

    List<Student> searchClass(Integer pid, String name, Integer pageSize, Integer pageNum);

    List<Student> searchStudent(Integer sid, String name, Integer pageSize, Integer pageNum);

    List<AbsentDetails> semesterStudent(Integer id, Integer choice, Integer start, Integer end, Integer pageSize, Integer pageNum);

    List<StudentAbsentVo> semesterClass(Integer id, Integer choice, Integer start, Integer end, Integer pageSize, Integer pageNum);


    List<StudentAbsentVo> dateRangeClass(Integer id, String start, String end, Integer pageSize, Integer pageNum);


    List<AbsentDetails> dateRangeStudent(Integer sid, String start, String end, Integer pageSize, Integer pageNum);

    int addAbsentList(List<CreateAbsentParam> createParamList);

}