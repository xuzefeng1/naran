package com.naran.web.param.order;

import com.naran.web.param.BaseParam;

public class OrderFeedbackParam extends BaseParam {

    private Long feedbackId;// 反馈ID

    private String feedbackContent;// 反馈内容

    private String feedbackImgs;// 反馈图片

    private Long orderId;// 订单ID

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
