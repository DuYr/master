package com.school.master.mbg.model;

import com.alibaba.fastjson.JSON;
import io.swagger.annotations.ApiModelProperty;

import java.io.Serializable;

/**
 * tp_students
 *
 * @author
 */
public class Student implements Serializable {
    private static final long serialVersionUID = 347841703779592345L;

    /**
     * 学号
     */
    @ApiModelProperty(value = "学号")
    private Integer sid;

    /**
     * 班级
     */
    @ApiModelProperty(value = "班级")
    private Integer pid;

    /**
     * 姓名
     */
    @ApiModelProperty(value = "姓名")
    private String name;

    /**
     * 备注
     */
    @ApiModelProperty(value = "备注")
    private String remarks;

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

    @Override
    public String toString() {

        return JSON.toJSONString(this);
    }

}