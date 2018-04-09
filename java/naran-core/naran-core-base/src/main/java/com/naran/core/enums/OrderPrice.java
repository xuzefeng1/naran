package com.naran.core.enums;

public enum OrderPrice {

    UNCLEAR("不清楚"), FORGET("忘记了");

    private String chinese;

    public String getChinese() {
	return chinese;
    }

    private OrderPrice(String chinese) {
	this.chinese = chinese;
    }
}
