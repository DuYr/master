package com.school.master.admin.dto;

import org.springframework.format.annotation.DateTimeFormat;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Past;
import java.util.Date;

public class UpdateAbsentDetailsParam {
    @NotNull
    private Integer sid;
    /**
     * 日期
     */
    @Past
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date date;
    /**
     * 缺勤课程
     */
    private String course;
    /**
     * 原因
     */
    private String reason;
    /**
     * 备注
     */
    private String remarks;

    public Integer getSid() {
        return sid;
    }

    public void setSid(Integer sid) {
        this.sid = sid;
    }


    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
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
