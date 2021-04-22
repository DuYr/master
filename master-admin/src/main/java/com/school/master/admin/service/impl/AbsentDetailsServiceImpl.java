package com.school.master.admin.service.impl;

import cn.hutool.core.collection.CollectionUtil;
import com.github.pagehelper.PageHelper;
import com.school.master.admin.dao.AbsentDetailsDao;
import com.school.master.admin.domain.BeanOperation;
import com.school.master.admin.domain.CriteriaGenerate;
import com.school.master.admin.domain.DutyEnum;
import com.school.master.admin.dto.*;
import com.school.master.admin.service.AbsentDetailsService;
import com.school.master.admin.service.SchoolClassService;
import com.school.master.admin.service.StudentService;
import com.school.master.admin.vo.StudentAbsentVo;
import com.school.master.admin.vo.StudentVo;
import com.school.master.common.exception.ApiException;
import com.school.master.common.exception.Asserts;
import com.school.master.common.utils.DateVaildUtil;
import com.school.master.common.utils.MyStringUils;
import com.school.master.common.utils.NumberRangeVaildUtil;
import net.logstash.logback.encoder.org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 缺勤详情(AbsentDetails)表服务实现类
 *
 * @author LeeDream
 * @since 2021-01-09 19:00:36
 */
@Service("absentDetailsService")
@Transactional(rollbackFor = ApiException.class)
public class AbsentDetailsServiceImpl implements AbsentDetailsService {
    @Autowired
    private AbsentDetailsDao absentDetailsDao;
    @Autowired
    private StudentService studentService;
    @Autowired
    private SchoolClassService schoolClassService;
    @Autowired
    private ApplicationContext applicationContext;

    @Override
    public int addAbsentList(List<CreateAbsentParam> createParamList) {
        int count = createParamList.stream().mapToInt(createParam ->
                applicationContext.getBean(this.getClass()).addAbsent(createParam)
        ).sum();
        return count;
    }

    @Override
    public int addAbsent(CreateAbsentParam createParam) {
        AbsentDetails absentDetails = argsLegal(createParam.getSid(), DateVaildUtil.formatString(createParam.getDate()),
                createParam.getCourse(), createParam.getReason(), createParam.getRemarks());
        if (!CollectionUtil.isEmpty(
                dateRangeStudent(absentDetails.getSid(), absentDetails.getDate(), absentDetails.getDate(), 1, 0))) {
            Asserts.fail("添加失败,学号" + absentDetails.getSid() + ",日期" + absentDetails.getDate() + "记录已存在");
        }
        if (StringUtils.isEmpty(absentDetails.getRemarks())) {
            absentDetails.setRemarks("未备注");
        }
        int count = absentDetailsDao.insert(absentDetails);
        return count;
    }

    @Override
    public int deleteList(List<DeleteAbsentParam> deleteParams) {
        int count = deleteParams.stream().mapToInt(deleteParam ->
                applicationContext.getBean(this.getClass()).deleteAbsent(deleteParam)).sum();
        return count;
    }


    @Override
    public int deleteAbsent(DeleteAbsentParam deleteParam) {
        String formatDate = DateVaildUtil.formatString(deleteParam.getDate());
        return absentDetailsDao.deleteBySidAndDate(deleteParam.getSid(), formatDate);
    }

    @Override
    public int update(UpdateAbsentDetailsParam updateParam) {

        AbsentDetails absentDetails = argsLegal(updateParam.getSid(), DateVaildUtil.formatString(updateParam.getDate()), updateParam.getCourse(), updateParam.getReason(), updateParam.getRemarks());
        List<AbsentDetails> absentDetailsList = dateRangeStudent(absentDetails.getSid(), absentDetails.getDate(), absentDetails.getDate(), 1, 0);
        if (CollectionUtil.isEmpty(absentDetailsList)) {
            Asserts.fail("修改失败,学号" + absentDetails.getSid() + ",日期" + absentDetails.getDate() + "记录不存在");
        }
        int update = absentDetailsDao.update(absentDetails);
        return update;
    }

