package com.naran.core.dao.questionnaire;

import com.naran.core.entity.questionnaire.Questionnaire;
import com.naran.foundation.mybatis.page.Page;

/**
 * 问卷数据访问接口
 * 
 * @author zefeng.xu
 */
public interface QuestionnaireDao {

    Long addQuestionnaire(Questionnaire questionnaire);

    void updateQuestionnaire(Questionnaire questionnaire);

    void deleteQuestionnaire(Long id);

    Questionnaire findQuestionnaireById(Long id);

    Page<Questionnaire> findQuestionnaireByPage(String orderType,int pageNum, int pageSize);

}
