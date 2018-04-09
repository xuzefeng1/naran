package com.naran.core.enums;

/**
 * 
 * @author zefeng.xu
 */
public enum OrderApplyStatus {

    INITIAL("待匹配"),SUCCESS("匹配成功"), REVOKE("撤回"), FAIL("匹配失败");

    private String chinese;

    public String getChinese() {
	return chinese;
    }

    private OrderApplyStatus(String chinese) {
	this.chinese = chinese;
    }
}
