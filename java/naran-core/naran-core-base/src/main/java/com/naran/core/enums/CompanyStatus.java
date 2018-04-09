package com.naran.core.enums;

/**
 * 
 * @author zefeng.xu
 */
public enum CompanyStatus {

    INITIAL("待匹配"), SUCCESS("匹配成功"), FAIL("匹配失败");

    private String chinese;

    public String getChinese() {
	return chinese;
    }

    private CompanyStatus(String chinese) {
	this.chinese = chinese;
    }
}
