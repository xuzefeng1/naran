package com.naran.bd.vo.questionnaire;

import java.util.List;

import org.springframework.beans.BeanUtils;

import com.naran.core.entity.questionnaire.Questionnaire;
import com.naran.foundation.util.BaseVO;

public class QuestionnaireVO implements BaseVO {

    private Long questionnaireId;

    private Integer questionnaireOrder;

    private String questionnaireContent;

    private Boolean questionnaireRequired;

    private String orderType;

    private List<QuestionnaireOptionVO> options;

    public Long getQuestionnaireId() {
	return questionnaireId;
    }

    public void setQuestionnaireId(Long questionnaireId) {
	this.questionnaireId = questionnaireId;
    }

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

    public List<QuestionnaireOptionVO> getOptions() {
	return options;
    }

    public void setOptions(List<QuestionnaireOptionVO> options) {
	this.options = options;
    }

    @Override
    public void convertPOToVO(Object poObj) {
	if (null == poObj) {
	    return;
	}
	Questionnaire po = (Questionnaire) poObj;
	BeanUtils.copyProperties(po, this);
	this.questionnaireId = po.getId();

    }

}
