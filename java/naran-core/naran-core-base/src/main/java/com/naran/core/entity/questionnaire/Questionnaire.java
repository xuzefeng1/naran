package com.naran.core.entity.questionnaire;

import com.naran.foundation.entity.BaseEntityPO;

public class Questionnaire extends BaseEntityPO {

    /**
     * 
     */
    private static final long serialVersionUID = 1L;

    private Integer questionnaireOrder;

    private String questionnaireContent;

    private Boolean questionnaireRequired;

    private String orderType;

    public Integer getQuestionnaireOrder() {
	return questionnaireOrder;
    }

    public void setQuestionnaireOrder(Integer questionnaireOrder) {
	this.questionnaireOrder = questionnaireOrder;
    }

    public String getQuestionnaireContent() {
	return questionnaireContent;
    }

    public void setQuestionnaireContent(String questionnaireContent) {
	this.questionnaireContent = questionnaireContent;
    }

    public Boolean getQuestionnaireRequired() {
	return questionnaireRequired;
    }

    public void setQuestionnaireRequired(Boolean questionnaireRequired) {
	this.questionnaireRequired = questionnaireRequired;
    }

    public String getOrderType() {
	return orderType;
    }

    public void setOrderType(String orderType) {
	this.orderType = orderType;
    }

}