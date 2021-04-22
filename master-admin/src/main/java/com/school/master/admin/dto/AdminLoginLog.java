package com.school.master.admin.dto;

import java.io.Serializable;
import java.util.Date;

/**
 * (TpLogLogin)实体类
 *
 * @author makejava
 * @since 2020-12-30 13:03:56
 */
public class AdminLoginLog implements Serializable {
    private static final long serialVersionUID = 351132615833718423L;
    /**
     * 主键
     */
    private Integer id;
    /**
     * 用户
     */
    private String username;
    /**
     * 密码
     */
    private String password;
    /**
     * 结果
     */
    private String result;
    /**
     * 日期
     */
    private Date time;

    public AdminLoginLog() {
    }

    public AdminLoginLog(Integer id, String username, String password, String result, Date time) {
        this.id = id;
        this.username = username;
        this.password = password;
        this.result = result;
        this.time = time;
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

    public String getResult() {
        return result;
    }

    public void setResult(String result) {
        this.result = result;
    }

    public Date getTime() {
        return time;
    }

    public void setTime(Date time) {
        this.time = time;
    }

}