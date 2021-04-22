package com.school.master.admin.dto;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

public class CreateClassParam {
    @NotNull
    private Integer pid;
    @NotNull
    private Integer cid;
    @NotEmpty
    private String name;

    public CreateClassParam() {
    }

    public CreateClassParam(Integer cid, String name) {
        this.cid = cid;
        this.name = name;
    }

    public Integer getPid() {
        return pid;
    }

    public void setPid(Integer pid) {
        this.pid = pid;
    }

    public Integer getCid() {
        return cid;
    }

    public void setCid(Integer cid) {
        this.cid = cid;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
