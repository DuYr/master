package com.school.master.admin.service.impl;

import com.github.pagehelper.PageHelper;
import com.school.master.admin.dao.SchoolClassDao;
import com.school.master.admin.domain.BeanOperation;
import com.school.master.admin.dto.College;
import com.school.master.admin.dto.SchoolClass;
import com.school.master.admin.dto.UpdateClassParam;
import com.school.master.admin.service.CollegeService;
import com.school.master.admin.service.SchoolClassService;
import com.school.master.admin.service.StudentService;
import com.school.master.admin.vo.SchoolClassVo;
import com.school.master.common.exception.Asserts;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.util.ArrayList;
import java.util.List;

/**
 * (TpClass)表服务实现类
 *
 * @author LeeDream
 * @since 2021-01-02 16:17:49
 */
@Service("schoolClassService")
public class SchoolClassServiceImpl implements SchoolClassService {
    @Autowired
    private SchoolClassDao schoolClassDao;
    @Autowired
    private CollegeService collegeService;
    @Autowired
    private StudentService studentService;


    @Override
    public List<SchoolClass> queryPageList(Integer pageSize, Integer pageNum) {
        PageHelper.startPage(pageNum, pageSize);
        List<SchoolClass> schoolClassList = schoolClassDao.queryAll(null);
        return schoolClassList;

    }

    @Override
    public int addClass(Integer pid, Integer cid, String name) {
        if (collegeService.getCollegeByCid(cid) == null) {
            Asserts.fail("该学院不存在 error:" + cid);
        }
        //查询班级是否不存在
        if (getClassByPid(pid) != null) {
            Asserts.fail("班级已存在 pid:" + pid);
        }

        return schoolClassDao.insert(new SchoolClass(pid, cid, name));
    }

    /**
     * 通过班级pid删除
     *
     * @param pid
     * @return
     */

    @Override
    public int deleteClassByPid(Integer pid) {
        //判断班级是否存在
        assertClassNotFound(pid);
        //删除班级
        int count = schoolClassDao.deleteById(pid);
        //删除学生
        studentService.deleteStudentByPid(pid);
        return count;
    }

    @Override
    public SchoolClass assertClassNotFound(Integer pid) {
        if (pid == null) {
            return null;
        }
        SchoolClass schoolClass = getClassByPid(pid);
        if (schoolClass == null) {
            Asserts.fail("班级不存在 pid:" + pid);
        }
        return schoolClass;
    }

    @Override
    public SchoolClass getClassByPid(Integer pid) {
        return schoolClassDao.queryByPid(pid);
    }

    /**
     * 通过学院cid
     *
     * @param cid
     * @return
     */

    @Override
    public int deleteClassByCollegeCid(Integer cid) {
        List<SchoolClass> schoolClassList = schoolClassDao.queryByCid(cid);
        schoolClassList.forEach(schoolClass -> {
            deleteClassByPid(schoolClass.getPid());
        });
        return schoolClassDao.deleteByCid(cid);
    }

    @Override
    public List<SchoolClass> getClassByCid(Integer pageSize, Integer pageNum, Integer cid) {
        PageHelper.startPage(pageNum, pageSize);
        List<SchoolClass> schoolClassList = schoolClassDao.queryByCid(cid);
        return schoolClassList;
    }

    @Override
    public List<SchoolClass> getClassByLikeName(Integer pageSize, Integer pageNum, String name) {
        String likeName = "%" + name + "%";
        PageHelper.startPage(pageNum, pageSize);
        List<SchoolClass> schoolClassList = schoolClassDao.queryClassByLikeName(likeName);
        return schoolClassList;
    }


    @Override
    public List<SchoolClass> getCriteriaClass(Integer pid, Integer cid, String name,
                                              Integer pageSize, Integer pageNum) {
        List<SchoolClass> schoolClassList = new ArrayList<>();
        //判断班级pid(唯一值)
        if (pid != null) {
            SchoolClass classByPid = assertClassNotFound(pid);
            schoolClassList.add(classByPid);
            return schoolClassList;
        }
        //判断名字(like查询)
        if (!StringUtils.isEmpty(name)) {
            schoolClassList = getClassByLikeName(pageSize, pageNum, name);
            return schoolClassList;
        }
        //判断学院cid
        if (cid != null) {
            schoolClassList = getClassByCid(pageSize, pageNum, cid);
            return schoolClassList;

        }
        return queryPageList(pageSize, pageNum);
    }

    private List<SchoolClassVo> listTransform(List<SchoolClass> schoolClassList) {
        List<SchoolClassVo> classVoList = new ArrayList<>(schoolClassList.size());
        schoolClassList.forEach(schoolClass -> {
            classVoList.add(transform(schoolClass));
        });
        return classVoList;
    }

    @Override
    public SchoolClassVo transform(SchoolClass schoolClass) {
        SchoolClassVo schoolClassVo = BeanOperation.beanCopy(schoolClass, new SchoolClassVo());
        College college = collegeService.getCollegeByCid(schoolClass.getCid());
        college.setCreateTime(null);
        schoolClassVo.setCollege(college);
        return schoolClassVo;
    }


    @Override
    public List<SchoolClass> getClassByLikePid(Integer pid, Integer pageSize, Integer pageNum) {
        String likePid = "%" + pid + "%";
        PageHelper.startPage(pageNum, pageSize);
        return schoolClassDao.queryClassByLikePid(likePid);
    }

    @Override
    public String getClassRoomName(Integer pid) {
        SchoolClass schoolClass = assertClassNotFound(pid);
        return schoolClass.getName();
    }

    @Override
    public int updateClass(UpdateClassParam updateParam) {
        Integer pid = updateParam.getPid();
        SchoolClass schoolClass = assertClassNotFound(pid);
        //判断学院是否存在
        Integer cid = updateParam.getCid();
        if (collegeService.assertCollegeNotFound(cid) != null) {
            schoolClass.setCid(cid);
        }
        String name = updateParam.getName();
        if (name != null) {
            schoolClass.setName(name);
        }
        return schoolClassDao.update(schoolClass);
    }

    @Override
    public List<SchoolClass> getAll() {
        return schoolClassDao.queryAll(null);
    }

}