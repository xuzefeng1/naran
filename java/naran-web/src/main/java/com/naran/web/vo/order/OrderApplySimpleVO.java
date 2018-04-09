package com.naran.web.vo.order;

import org.springframework.beans.BeanUtils;

import com.naran.core.entity.order.OrderApply;
import com.naran.foundation.util.BaseVO;
import com.naran.foundation.util.DateUtil;

public class OrderApplySimpleVO implements BaseVO {

    private Long applyId;

    private Long orderId;

    private Long accountId;

    private String accountNike;

    private String accountImg;

    private String applyStatus;

    private String applyTitle;

    private String createTime;

    private String updateTime;

    public Long getApplyId() {
	return applyId;
    }

    public void setApplyId(Long applyId) {
	this.applyId = applyId;
    }

    public Long getOrderId() {
	return orderId;
    }

    public void setOrderId(Long orderId) {
	this.orderId = orderId;
    }

    public Long getAccountId() {
	return accountId;
    }

    public void setAccountId(Long accountId) {
	this.accountId = accountId;
    }

    public String getAccountNike() {
	return accountNike;
    }

    public void setAccountNike(String accountNike) {
	this.accountNike = accountNike;
    }

    public String getAccountImg() {
	return accountImg;
    }

    public void setAccountImg(String accountImg) {
	this.accountImg = accountImg;
    }

    public String getApplyStatus() {
	return applyStatus;
    }

    public void setApplyStatus(String applyStatus) {
	this.applyStatus = applyStatus;
    }

    public String getApplyTitle() {
	return applyTitle;
    }

    public void setApplyTitle(String applyTitle) {
	this.applyTitle = applyTitle;
    }

    public String getCreateTime() {
	return createTime;
    }

    public void setCreateTime(String createTime) {
	this.createTime = createTime;
    }

    public String getUpdateTime() {
	return updateTime;
    }

    public void setUpdateTime(String updateTime) {
	this.updateTime = updateTime;
    }

    @Override
    public void convertPOToVO(Object poObj) {
	if (null == poObj) {
	    return;
	}
	OrderApply po = (OrderApply) poObj;
	BeanUtils.copyProperties(po, this);
	this.applyId = po.getId();
	this.createTime = DateUtil.formatDateYMDHMS(po.getCreateTime());
	this.updateTime = DateUtil.formatDateYMDHMS(po.getUpdateTime());

    }

}
