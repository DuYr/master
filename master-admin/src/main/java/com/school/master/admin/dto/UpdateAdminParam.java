package com.school.master.admin.dto;

import javax.validation.constraints.NotEmpty;

public class UpdateAdminParam {
    @NotEmpty
    private String username;
    private Integer enable;
    private String remark;
    private Integer type;
    private Integer pid;

    public UpdateAdminParam() {
    }

    public UpdateAdminParam(String username, Integer enable, String remark, Integer type, Integer pid) {
        this.username = username;
        this.enable = enable;
        this.remark = remark;
        this.type = type;
        this.pid = pid;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public Integer getEnable() {
        return enable;
    }

    public void setEnable(Integer enable) {
        this.enable = enable;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    public Integer getType() {
        return type;
    }

    public void setType(Integer type) {
        this.type = type;
    }

    public Integer getPid() {
        return pid;
    }

    public void setPid(Integer pid) {
        this.pid = pid;
    }

    @Override
    public String toString() {
        return "UpdateAdminParam{" +
                "name='" + username + '\'' +
                ", authority=" + enable +
                ", remark='" + remark + '\'' +
                ", type=" + type +
                ", pid=" + pid +
                '}';
    }
}
