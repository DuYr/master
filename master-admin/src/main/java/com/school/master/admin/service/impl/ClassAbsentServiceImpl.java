package com.school.master.admin.service.impl;

import cn.hutool.core.collection.CollectionUtil;
import com.github.pagehelper.PageHelper;
import com.school.master.admin.dao.ClassAbsentDao;
import com.school.master.admin.domain.BeanOperation;
import com.school.master.admin.dto.AbsentDetails;
import com.school.master.admin.dto.ClassAbsent;
import com.school.master.admin.dto.Student;
import com.school.master.admin.service.ClassAbsentService;
import com.school.master.admin.service.SchoolClassService;
import com.school.master.admin.vo.ClassAbsentFullVo;
import com.school.master.common.utils.DateVaildUtil;
import com.school.master.execl.column.AbsenceColumn;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * (TpAbsent)表服务实现类
 *
 * @author makejava
 * @since 2021-03-14 22:08:34
 */
@Service("classAbsentService")
public class ClassAbsentServiceImpl implements ClassAbsentService {
    @Autowired
    private ClassAbsentDao classAbsentDao;
    @Autowired
    private ClassAbsentFullVo classAbsentFullVo;
    @Autowired
    private SchoolClassService schoolClassService;

    /**
     * 范围查询班级全勤记录
     *
     * @param pid
     * @param start
     * @param end
     * @param pageSize
     * @param pageNum
     * @return
     */

    @Override
    public List<ClassAbsentFullVo> dateRange(Integer pid, Date start, Date end, Integer pageSize, Integer pageNum) {
        List<ClassAbsent> classAbsentList;
        if (start == null && end == null) {
            PageHelper.startPage(pageNum, pageSize);
            classAbsentList = classAbsentDao.queryAll(null);
            return transform(classAbsentList);
        }
        Date[] dates = {start, end};
        if (start != null && end == null) {
            dates[0] = start;
            dates[1] = start;
        }
        PageHelper.startPage(pageNum, pageSize);
        classAbsentList = classAbsentDao.queryByRange(pid, DateVaildUtil.formatString(dates[0]), DateVaildUtil.formatString(dates[1]));
        return transform(classAbsentList);
    }


    @Override
    public List<ClassAbsentFullVo> transform(List<ClassAbsent> classAbsentList) {
        List<ClassAbsentFullVo> resultList = new ArrayList<>();
        ClassAbsentFullVo temp = null;
        for (int i = 0; i < classAbsentList.size(); i++) {
            ClassAbsent absent = classAbsentList.get(i);
            ClassAbsentFullVo fullVo = BeanOperation.beanCopy(absent, new ClassAbsentFullVo());
            fullVo.setDate(absent.getDate());
            if (temp != null && temp.getPid().equals(fullVo.getPid())) {
                fullVo.setName(temp.getName());
            } else {
                String roomName = schoolClassService.getClassRoomName(absent.getPid());
                fullVo.setName(roomName);
                temp = fullVo;
            }
            resultList.add(fullVo);
        }
        return resultList;
//        List<ClassAbsentFullVo> fullVoList = new ArrayList<>();
//        classAbsentList.stream().forEach(classAbsent -> fullVoList.add(new ClassAbsentFullVo(classAbsent.getId(),
//                classAbsent.getDate(),
//                schoolClassService.getClassRoomName(classAbsent.getPid()),
//                classAbsent.getFull())));
//        return fullVoList;
    }


    /**
     * 查询多条数据
     *
     * @param offset 查询起始位置
     * @param limit  查询条数
     * @return 对象列表
     */
    @Override
    public List<ClassAbsent> queryAllByLimit(int offset, int limit) {
        return this.classAbsentDao.queryAllByLimit(offset, limit);
    }

    /**
     * 新增数据
     *
     * @param classAbsent 实例对象
     * @return 实例对象
     */
    @Override
    public int addFullAttendance(ClassAbsent classAbsent) {
        schoolClassService.assertClassNotFound(classAbsent.getPid());
        return classAbsentDao.insert(classAbsent);
    }


    /**
     * 通过主键删除数据
     *
     * @param pid 主键
     * @return 是否成功
     */
    @Override
    public boolean deleteById(Integer pid) {
        return this.classAbsentDao.deleteById(pid) > 0;
    }

    /**
     * @param pid
     * @param full true : 1,false :0
     */

    @Override
    public int addOrChange(Integer pid, String today, boolean full) {
        List<ClassAbsent> classAbsernt = getClassAbsernt(pid, today);
        int count = 0;
        //记录不存在
        if (CollectionUtil.isEmpty(classAbsernt)) {
            count = addFullAttendance(pid, today, full);
        } else {
            count = updateFullAttendance(pid, today, full);
        }
        return count;
    }

    @Override
    public int addFullAttendance(Integer pid, String date, boolean full) {
        ClassAbsent classAbsent = createObject(pid, date, full);
        return addFullAttendance(classAbsent);
    }

    @Override
    public int updateFullAttendance(Integer pid, String date, boolean full) {
        ClassAbsent classAbsent = createObject(pid, date, full);
        return this.classAbsentDao.update(classAbsent);
    }

    @Override
    public List<ClassAbsent> getClassAbsernt(Integer pid, String date) {
        ClassAbsent classAbsent = new ClassAbsent();
        classAbsent.setPid(pid);
        classAbsent.setDate(date);
        return classAbsentDao.queryAll(classAbsent);
    }

    private ClassAbsent createObject(Integer pid, String date, boolean full) {
        ClassAbsent classAbsent = new ClassAbsent();
        classAbsent.setPid(pid);
        classAbsent.setDate(date);
        if (full) {
            classAbsent.setFull(1);
        } else {
            classAbsent.setFull(0);
        }
        return classAbsent;
    }

}
