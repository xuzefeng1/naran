package com.naran.core.enums;

public enum ActivityApplyStatus {

    INITIAL("待完成"), FAIL("放弃"), GOING("完成中"), SUCCESS("确认完成");

    private String chinese;

    public String getChinese() {
	return chinese;
    }

    private ActivityApplyStatus(String chinese) {
	this.chinese = chinese;
    }
}