    /**
     * 通过ID查询单条数据
     *
     * @param id 主键
     * @return 实例对象
     */
    @Override
    public AbsentDetails queryById(Integer id) {
        return this.absentDetailsDao.queryById(id);
    }

    @Override
    public List<AbsentDetails> queryByLikeSidAndDate(Integer pid, String date, Integer pageSize, Integer pageNum) {
        String likeSid = new CriteriaGenerate().add(pid.toString()).builder();
        PageHelper.startPage(pageNum, pageSize);
        return absentDetailsDao.queryAllByLikeSidAndDate(likeSid, date);
    }

    private String reasonVaild(String reason) {
        if (StringUtils.isEmpty(reason)) {
            return reason;
        }
        for (DutyEnum dutyEnum : DutyEnum.values()) {
            if (dutyEnum.getSituation().equals(reason)) {
                return reason;
            }
        }
        Asserts.fail("请选择正确的缺勤原因");
        return null;
    }

    private String courseVaild(String course) {
        if (StringUtils.isEmpty(course)) {
            return course;
        }
        course = MyStringUils.convertHalfSize(course);
        String deleteWhitespace = StringUtils.deleteWhitespace(course);
        String[] split = deleteWhitespace.split(",");
        if (split.length >= 12) {
            Asserts.fail("超出合法课节长度");
        }
        for (String s : split) {
            if (!NumberRangeVaildUtil.input(s, 1, 11)) {
                Asserts.fail("没有此节课:" + s);
            }
            if (MyStringUils.findStringCount(s, course) != 1) {
                Asserts.fail("课节冲突:" + s);
            }
        }
        return course;
    }


    @Override
    public List<StudentVo> search(Integer umit, Integer id, String name, Integer pageSize, Integer pageNum) {
        List<Student> studentList = new ArrayList<>();
        if (umit == 0) {
            studentList = searchStudent(id, name, pageSize, pageNum);
        } else if (umit == 1) {
            studentList = searchClass(id, name, pageSize, pageNum);
        } else {
            Asserts.fail("请选择班级或学生进行搜索");
        }
        List<StudentVo> studentVoList = new ArrayList<>();
        studentList.forEach(student -> {
            StudentVo studentVo = BeanOperation.beanCopy(student, new StudentVo());
            studentVo.setRoomName(schoolClassService.getClassRoomName(student.getPid()));
            studentVoList.add(studentVo);
        });
        return studentVoList;
    }

    @Override
    public List<Student> searchClass(Integer pid, String name, Integer pageSize, Integer pageNum) {
        List<SchoolClass> schoolClasses = new ArrayList<>();
        if (pid != null) {
            schoolClasses = schoolClassService.getClassByLikePid(pid, pageSize, pageNum);
        } else if (name != null && !"null".equals(name)) {
            schoolClasses = schoolClassService.getClassByLikeName(pageSize, pageNum, name);
        } else {
            Asserts.fail("参数为空");
        }
        if (CollectionUtil.isEmpty(schoolClasses)) {
            Asserts.fail("未找到该关键字班级");
        }
        List<Student> studentList = new ArrayList<>();
        schoolClasses.stream().forEach(school -> studentList.addAll(studentService.getStudentsByPid(school.getPid(), 100, 0)));
        return studentList;
    }

    @Override
    public List<Student> searchStudent(Integer sid, String name, Integer pageSize, Integer pageNum) {
        List<Student> studentList = new ArrayList<>();
        if (sid != null) {
            studentList = studentService.getStudentsByLikeSid(sid, pageSize, pageNum);
        } else if (name != null) {
            studentList = studentService.getStudentsByLikeName(name, pageSize, pageNum);
        } else {
            Asserts.fail("参数为空");
        }
        return studentList;
    }


    private Map<Student, List<AbsentDetails>> getSearchResults(List<Student> studentList, Integer
            pageSize, Integer pageNum) {
        Map<Student, List<AbsentDetails>> absentDetailsMap = new HashMap<>(60);
        if (CollectionUtil.isEmpty(studentList)) {
            Asserts.fail("找不到学生记录");
        }
        studentList.stream().forEach(student -> {
            List<AbsentDetails> absentDetailsList = getAllBySid(student.getSid(), pageNum, pageSize);
            if (!CollectionUtil.isEmpty(absentDetailsList)) {
                absentDetailsMap.put(student, absentDetailsList);
            }
        });
        return absentDetailsMap;
    }

