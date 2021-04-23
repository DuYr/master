package com.school.master.admin;

import com.school.master.admin.service.AbsentDetailsService;
import com.school.master.admin.service.ClassAbsentService;
import com.school.master.admin.service.SchoolClassService;
import com.school.master.mapper.CollegeDao;
import com.school.master.mapper.SchoolClassDao;
import com.school.master.model.College;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.testng.AbstractTestNGSpringContextTests;
import org.testng.annotations.Test;

import java.util.List;

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
