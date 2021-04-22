package com.school.master.admin.service.impl;

import com.github.pagehelper.PageHelper;
import com.school.master.admin.domain.CriteriaGenerate;
import com.school.master.common.api.ResultStatu;
import com.school.master.common.exception.ApiException;
import com.school.master.common.exception.Asserts;
import com.school.master.admin.dao.StudentDao;
import com.school.master.admin.domain.BeanOperation;
import com.school.master.admin.dto.CreateStudentParam;
import com.school.master.admin.dto.Student;
import com.school.master.admin.dto.UpdateStudentParam;
import com.school.master.admin.service.StudentService;
import com.school.master.admin.service.SchoolClassService;
import net.logstash.logback.encoder.org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;

@Service("studentService")
@Transactional(rollbackFor = ApiException.class)
public class StudentServiceImpl implements StudentService {

    @Autowired
    private StudentDao studentDao;
    @Autowired
    private SchoolClassService schoolClassService;

    @Override
    public List<Student> getStudentsByCriteria(Integer sid, Integer pid, String name, Integer pageSize, Integer pageNum) {
        //判断sid存在
        List<Student> studentList;
        if (sid != null) {
            Student student = getStudentBySid(sid);
            if (student == null) {
                Asserts.fail("该学生不存在 error:" + sid);
            }
            studentList = new ArrayList<>();
            studentList.add(student);
            return studentList;
        }
        //name判空和pid(班级id)不为空
        if (!StringUtils.isEmpty(name)) {
            studentList = getStudentsByLikeName(name, pageSize, pageNum);
            return studentList;
        }
        //判断pid存在
        if (pid != null) {
            studentList = getStudentsByPid(pid, pageSize, pageNum);
            return studentList;
        }
        //直接分页查询
        studentList = getStudentsByPage(pageSize, pageNum);
        return studentList;
    }

    @Override
    public List<Student> getStudentsByPage(Integer pageSize, Integer pageNum) {
        PageHelper.startPage(pageNum, pageSize);
        List<Student> studentList = studentDao.queryAll();
        return studentList;
    }

    @Override
    public List<Student> getStudentsByPid(Integer pid, Integer pageSize, Integer pageNum) {
        Student student = new Student();
        student.setPid(pid);
        PageHelper.startPage(pageNum, pageSize);
        List<Student> studentList = studentDao.queryAllStudents(student);
        return studentList;
    }

    @Override
    public Student getStudentBySid(Integer sid) {
        return studentDao.selectByPrimaryKey(sid);
    }

    @Override
    public Student studentsNotExist(Integer sid) {
        Student student = getStudentBySid(sid);
        if (student == null) {
            Asserts.fail("sid:" + sid + " 学生不存在");
        }
        return student;
    }

    @Override
    public List<Student> getStudentsByLikeName(String name, Integer pageSize, Integer pageNum) {
        String likeName = new CriteriaGenerate().add(name).builder();
        PageHelper.startPage(pageNum, pageSize);
        List<Student> studentList = studentDao.queryAllByLikeName(likeName);
        return studentList;
    }

    @Override
    public List<Student> getAllStudentByPid(Integer pid ) {
        return studentDao.queryAllByPid(pid);
    }


    @Override
    public int addStudentList(List<CreateStudentParam> createParamList) {
        AtomicInteger count = new AtomicInteger();
        createParamList.forEach(createParam -> {
            schoolClassService.assertClassNotFound(createParam.getPid());
            Integer sid = createParam.getSid();
            if (!(StringUtils.substring(String.valueOf(sid), 0, String.valueOf(sid).length() - 2)
                    .equals(createParam.getPid().toString()))) {
                Asserts.fail("学号不合法 error:" + sid);
            }
            Student studentBySid = getStudentBySid(sid);
            if (studentBySid != null) {
                Asserts.fail("该学生已存在 error:" + sid);
            }
            Student student = BeanOperation.beanCopy(createParam, new Student());
            int insert = studentDao.insert(student);
            count.addAndGet(insert);
        });
        return count.get();
    }

    @Override
    public int deleteStudentList(List<Integer> sidList) {
        int count = sidList.stream().mapToInt(sid -> deleteStudentBySid(sid)).sum();
        return count;
    }

    @Override
    public int deleteStudentBySid(Integer sid) {
        Student studentBySid = getStudentBySid(sid);
        if (studentBySid == null) {
            Asserts.fail("该学生不存在 error:" + sid);
        }
        int count = studentDao.deleteByPrimaryKey(sid);
        return count;
    }

    @Override
    public int updateStudent(UpdateStudentParam updateParam) {
        Integer sid = updateParam.getSid();
        //按学号查询
        Student student = getStudentBySid(sid);
        if (student == null) {
            Asserts.fail("该学生不存在 error:" + sid);
        }
        //判断更改的pid合法
        Integer pid = updateParam.getPid();
        if (!student.getPid().equals(pid)) {
            if (schoolClassService.assertClassNotFound(pid) != null) {
                student.setPid(pid);
            }
        } else {
            Asserts.fail("班级未更改 error:" + pid);
        }
        String newName = updateParam.getNewName();
        if (!student.getName().equals(newName)) {
            student.setName(newName);
        } else {
            Asserts.fail("名字未更改 error:" + newName);
        }
        String remarks = updateParam.getRemarks();
        if (!student.getRemarks().equals(updateParam)) {
            student.setRemarks(remarks);
        } else {
            Asserts.fail("描述未更改 error:" + remarks);
        }
        return studentDao.updateByStudent(student);
    }

    @Override
    public int deleteStudentByPid(Integer pid) {
        int count = studentDao.deleteByPid(pid);
        return count;
    }

    @Override
    public List<Student> getStudentsByLikeSid(Integer sid, Integer pageSize, Integer pageNum) {
        String likeSid = new CriteriaGenerate().add(sid.toString()).builder();
        PageHelper.startPage(pageNum, pageSize);
        return studentDao.queryAllByLikeSid(likeSid);
    }

}
