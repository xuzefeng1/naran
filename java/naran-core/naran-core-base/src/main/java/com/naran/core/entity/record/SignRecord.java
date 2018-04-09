package com.naran.core.entity.record;

import java.util.Date;

import com.naran.foundation.entity.BaseEntityPO;

public class SignRecord extends BaseEntityPO {

    /**
     * 
     */
    private static final long serialVersionUID = 1L;

    private Date signDay;// 签到日期

    private Long accountId;// 用户ID

    private String signTitle;// 签到标题

    private String signContent;// 签到描述

    private Integer continuityDay;// 连续签到天数

    private Long honorId;// 荣誉称号Id

    public Date getSignDay() {
	return signDay;
    }

    public void setSignDay(Date signDay) {
	this.signDay = signDay;
    }

    public Long getAccountId() {
	return accountId;
    }

    public void setAccountId(Long accountId) {
	this.accountId = accountId;
    }

    public String getSignTitle() {
	return signTitle;
    }

    public void setSignTitle(String signTitle) {
	this.signTitle = signTitle;
    }

    public String getSignContent() {
	return signContent;
    }

    public void setSignContent(String signContent) {
	this.signContent = signContent;
    }

    public Integer getContinuityDay() {
	return continuityDay;
    }

    public void setContinuityDay(Integer continuityDay) {
	this.continuityDay = continuityDay;
    }

    public Long getHonorId() {
	return honorId;
    }

    public void setHonorId(Long honorId) {
	this.honorId = honorId;
    }

}