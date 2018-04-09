package com.naran.web.vo.record;

import org.springframework.beans.BeanUtils;

import com.naran.core.entity.record.SignRecord;
import com.naran.foundation.util.BaseVO;
import com.naran.foundation.util.DateUtil;

/**
 * 评论展示封装
 * 
 * @author zefeng.xu
 */
public class SignRecordVO implements BaseVO {

    private Long signId;

    private String signDay;// 签到日期

    private Long accountId;// 用户ID

    private String signTitle;// 签到标题

    private String signContent;// 签到描述

    private Integer continuityDay;// 连续签到天数

    public Long getSignId() {
	return signId;
    }

    public void setSignId(Long signId) {
	this.signId = signId;
    }

    public String getSignDay() {
	return signDay;
    }

    public void setSignDay(String signDay) {
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

    @Override
    public void convertPOToVO(Object poObj) {
	if (null == poObj) {
	    return;
	}
	SignRecord po = (SignRecord) poObj;
	BeanUtils.copyProperties(po, this);
	this.signId = po.getId();
	this.signDay = DateUtil.formatDateYMDHMS(po.getSignDay());

    }
}
