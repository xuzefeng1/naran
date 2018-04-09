package com.naran.web.vo.questionnaire;

import org.springframework.beans.BeanUtils;

import com.naran.core.entity.questionnaire.QuestionnaireOption;
import com.naran.foundation.util.BaseVO;

public class QuestionnaireOptionVO implements BaseVO {

    private Long optionId;

    private Integer optionOrder;

    private String optionContent;

    private Long questionnaireId;

    private String optionTitle;

    public Long getOptionId() {
	return optionId;
    }

    public void setOptionId(Long optionId) {
	this.optionId = optionId;
    }

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

    @Override
    public void convertPOToVO(Object poObj) {
	QuestionnaireOption po = (QuestionnaireOption) poObj;
	BeanUtils.copyProperties(po, this);
	this.optionId = po.getId();

    }

}