    @Override
    public List<AbsentDetails> getAllBySid(Integer sid, Integer pageNum, Integer pageSize) {
        PageHelper.startPage(pageNum, pageSize);
        AbsentDetails absentDetails = new AbsentDetails();
        absentDetails.setSid(sid);
        PageHelper.startPage(pageNum, pageSize);
        return absentDetailsDao.queryAll(absentDetails);
    }

    @Override
    public List<AbsentDetails> dateRangeStudent(Integer sid, String start, String end, Integer pageSize, Integer pageNum) {
        studentService.studentsNotExist(sid);
        PageHelper.startPage(pageNum, pageSize);
        return absentDetailsDao.queryAllByDateRange(sid, start, end);

    }

    /**
     * 学生学期考勤
     *
     * @param sid
     * @param choice
     * @param start
     * @param end
     * @return
     */
    @Override
    public List<AbsentDetails> semesterStudent(Integer sid, Integer choice, Integer start, Integer end, Integer
            pageSize, Integer pageNum) {
        if (end - start != 1) {
            Asserts.fail("学期范围:" + start + "-" + end + "不正确");
        }
        studentService.studentsNotExist(sid);
        String[] terMonth = getSemesterMonth(choice, start, end);
        List<AbsentDetails> detailsList = dateRangeStudent(sid, terMonth[0], terMonth[1], pageSize, pageNum);
        return detailsList;
    }

    @Override
    public List<StudentAbsentVo> dateRangeClass(Integer pid, String start, String end, Integer pageSize, Integer pageNum) {
        schoolClassService.assertClassNotFound(pid);
        List<Student> studentList = studentService.getStudentsByPid(pid, pageSize, pageNum);
        if (CollectionUtil.isEmpty(studentList)) {
            Asserts.fail("找不到学生记录");
        }
        List<StudentAbsentVo> absentVoList = new ArrayList<>(60);
        studentList.forEach(student -> {
            List<AbsentDetails> detailsList = dateRangeStudent(student.getSid(), start, end, 100, 0);
            if (!CollectionUtil.isEmpty(detailsList)) {
                StudentAbsentVo absentVo = new StudentAbsentVo(student, detailsList);
                absentVoList.add(absentVo);
            }
        });
        return absentVoList;
    }

    /**
     * 学期
     *
     * @param pid
     * @param choice
     * @param start
     * @param end
     * @param pageSize
     * @param pageNum
     * @return
     */

    @Override
    public List<StudentAbsentVo> semesterClass(Integer pid, Integer choice, Integer start, Integer end, Integer
            pageSize, Integer pageNum) {
        String[] dataRange = getSemesterMonth(choice, start, end);
        List<StudentAbsentVo> absentVoList = dateRangeClass(pid, dataRange[0], dataRange[1], pageSize, pageNum);
        return absentVoList;
    }

    /**
     * @param choice {1：上学期，2：下学期}
     * @return
     */

    private String[] getSemesterMonth(Integer choice, Integer start, Integer end) {
        String[] dateRange = new String[2];
        CriteriaGenerate criteriaGenerate = new CriteriaGenerate();
        if (choice == 1) {
            dateRange = criteriaGenerate.lastSemester(start, end);
        } else if (choice == 2) {
            dateRange = criteriaGenerate.nextSemester(start, end);
        }
        return dateRange;
    }

    private AbsentDetails argsLegal(Integer sid, String date, String course, String reason, String remarks) {
        AbsentDetails absentDetails = new AbsentDetails();
        studentService.studentsNotExist(sid);
        absentDetails.setSid(sid);
        absentDetails.setDate(date);
        absentDetails.setCourse(courseVaild(course));
        absentDetails.setReason(reasonVaild(reason));
        absentDetails.setRemarks(remarks);
        return absentDetails;
    }
}