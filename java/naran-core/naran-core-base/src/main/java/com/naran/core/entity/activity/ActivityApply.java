package com.naran.core.entity.activity;

import com.naran.foundation.entity.BaseEntityPO;

public class ActivityApply extends BaseEntityPO {

    /**
     * 
     */
    private static final long serialVersionUID = 1L;

    private Long accountId;

    private String accountName;

    private String accountPhone;

    private Long activityId;

    private String applyStatus;

    public Long getAccountId() {
	return accountId;
    }

    public void setAccountId(Long accountId) {
	this.accountId = accountId;
    }

    public String getAccountName() {
	return accountName;
    }

    public void setAccountName(String accountName) {
	this.accountName = accountName;
    }

    public String getAccountPhone() {
	return accountPhone;
    }

    public void setAccountPhone(String accountPhone) {
	this.accountPhone = accountPhone;
    }

    public Long getActivityId() {
	return activityId;
    }

    public void setActivityId(Long activityId) {
	this.activityId = activityId;
    }

    public String getApplyStatus() {
	return applyStatus;
    }

    public void setApplyStatus(String applyStatus) {
	this.applyStatus = applyStatus;
    }

}