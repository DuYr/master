package com.school.master.model;

import java.io.Serializable;
import java.util.Date;

/**
 * (TpLogAbsent)实体类
 *
 * @author makejava
 * @since 2021-01-13 14:13:22
 */
public class LogAbsent implements Serializable {
    private static final long serialVersionUID = 865673117677214988L;
    /**
     * 主键
     */
    private Integer id;
    /**
     * 管理员名称
     */
    private String username;
    /**
     * 行为
     */
    private String behavior;
    /**
     * 具体内容
     */
    private Object content;
    /**
     * 日期
     */
    private Date time;


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

    public String getBehavior() {
        return behavior;
    }

    public void setBehavior(String behavior) {
        this.behavior = behavior;
    }

    public Object getContent() {
        return content;
    }

    public void setContent(Object content) {
        this.content = content;
    }

    public Date getTime() {
        return time;
    }

    public void setTime(Date time) {
        this.time = time;
    }

}