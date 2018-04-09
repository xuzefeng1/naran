package com.naran.core.enums;

public enum OrderByType {
    NEW("最新"), HOT("最热");

    private String chinese;

    public String getChinese() {
	return chinese;
    }

    private OrderByType(String chinese) {
	this.chinese = chinese;
    }
}
