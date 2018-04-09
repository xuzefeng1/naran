package com.naran.core.enums;

public enum BusinessType {

    CONTENT("文章"), ACTIVITY("活动"), ORDER("订单");

    private String chinese;

    public String getChinese() {
	return chinese;
    }

    private BusinessType(String chinese) {
	this.chinese = chinese;
    }
}
