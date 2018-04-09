package com.naran.core.enums;

/**
 * 
 * @author zefeng.xu
 */
public enum RankType {

    WELFARE_DAY("公益达人日榜"), WELFARE_WEEK("公益达人周榜"), WELFARE_MONTH("公益达人月榜"), GRADE("等级榜"), ACHIEVEMENT("成就榜");

    private String chinese;

    public String getChinese() {
	return chinese;
    }

    private RankType(String chinese) {
	this.chinese = chinese;
    }
}
