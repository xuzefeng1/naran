package com.naran.core.enums;

/**
 * 
 * @author zefeng.xu
 */
public enum OrderStatus {

    INITIAL("待匹配"), GOING("匹配中"),WAITING("等待中"), SUCCESS("匹配成功"), REVOKE("撤回"), FAIL("匹配失败");

    private String chinese;

    public String getChinese() {
	return chinese;
    }

    private OrderStatus(String chinese) {
	this.chinese = chinese;
    }
}
