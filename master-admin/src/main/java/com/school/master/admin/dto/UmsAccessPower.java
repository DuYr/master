package com.school.master.admin.dto;

import java.io.Serializable;

/**
 * tp_access_power
 *
 * @author
 */
public class UmsAccessPower implements Serializable {
    private Integer id;

    /**
     * uri
     */
    private String uri;

    /**
     * 访问所需类型
     */
    private Integer type;

    private static final long serialVersionUID = 1L;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getUri() {
        return uri;
    }

    public void setUri(String uri) {
        this.uri = uri;
    }

    public Integer getType() {
        return type;
    }

    public void setType(Integer type) {
        this.type = type;
    }
}