package com.naran.core.enums;

/**
 * 
 * @author zefeng.xu
 */
public enum ContentType {

    COVERAGE("专题报道"), WEEKLY("每周推荐");

    private String chinese;

    public String getChinese() {
	return chinese;
    }

    private ContentType(String chinese) {
	this.chinese = chinese;
    }
}
