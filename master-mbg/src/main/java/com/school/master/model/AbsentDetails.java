package com.school.master.model;

import io.swagger.annotations.ApiModelProperty;

import java.io.Serializable;
import java.util.Date;

/**
 * 缺勤详情(TpAbsentDetails)实体类
 *
 * @author makejava
 * @since 2021-01-09 19:00:32
 */
public class AbsentDetails implements Serializable {
    private static final long serialVersionUID = 347841703779592185L;
    private Integer id;
    @ApiModelProperty(value = "缺勤日期(yyyy-MM-dd)")
    private String date;
    @ApiModelProperty(value = "学号")
    private Integer sid;
    @ApiModelProperty(value = "缺勤课程(1,2,3)")
    private String course;
    @ApiModelProperty(value = "早退，旷课，迟到")
    private String reason;
    @ApiModelProperty(value = "情况说明")
    private String remarks;
    private Date createDate;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public Integer getSid() {
        return sid;
    }

    public void setSid(Integer sid) {
        this.sid = sid;
    }

    public String getCourse() {
        return course;
    }

    public void setCourse(String course) {
        this.course = course;
    }

    public String getReason() {
        return reason;
    }

    public void setReason(String reason) {
        this.reason = reason;
    }

    public String getRemarks() {
        return remarks;
    }

    public void setRemarks(String remarks) {
        this.remarks = remarks;
    }

    public Date getCreateDate() {
        return createDate;
    }

    public void setCreateDate(Date createDate) {
        this.createDate = createDate;
    }

}