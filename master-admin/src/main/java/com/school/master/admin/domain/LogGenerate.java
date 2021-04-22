package com.school.master.admin.domain;

import org.springframework.stereotype.Component;

@Component
public class LogGenerate {
    private String absent = "";

    public LogGenerate log(Integer sid, String course, String date, String reason, String remarks) {
        absent = "";
        addSid("学号" + sid + "的缺勤记录:");
        addCourse("时间:" + course + ",");
        addDate("日期:" + date + ",");
        addReason("原因:" + reason + ",");
        addRemarks("备注:" + remarks);
        return this;
    }

    public void addLogType(String type) {
        absent += type;
    }

    public void addSid(String sid) {
        absent += sid;
    }

    public void addCourse(String course) {
        absent += course;
    }

    public void addDate(String date) {
        absent += date;
    }

    public void addReason(String reason) {
        absent += reason;
    }

    public void addRemarks(String remarks) {
        absent += remarks;
    }

    public String builder() {
        return absent;
    }

}
