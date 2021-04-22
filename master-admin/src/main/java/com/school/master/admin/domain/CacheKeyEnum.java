package com.school.master.admin.domain;

public enum CacheKeyEnum {
    ADMIN_LIST_ID(10312L);
    private long key;

    private CacheKeyEnum(long key) {
        this.key = key;
    }

    public long getKey() {
        return key;
    }

    public void setKey(long key) {
        this.key = key;
    }
}
