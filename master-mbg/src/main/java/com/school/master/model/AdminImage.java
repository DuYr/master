package com.school.master.model;

import java.io.Serializable;

/**
 * (AdminImage)实体类
 *
 * @author makejava
 * @since 2021-04-19 14:50:51
 */
public class AdminImage implements Serializable {
    private static final long serialVersionUID = 586638924184640718L;

    private String name;

    private String imageUrl;


    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

}
