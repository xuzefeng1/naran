package com.naran.core.enums;

public enum AgingDegree {

    ALL_NEW("全新"), NINE_NEW("九成新"), SEVEN_NEW("七成新"), SIX_NEW("六成新以下");

    private String chinese;

    public String getChinese() {
	return chinese;
    }

    private AgingDegree(String chinese) {
	this.chinese = chinese;
    }
}
