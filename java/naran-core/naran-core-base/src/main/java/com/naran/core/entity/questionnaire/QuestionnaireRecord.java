package com.naran.core.entity.questionnaire;

import com.naran.foundation.entity.BaseEntityPO;

public class QuestionnaireRecord extends BaseEntityPO {

    /**
     * 
     */
    private static final long serialVersionUID = 1L;

    private String optionContent;

    private Long orderId;

    private Long questionnaireId;

    private Long accountId;

    private Long optionId;

    public String getOptionContent() {
	return optionContent;
    }

    public void setOptionContent(String optionContent) {
	this.optionContent = optionContent;
    }

    public Long getOrderId() {
	return orderId;
    }

    public void setOrderId(Long orderId) {
	this.orderId = orderId;
    }

    public Long getQuestionnaireId() {
	return questionnaireId;
    }

    public void setQuestionnaireId(Long questionnaireId) {
	this.questionnaireId = questionnaireId;
    }

    public Long getAccountId() {
	return accountId;
    }

    public void setAccountId(Long accountId) {
	this.accountId = accountId;
    }

    public Long getOptionId() {
	return optionId;
    }

    public void setOptionId(Long optionId) {
	this.optionId = optionId;
    }

}