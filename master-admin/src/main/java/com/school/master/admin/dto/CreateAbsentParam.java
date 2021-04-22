package com.school.master.admin.dto;


import org.springframework.format.annotation.DateTimeFormat;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Past;
import java.util.Date;

public class CreateAbsentParam{
    /**
     * 缺勤日期
     */
    @Past
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date date;
    /**
     * 学号
     */
    @NotNull
    private Integer sid;
    /**
     * 缺勤课程
     */
    @NotEmpty
    private String course;
    /**
     * 原因
     */
    @NotEmpty
    private String reason;
    /**
     * 备注
     */
    private String remarks;

    public CreateAbsentParam() {
    }


    public CreateAbsentParam(@Past Date date, @NotNull Integer sid, @NotEmpty String course, @NotEmpty String reason, String remarks) {
        this.date = date;
        this.sid = sid;
        this.course = course;
        this.reason = reason;
        this.remarks = remarks;
    }


    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
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
}
