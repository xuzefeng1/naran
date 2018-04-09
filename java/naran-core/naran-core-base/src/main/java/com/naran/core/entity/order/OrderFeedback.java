package com.naran.core.entity.order;

import com.naran.foundation.entity.BaseEntityPO;

public class OrderFeedback extends BaseEntityPO {

    /**
     * 
     */
    private static final long serialVersionUID = 1L;

    private String feedbackContent;

    private Long accountId;

    private String feedbackImgs;

    private Long orderId;

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

}