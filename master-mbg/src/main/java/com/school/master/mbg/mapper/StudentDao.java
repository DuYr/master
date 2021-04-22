package com.school.master.mbg.mapper;

import com.school.master.admin.dto.Student;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface StudentDao {
    int deleteByPrimaryKey(Integer sid);

    int insert(Student record);

    int insertSelective(Student record);

    Student selectByPrimaryKey(Integer sid);

    int updateByStudent(Student record);

    int updateByPrimaryKey(Student record);

    List<Student> queryAllStudents(Student student);

    List<Student> queryAllByLikeName(@Param(value = "likeName") String likeName);

    List<Student> queryAll();


    int deleteByPid(Integer pid);

    List<Student> queryAllByLikeSid(@Param("likeSid") String likeSid);

    List<Student> queryAllByPid(Integer pid);
}