package com.school.master.admin.service.impl;

import com.github.pagehelper.PageHelper;
import com.school.master.admin.dao.CollegeDao;
import com.school.master.admin.domain.BeanOperation;
import com.school.master.admin.dto.College;
import com.school.master.admin.dto.CreateCollegeParam;
import com.school.master.admin.dto.UpdateCollegeParam;
import com.school.master.admin.service.CollegeService;
import com.school.master.admin.service.SchoolClassService;
import com.school.master.common.exception.ApiException;
import com.school.master.common.exception.Asserts;
import org.springframework.beans.factory.BeanFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service("collegeService")
@Transactional(rollbackFor = ApiException.class)
public class CollegeServiceImpl implements CollegeService {
    @Autowired
    private CollegeDao collegeDao;
    @Autowired
    private SchoolClassService schoolClassService;
    @Autowired
    private ApplicationContext applicationContext;

    @Override
    public int addCollegeList(List<CreateCollegeParam> collegeList) {
        int count = collegeList.stream().mapToInt(college ->
                applicationContext.getBean(this.getClass()).addCollege(college)).sum();
        return count;
    }

    @Override
    public int addCollege(CreateCollegeParam createParam) {
        Integer cid = createParam.getCid();
        College college = queryCollege(cid);
        if (college != null) {
            Asserts.fail("学院已存在 error:" + cid);
        }
        college = new College();
        BeanOperation.beanCopy(createParam, college);
        return collegeDao.insert(college);
    }

    @Override
    public College queryCollege(Integer cid) {
        College college = collegeDao.queryById(cid);
        return college;
    }

    @Override
    public College getCollegeByCid(Integer cid) {
        College college = collegeDao.queryById(cid);
        return college;
    }


    @Override
    public List<College> pageQueryAll(Integer pageSize, Integer pageNum) {
        PageHelper.startPage(pageNum, pageSize);
        List<College> colleges = collegeDao.queryAll(null);
        colleges.forEach(college -> {
            college.setCreateTime(null);
        });
        return colleges;
    }

    @Override
    public int updateColloege(UpdateCollegeParam updateParam) {
        Integer cid = updateParam.getCid();
        College college = assertCollegeNotFound(cid);
        BeanOperation.beanCopy(updateParam, college);
        int update = collegeDao.update(college);
        return update;
    }

    @Override
    public int deleteCollege(Integer cid) {
        int count = collegeDao.deleteById(cid);
        //删除班级
        schoolClassService.deleteClassByCollegeCid(cid);
        return count;
    }

    @Override
    public College assertCollegeNotFound(Integer cid) {
        if (cid == null) {
            return null;
        }
        College college = getCollegeByCid(cid);
        if (college == null) {
            Asserts.fail("该学院不存在 error:" + cid);
        }
        return college;
    }
}
