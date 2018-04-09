package com.naran.core.enums;

/**
 * 
 * @author zefeng.xu
 */
public enum MailStatus {

    INITIAL("待发货"), GOING("运输中"), SUCCESS("运输完成");

    private String chinese;

    public String getChinese() {
	return chinese;
    }

    private MailStatus(String chinese) {
	this.chinese = chinese;
    }
}
