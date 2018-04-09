package com.naran.core.enums;

/**
 * 
 * @author zefeng.xu
 */
public enum ActivityType {

    VOLUNTEER("公益活动"), AID("援助任务");

    private String chinese;

    public String getChinese() {
	return chinese;
    }

    private ActivityType(String chinese) {
	this.chinese = chinese;
    }
}