package com.school.master.admin.domain;

public class CriteriaGenerate {
    private String criteria = "";

    public CriteriaGenerate add(String s) {
        criteria += s;
        return this;
    }

    public String builder() {
        return "%" + criteria + "%";
    }

    public String[] lastSemester(Integer start, Integer end) {
        String[] dateRange = new String[2];
        dateRange[0] = start + "-9-1";
        dateRange[1] = end + "-2-1";
        return dateRange;
    }
    public String[] nextSemester(Integer start, Integer end) {
        String[] dateRange = new String[2];
        dateRange[0] = start + "-2-1";
        dateRange[1] = end + "-7-1";
        return dateRange;
    }

}
