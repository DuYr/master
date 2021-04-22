package com.school.master.admin.service;


import com.school.master.admin.dto.CreateStudentParam;
import com.school.master.admin.dto.Student;
import com.school.master.admin.dto.UpdateStudentParam;

import java.util.List;

public interface StudentService {
    /**
     * 条件分页查询
     *
     * @param sid
     * @param pid
     * @param name
     * @param pageSize
     * @param pageNum
     * @return
     */

    List<Student> getStudentsByCriteria(Integer sid, Integer pid, String name, Integer pageSize, Integer pageNum);

    /**
     * 分页查询
     *
     * @param pageSize
     * @param pageNum
     * @return
     */

    List<Student> getStudentsByPage(Integer pageSize, Integer pageNum);

    List<Student> getStudentsByPid(Integer pid, Integer pageSize, Integer pageNum);

    Student getStudentBySid(Integer sid);


    Student studentsNotExist(Integer sid);

    List<Student> getStudentsByLikeName(String name, Integer pageSize, Integer pageNum);

    List<Student> getAllStudentByPid(Integer pid);

    int addStudentList(List<CreateStudentParam> studentsParamList);

    int deleteStudentList(List<Integer> sidList);

    /**
     *
     * 通过学生sid删除学生
     * @param sid
     * @return
     */

    int deleteStudentBySid(Integer sid);

    int updateStudent(UpdateStudentParam updateStudentParam);

    /**
     * 通过班级pid删除学生
     * @param pid
     * @return
     */

    int deleteStudentByPid(Integer pid);

    List<Student> getStudentsByLikeSid(Integer sid,Integer pageSize,Integer pageNum);

}
