package com.naran.bd.param.other;

import com.naran.bd.param.BasePageParam;

public class QuestionnairePageParam extends BasePageParam {

    private String questionnaireType; // 类型

    public String getQuestionnaireType() {
	return questionnaireType;
    }

    public void setQuestionnaireType(String questionnaireType) {
	this.questionnaireType = questionnaireType;
    }

}
