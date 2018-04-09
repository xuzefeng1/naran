package com.naran.web.vo.order;

import org.springframework.beans.BeanUtils;

import com.naran.core.entity.order.OrderFeedback;
import com.naran.foundation.util.BaseVO;
import com.naran.foundation.util.DateUtil;
import com.naran.foundation.util.file.qiniu.QiniuImageUtil;

public class OrderFeedbackVO implements BaseVO {

    private Long feedbackId;

    private String feedbackContent;

    private Long accountId;

    private String accountNike;

    private String accountImg;

    private String feedbackImgs;

    private Long orderId;

    private String createTime;

    public Long getFeedbackId() {
	return feedbackId;
    }

    public void setFeedbackId(Long feedbackId) {
	this.feedbackId = feedbackId;
    }

    public String getFeedbackContent() {
	return feedbackContent;
    }

    public void setFeedbackContent(String feedbackContent) {
	this.feedbackContent = feedbackContent;
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

    public String getFeedbackImgs() {
	return feedbackImgs;
    }

    public void setFeedbackImgs(String feedbackImgs) {
	this.feedbackImgs = feedbackImgs;
    }

    public Long getOrderId() {
	return orderId;
    }

    public void setOrderId(Long orderId) {
	this.orderId = orderId;
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
	OrderFeedback po = (OrderFeedback) poObj;
	BeanUtils.copyProperties(po, this);
	this.feedbackId = po.getId();
	this.feedbackImgs = QiniuImageUtil.newImageUrls(po.getFeedbackImgs());
	this.createTime = DateUtil.formatDateYMDHMS(po.getCreateTime());

    }

}
