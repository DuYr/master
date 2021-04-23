package com.school.master.model;

import java.io.Serializable;
import java.util.Date;

/**
 * (TpAdmin)实体类
 *
 * @author makejava
 * @since 2020-12-11 08:37:09
 */
public class UmsAdmin implements Serializable {
    private static final long serialVersionUID = 528544036349694765L;
    /**
     * 主键
     */
    private Integer id;
    /**
     * 用户名
     */
    private String username;
    /**
     * 密码
     */
    private String password;
    /**
     * 创建时间
     */
    private Date createTime;
    /**
     * 是否激活
     */
    private Integer enable;
    /**
     * 说明
     */
    private String remark;
    /**
     * 类型
     */
    private Integer type;
    /**
     * 相关班级
     */
    private Integer pid;

    public UmsAdmin() {
    }

    public UmsAdmin(String username, String password, Date createTime, Integer enable, String remark, Integer type, Integer pid) {
        this.username = username;
        this.password = password;
        this.createTime = createTime;
        this.enable = enable;
        this.remark = remark;
        this.type = type;
        this.pid = pid;
    }

    public UmsAdmin(Integer id, String username, String password, Date createTime, Integer enable, String remark, Integer type, Integer pid) {
        this.id = id;
        this.username = username;
        this.password = password;
        this.createTime = createTime;
        this.enable = enable;
        this.remark = remark;
        this.type = type;
        this.pid = pid;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
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
        return "UmsAdmin{" +
                "id=" + id +
                ", name='" + username + '\'' +
                ", password='" + password + '\'' +
                ", createTime=" + createTime +
                ", authority=" + enable +
                ", remark='" + remark + '\'' +
                ", type=" + type +
                ", pid=" + pid +
                '}';
    }
}