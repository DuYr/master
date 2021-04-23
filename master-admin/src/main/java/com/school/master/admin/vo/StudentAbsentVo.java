package com.school.master.admin.vo;

import com.school.master.model.AbsentDetails;
import com.school.master.model.Student;

import java.util.List;

public class StudentAbsentVo {
    private Student student;
    private List<AbsentDetails> detailsList;

    public StudentAbsentVo() {
    }

    public StudentAbsentVo(Student student, List<AbsentDetails> detailsList) {
        this.student = student;
        this.detailsList = detailsList;
    }

    public Student getStudent() {
        return student;
    }

    public void setStudent(Student student) {
        this.student = student;
    }

    public List<AbsentDetails> getDetailsList() {
        return detailsList;
    }

    public void setDetailsList(List<AbsentDetails> detailsList) {
        this.detailsList = detailsList;
    }
}
