package com.school.master.admin.vo;


import com.school.master.model.College;
import com.school.master.model.SchoolClass;

public class SchoolClassVo extends SchoolClass {
    /**
     * 主键，班级id
     */
    private Integer pid;
    /**
     * 名称
     */
    private String name;
    private College college;

    public College getCollege() {
        return college;
    }

    public void setCollege(College college) {
        this.college = college;
    }

    public Integer getPid() {
        return pid;
    }

    public void setPid(Integer pid) {
        this.pid = pid;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
