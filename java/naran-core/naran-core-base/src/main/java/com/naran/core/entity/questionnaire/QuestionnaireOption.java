package com.naran.core.entity.questionnaire;

import com.naran.foundation.entity.BaseEntityPO;

public class QuestionnaireOption extends BaseEntityPO {

    /**
     * 
     */
    private static final long serialVersionUID = 1L;

    private Integer optionOrder;

    private String optionContent;

    private Long questionnaireId;

    private String optionTitle;

    public Integer getOptionOrder() {
	return optionOrder;
    }

    public void setOptionOrder(Integer optionOrder) {
	this.optionOrder = optionOrder;
    }

    public String getOptionContent() {
	return optionContent;
    }

    public void setOptionContent(String optionContent) {
	this.optionContent = optionContent;
    }

    public Long getQuestionnaireId() {
	return questionnaireId;
    }

    public void setQuestionnaireId(Long questionnaireId) {
	this.questionnaireId = questionnaireId;
    }

    public String getOptionTitle() {
	return optionTitle;
    }

    public void setOptionTitle(String optionTitle) {
	this.optionTitle = optionTitle;
    }

}