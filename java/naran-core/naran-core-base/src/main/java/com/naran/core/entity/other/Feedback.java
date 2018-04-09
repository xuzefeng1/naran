package com.naran.core.entity.other;

import com.naran.foundation.entity.BaseEntityPO;

public class Feedback extends BaseEntityPO {

    /**
     * 
     */
    private static final long serialVersionUID = 1L;

    private String feedbackStatus;

    private String feedbackContent;

    private Long businessId;

    private String businessType;

    private Long processId;

    private String processName;

    private String processRemark;

    public String getFeedbackStatus() {
	return feedbackStatus;
    }

    public void setFeedbackStatus(String feedbackStatus) {
	this.feedbackStatus = feedbackStatus;
    }

    public String getFeedbackContent() {
	return feedbackContent;
    }

    public void setFeedbackContent(String feedbackContent) {
	this.feedbackContent = feedbackContent;
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

    public Long getProcessId() {
	return processId;
    }

    public void setProcessId(Long processId) {
	this.processId = processId;
    }

    public String getProcessName() {
	return processName;
    }

    public void setProcessName(String processName) {
	this.processName = processName;
    }

    public String getProcessRemark() {
	return processRemark;
    }

    public void setProcessRemark(String processRemark) {
	this.processRemark = processRemark;
    }

}