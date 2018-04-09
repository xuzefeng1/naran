package com.naran.core.entity.points;

import com.naran.foundation.entity.BaseEntityPO;

public class Level extends BaseEntityPO {

    /**
     * 
     */
    private static final long serialVersionUID = 1L;

    private Long topValue;

    private Long lowValue;

    private String levelName;

    private Integer levelNum;

    public Long getTopValue() {
	return topValue;
    }

    public void setTopValue(Long topValue) {
	this.topValue = topValue;
    }

    public Long getLowValue() {
	return lowValue;
    }

    public void setLowValue(Long lowValue) {
	this.lowValue = lowValue;
    }

    public String getLevelName() {
	return levelName;
    }

    public void setLevelName(String levelName) {
	this.levelName = levelName;
    }

    public Integer getLevelNum() {
	return levelNum;
    }

    public void setLevelNum(Integer levelNum) {
	this.levelNum = levelNum;
    }

}