package com.school.master.admin.service;

import com.school.master.admin.dto.CreateCollegeParam;
import com.school.master.admin.dto.UpdateCollegeParam;
import com.school.master.model.College;

import java.util.List;

public interface CollegeService {
    int addCollege(CreateCollegeParam college);

    College queryCollege(Integer cid);

    College getCollegeByCid(Integer cid);


    List<College> pageQueryAll(Integer pageSize,Integer pageNum);

    int updateColloege(UpdateCollegeParam updateParam);

    int deleteCollege(Integer cid);

    int addCollegeList(List<CreateCollegeParam> collegeList);

    College assertCollegeNotFound(Integer cid);
}
