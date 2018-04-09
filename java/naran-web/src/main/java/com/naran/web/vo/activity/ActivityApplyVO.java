package com.naran.web.vo.activity;

import org.springframework.beans.BeanUtils;

import com.naran.core.entity.activity.ActivityApply;
import com.naran.foundation.util.BaseVO;
import com.naran.foundation.util.DateUtil;

/**
 * 展示封装
 * 
 * @author zefeng.xu
 */
public class ActivityApplyVO implements BaseVO {

    private Long applyId;

    private Long accountId;

    private String accountName;

    private String accountPhone;

    private Long activityId;

    private String applyStatus;

    private String createTime;

    public Long getApplyId() {
	return applyId;
    }

    public void setApplyId(Long applyId) {
	this.applyId = applyId;
    }

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

    public String getCreateTime() {
	return createTime;
    }

    public void setCreateTime(String createTime) {
	this.createTime = createTime;
    }

    @Override
    public void convertPOToVO(Object poObj) {
	if (null == poObj) {
	    return;
	}

	ActivityApply po = (ActivityApply) poObj;
	BeanUtils.copyProperties(po, this);
	this.applyId = po.getId();
	this.createTime = DateUtil.formatDateYMDHMS(po.getCreateTime());
	

    }

}
