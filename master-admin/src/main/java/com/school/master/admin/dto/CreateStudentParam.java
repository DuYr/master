package com.school.master.admin.dto;


import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

public class CreateStudentParam {
    @NotNull
    private Integer sid;
    @NotNull
    private Integer pid;
    @NotEmpty
    private String name;
    @NotEmpty
    private String remarks;

    public CreateStudentParam() {
    }

    public CreateStudentParam(Integer sid, Integer pid, String name, String remarks) {
        this.sid = sid;
        this.pid = pid;
        this.name = name;
        this.remarks = remarks;
    }

    public Integer getSid() {
        return sid;
    }

    public void setSid(Integer sid) {
        this.sid = sid;
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

    public String getRemarks() {
        return remarks;
    }

    public void setRemarks(String remarks) {
        this.remarks = remarks;
    }
}
