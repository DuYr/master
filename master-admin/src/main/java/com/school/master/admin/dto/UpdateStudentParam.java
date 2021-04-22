package com.school.master.admin.dto;

import javax.validation.constraints.NotNull;

public class UpdateStudentParam {
    @NotNull
    private Integer sid;
    private Integer pid;
    private String newName;
    private String remarks;

    public UpdateStudentParam() {
    }

    public UpdateStudentParam(@NotNull Integer sid, Integer pid, String newName) {
        this.sid = sid;
        this.pid = pid;
        this.newName = newName;
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

    public String getNewName() {
        return newName;
    }

    public void setNewName(String newName) {
        this.newName = newName;
    }

    public String getRemarks() {
        return remarks;
    }

    public void setRemarks(String remarks) {
        this.remarks = remarks;
    }
}
