package com.naran.core.enums;

/**
 * 方便时间
 * 
 * @author zefeng.xu
 */
public enum ConvenientTime {

    ALL("全天"), HOLIDAY("节假日"), WORKDAY("工作日");

    private String chinese;

    public String getChinese() {
	return chinese;
    }

    private ConvenientTime(String chinese) {
	this.chinese = chinese;
    }
}
