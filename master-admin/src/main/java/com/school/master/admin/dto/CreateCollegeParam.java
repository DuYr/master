package com.school.master.admin.dto;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

public class CreateCollegeParam {
    @NotNull
    private Integer cid;
    /**
     * 二级学院名称
     */
    @NotEmpty
    private String name;

    public CreateCollegeParam() {
    }

    public CreateCollegeParam(Integer cid, String name) {
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
