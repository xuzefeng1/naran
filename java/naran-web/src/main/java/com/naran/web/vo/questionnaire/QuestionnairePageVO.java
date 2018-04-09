package com.naran.web.vo.questionnaire;

import java.util.List;

import com.naran.foundation.util.BasePageVO;

public class QuestionnairePageVO extends BasePageVO {

    private List<QuestionnaireVO> questionnaires;

    public List<QuestionnaireVO> getQuestionnaires() {
	return questionnaires;
    }

    public void setQuestionnaires(List<QuestionnaireVO> questionnaires) {
	this.questionnaires = questionnaires;
    }

}
