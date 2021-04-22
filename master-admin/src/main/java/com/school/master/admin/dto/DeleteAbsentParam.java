package com.school.master.admin.dto;

import org.springframework.format.annotation.DateTimeFormat;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Past;
import java.util.Date;

public class DeleteAbsentParam {
    @NotNull
    private Integer sid;
    @Past
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date date;

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
}
