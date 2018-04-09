package com.naran.core.entity.points;

import com.naran.foundation.entity.BaseEntityPO;

public class LoveRecord  extends BaseEntityPO {

    /**
     * 
     */
    private static final long serialVersionUID = 1L;

    private Long loveValue;

    private Long nowLove;

    private Long accountId;

    private Long businessId;

    private String businessType;

    public Long getLoveValue() {
	return loveValue;
    }

    public void setLoveValue(Long loveValue) {
	this.loveValue = loveValue;
    }

    public Long getNowLove() {
	return nowLove;
    }

    public void setNowLove(Long nowLove) {
	this.nowLove = nowLove;
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