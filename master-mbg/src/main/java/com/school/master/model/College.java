package com.school.master.model;


import io.swagger.annotations.ApiModelProperty;

import java.io.Serializable;
import java.util.Date;

/**
 * (TpCollege)实体类
 *
 * @author makejava
 * @since 2020-12-31 10:40:43
 */
public class College implements Serializable {
    private static final long serialVersionUID = 435359864863486719L;
    /**
     * 二级学院ID
     */
    @ApiModelProperty(value = "二级学院编号")
    private Integer cid;
    /**
     * 二级学院名称
     */
    @ApiModelProperty(value = "二级学院名称")
    private String name;
    /**
     * 日期
     */
    private Date createTime;


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

}