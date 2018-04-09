package com.naran.core.enums;

public enum ActivityStatus {

    INITIAL("待结束"), OVER("结束");

    private String chinese;

    public String getChinese() {
	return chinese;
    }

    private ActivityStatus(String chinese) {
	this.chinese = chinese;
    }
}
