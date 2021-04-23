package com.school.master.admin.dto;

import org.hibernate.validator.constraints.Length;
import org.springframework.format.annotation.DateTimeFormat;

import javax.validation.constraints.Past;
import java.util.Date;

public class UpdateClassAbsentParam {
    private Integer pid;
    @Past
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date date;
    @Length(min = 1, max = 1)
    private Integer full;

    public Integer getPid() {
        return pid;
    }

    public void setPid(Integer pid) {
        this.pid = pid;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public Integer getFull() {
        return full;
    }

    public void setFull(Integer full) {
        this.full = full;
    }
}
