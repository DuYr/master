package com.school.master.admin.domain;

public enum DutyEnum {
    LATE("迟到"),
    TRUANCY("旷课"),
    LEAVE_EARLY("早退");
    private String situation;

    private DutyEnum(String situation) {

        this.situation = situation;
    }

    public String getSituation() {
        return situation;
    }

    public void setSituation(String situation) {
        this.situation = situation;
    }
}
