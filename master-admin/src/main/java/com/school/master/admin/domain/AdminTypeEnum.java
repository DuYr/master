package com.school.master.admin.domain;

public enum AdminTypeEnum {
    ROOT(1, "超级管理员"),
    ORDINARY(2, "普通管理员");
    private Integer type;
    private String typeName;

    private AdminTypeEnum(int type, String typeName) {
        this.type = type;
        this.typeName = typeName;
    }

    public Integer getType() {
        return type;
    }

    public void setType(Integer type) {
        this.type = type;
    }

    public String getTypeName() {
        return typeName;
    }

    public void setTypeName(String typeName) {
        this.typeName = typeName;
    }

    public static String getTypeName(int type) {
        for (AdminTypeEnum adminTypeEnum : values()) {
            if (adminTypeEnum.type == type) {
                return adminTypeEnum.getTypeName();
            }
        }
        return "";
    }


}
