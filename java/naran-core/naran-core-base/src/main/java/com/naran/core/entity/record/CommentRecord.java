package com.naran.core.entity.record;

import com.naran.foundation.entity.BaseEntityPO;

public class CommentRecord extends BaseEntityPO {

    /**
     * 
     */
    private static final long serialVersionUID = 1L;

    private Long businessId;

    private String businessType;

    private Long initiatorId;

    private String initiatorNike;

    private Long recipientId;

    private String recipientNike;

    private String commentContent;

    private Long commentedId;
    
    private Long commentedTopId;

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

    public Long getInitiatorId() {
	return initiatorId;
    }

    public void setInitiatorId(Long initiatorId) {
	this.initiatorId = initiatorId;
    }

    public String getInitiatorNike() {
	return initiatorNike;
    }

    public void setInitiatorNike(String initiatorNike) {
	this.initiatorNike = initiatorNike;
    }

    public Long getRecipientId() {
	return recipientId;
    }

    public void setRecipientId(Long recipientId) {
	this.recipientId = recipientId;
    }

    public String getRecipientNike() {
	return recipientNike;
    }

    public void setRecipientNike(String recipientNike) {
	this.recipientNike = recipientNike;
    }

    public String getCommentContent() {
	return commentContent;
    }

    public void setCommentContent(String commentContent) {
	this.commentContent = commentContent;
    }

    public Long getCommentedId() {
	return commentedId;
    }

    public void setCommentedId(Long commentedId) {
	this.commentedId = commentedId;
    }

    public Long getCommentedTopId() {
        return commentedTopId;
    }

    public void setCommentedTopId(Long commentedTopId) {
        this.commentedTopId = commentedTopId;
    }
    

}