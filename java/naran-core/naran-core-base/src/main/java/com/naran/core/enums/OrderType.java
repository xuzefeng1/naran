package com.naran.core.enums;

/**
 * 
 * @author zefeng.xu
 */
public enum OrderType {

    WISH("心愿"), DONATION("捐赠");

    private String chinese;

    public String getChinese() {
	return chinese;
    }

    private OrderType(String chinese) {
	this.chinese = chinese;
    }
}
