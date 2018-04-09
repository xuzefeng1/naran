package com.naran.core.entity.points;

import com.naran.foundation.entity.BaseEntityPO;

public class LevelRecord extends BaseEntityPO {

    /**
     * 
     */
    private static final long serialVersionUID = 1L;

    private Long levelValue;

    private Long nowLevel;

    private Long accountId;

    private Long businessId;

    private String businessType;

    public Long getLevelValue() {
	return levelValue;
    }

    public void setLevelValue(Long levelValue) {
	this.levelValue = levelValue;
    }

    public Long getNowLevel() {
	return nowLevel;
    }

    public void setNowLevel(Long nowLevel) {
	this.nowLevel = nowLevel;
    }

    public Long getAccountId() {
	return accountId;
    }

    public void setAccountId(Long accountId) {
	this.accountId = accountId;
    }

    public Long getBusinessId() {
	return businessId;
    }

    public void setBusinessId(Long businessId) {
	this.businessId = businessId;
    }

    public String getBusinessType() {
	return businessType;
    }

    public void setBusinessType(String businessType) {
	this.businessType = businessType;
    }

}