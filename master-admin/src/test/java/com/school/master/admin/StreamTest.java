package com.school.master.admin;

import com.school.master.admin.service.CollegeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.testng.AbstractTestNGSpringContextTests;
import org.testng.annotations.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

@SpringBootTest
public class StreamTest extends AbstractTestNGSpringContextTests {
    @Autowired
    CollegeService collegeService;

    @Test
    public void streamTest() {
        List<String> list = Arrays.asList("hello", "world", "stream");
        list.stream().map(item -> item + item).collect(Collectors.toList()).forEach(System.out::println);
        list.stream().map(item -> item + item).collect(
                ArrayList::new,
                (list1, value) -> list1.add(value),
                (list1, list2) -> list1.addAll(list2)
        ).forEach(System.out::println);
    }

    @Test
    public void lamabaHandle() {
//        Map<Integer, String> collegeInfoMap = collegeService.getCollegeInfoMap();
//        collegeInfoMap.forEach((k, v) -> System.out.println(k + ":" + v));
    }

}
