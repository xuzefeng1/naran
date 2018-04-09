package com.naran.core.enums;

/**
 * 
 * @author zefeng.xu
 */
public enum CertificationType {

    INITIAL("待认证"), GOING("认证中"), SUCCESS("认证成功"), FAIL("认证失败");

    private String chinese;

    public String getChinese() {
	return chinese;
    }

    private CertificationType(String chinese) {
	this.chinese = chinese;
    }
}
