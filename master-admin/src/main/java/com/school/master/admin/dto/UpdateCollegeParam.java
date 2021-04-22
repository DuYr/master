package com.school.master.admin.dto;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

public class UpdateCollegeParam {
    @NotNull
    private Integer cid;
    @NotEmpty
    private String name;

    public UpdateCollegeParam() {
    }

    public UpdateCollegeParam(Integer cid, String name) {
        this.cid = cid;
        this.name = name;
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
