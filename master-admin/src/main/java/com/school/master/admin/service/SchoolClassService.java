package com.school.master.admin.service;

import com.school.master.admin.dto.UpdateClassParam;
import com.school.master.admin.vo.SchoolClassVo;
import com.school.master.model.SchoolClass;

import java.util.List;


public interface SchoolClassService {


    List<SchoolClass> queryPageList(Integer pageSize, Integer pageNum);

    int addClass(Integer pid, Integer cid, String name);

    int deleteClassByPid(Integer pid);

    SchoolClass assertClassNotFound(Integer pid);

    SchoolClass getClassByPid(Integer pid);

    int deleteClassByCollegeCid(Integer cid);

    List<SchoolClass> getClassByCid(Integer pageSize, Integer pageNum, Integer cid);

    List<SchoolClass> getClassByLikeName(Integer pageSize, Integer pageNum, String name);


    List<SchoolClass> getCriteriaClass(Integer pid, Integer cid, String name, Integer pageSize, Integer pageNum);

    String getClassRoomName(Integer pid);

    int updateClass(UpdateClassParam updateParam);

    SchoolClassVo transform(SchoolClass schoolClass);

    List<SchoolClass> getClassByLikePid(Integer id, Integer pageSize, Integer pageNum);

    List<SchoolClass> getAll();


}