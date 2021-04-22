package com.school.master.admin;

import com.school.master.admin.dao.CollegeDao;
import com.school.master.admin.dao.SchoolClassDao;
import com.school.master.admin.dto.AbsentDetails;
import com.school.master.admin.dto.ClassAbsent;
import com.school.master.admin.dto.College;
import com.school.master.admin.dto.SchoolClass;
import com.school.master.admin.service.AbsentDetailsService;
import com.school.master.admin.service.ClassAbsentService;
import com.school.master.admin.service.SchoolClassService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.testng.AbstractTestNGSpringContextTests;
import org.testng.annotations.Test;

import java.util.Collection;
import java.util.List;
import java.util.concurrent.ScheduledExecutorService;

@SpringBootTest
public class MyBatisTest extends AbstractTestNGSpringContextTests {
    @Autowired
    CollegeDao collegeDao;
    @Autowired
    SchoolClassDao schoolClassDao;
    @Autowired
    SchoolClassService schoolClassServic;
    @Autowired
    AbsentDetailsService absentDetailsService;
    @Autowired
    ClassAbsentService classAbsentService;

    @Test
    public void mybatisQuery() {
        College college = collegeDao.queryAllByName("江西");
        System.out.println(college);
        List<College> colleges = collegeDao.queryAll(null);
        colleges.forEach(System.out::println);
    }


}
