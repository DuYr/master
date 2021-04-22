package com.school.master.admin.dto;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

public class UpdateClassParam {
    @NotNull
    private Integer pid;
    private Integer cid;
    private String name;

    public UpdateClassParam() {
    }

    public UpdateClassParam(@NotNull Integer pid, @NotNull Integer cid, @NotEmpty String name) {
        this.pid = pid;
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
