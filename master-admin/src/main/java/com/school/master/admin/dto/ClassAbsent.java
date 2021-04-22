package com.school.master.admin.dto;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiModelProperty;

import java.io.Serializable;

/**
 * (TpAbsent)实体类
 *
 * @author LeeDream
 * @since 2021-03-14 22:08:31
 */
public class ClassAbsent implements Serializable {
    private static final long serialVersionUID = -90042241857490976L;
    private Integer id;
    /**
     * 班级id
     */
    @ApiModelProperty(value = "班级编号")
    private Integer pid;
    /**
     * 日期
     */
    @ApiModelProperty(value = "缺勤时间")
    private String date;
    /**
     * 是否全勤
     */
    @ApiModelProperty(value = "是否全勤(1:全勤，2:非全勤")
    private Integer full;

    public ClassAbsent() {
    }

    public ClassAbsent(Integer pid, String date, Integer full) {
        this.pid = pid;
        this.date = date;
        this.full = full;
    }

    public Integer getPid() {
        return pid;
    }

    public void setPid(Integer pid) {
        this.pid = pid;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public Integer getFull() {
        return full;
    }

    public void setFull(Integer full) {
        this.full = full;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }
}
