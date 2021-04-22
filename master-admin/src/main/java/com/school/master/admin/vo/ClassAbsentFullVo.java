package com.school.master.admin.vo;

import com.school.master.admin.dto.ClassAbsent;
import com.school.master.admin.service.SchoolClassService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

/**
 * @FileName: ClassAbsentFull
 * @Author: LeeDream
 * @Date: 2021/3/15 11:09
 * @Description: 展示班级全勤数据
 * @Version 1.0.0
 */
@Component
public class ClassAbsentFullVo extends ClassAbsent {
    private String name;

    public ClassAbsentFullVo() {
    }

    public ClassAbsentFullVo(Integer id, String date, String name, Integer full) {
        this.setId(id);
        this.setDate(date);
        this.setFull(full);
        this.name = name;
    }


    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

}
