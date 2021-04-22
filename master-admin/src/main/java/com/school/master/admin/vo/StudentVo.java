package com.school.master.admin.vo;

import com.school.master.admin.dto.Student;

public class StudentVo extends Student {
    private String roomName;

    public StudentVo() {
    }

    public StudentVo(String roomName) {
        this.roomName = roomName;
    }

    public String getRoomName() {
        return roomName;
    }

    public void setRoomName(String roomName) {
        this.roomName = roomName;
    }
}
