package com.school.master.model;

import java.io.Serializable;
import java.util.Date;

/**
 * (TpClass)实体类
 *
 * @author makejava
 * @since 2021-01-02 16:18:58
 */
public class SchoolClass implements Comparable<SchoolClass>, Serializable {
    private static final long serialVersionUID = -97795314714862654L;
    /**
     * 主键，班级id
     */
    private Integer pid;
    /**
     * 学院id
     */
    private Integer cid;
    /**
     * 名称
     */
    private String name;
    /**
     * 创建日期
     */
    private Date createTime;

    public SchoolClass() {
    }

    public SchoolClass(Integer pid, Integer cid, String name) {
        this.pid = pid;
        this.cid = cid;
        this.name = name;
    }

    public SchoolClass(Integer cid, String name) {
        this.cid = cid;
        this.name = name;
    }

    public Integer getPid() {
        return pid;
    }

    public void setPid(Integer pid) {
        this.pid = pid;
    }

    public Integer getCid() {
        return cid;
    }

    public void setCid(Integer cid) {
        this.cid = cid;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    @Override
    public int compareTo(SchoolClass schoolClass) {
        return this.getPid() - schoolClass.getPid();
    }
}