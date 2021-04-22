package com.school.master.execl.column;

import com.school.master.common.annotation.ExcelColumn;

import java.text.SimpleDateFormat;
import java.util.Date;

public class AbsenceColumn {
    @ExcelColumn(column = "id", rank = 1)
    private Integer id;
    @ExcelColumn(column = "班级", rank = 2)
    private String roomName;
    @ExcelColumn(column = "学号", rank = 3)
    private Integer sid;
    @ExcelColumn(column = "姓名", rank = 4)
    private String name;
    @ExcelColumn(column = "日期", rank = 5)
    private String date;
    @ExcelColumn(column = "课程", rank = 6)
    private String course;
    @ExcelColumn(column = "原因", rank = 7)
    private String reason;
    @ExcelColumn(column = "备注", rank = 8)
    private String remarks;
    @ExcelColumn(column = "登记时间", rank = 9)
    private String createDate;

    public AbsenceColumn() {
    }

    public AbsenceColumn(Integer sid, String roomName, String name) {
        this.roomName = roomName;
        this.sid = sid;
        this.name = name;
    }

    public AbsenceColumn(Integer id, String roomName, Integer sid, String name, String date, String course, String reason, String remarks, String createDate) {
        this.id = id;
        this.roomName = roomName;
        this.sid = sid;
        this.name = name;
        this.date = date;
        this.course = course;
        this.reason = reason;
        this.remarks = remarks;
        this.createDate = createDate;
    }

    public void setData(Integer id, String roomName, String name, String createDate) {
        this.id = id;
        this.roomName = roomName;
        this.name = name;
        this.createDate = createDate;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getSid() {
        return sid;
    }

    public void setSid(Integer sid) {
        this.sid = sid;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {

        this.date = date;
    }

    public String getRoomName() {
        return roomName;
    }

    public void setRoomName(String roomName) {
        this.roomName = roomName;
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

    public String getCreateDate() {
        return date;
    }

    public void setCreateDate(String createDate) {
        this.createDate = createDate;
    }
}
