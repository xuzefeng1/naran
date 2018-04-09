package com.naran.dubbo.service.questionnaire;

import com.naran.core.entity.questionnaire.Questionnaire;
import com.naran.foundation.mybatis.page.Page;

/**
 * 问卷服务接口
 * 
 * @author zefeng.xu
 */
public interface IQuestionnaireService {

    Long addQuestionnaire(Questionnaire questionnaire);

    void updateQuestionnaire(Questionnaire questionnaire);

    void deleteQuestionnaire(Long id);

    Questionnaire findQuestionnaireById(Long id);

    /**
     * 获取（分页）
     * 
     * @return
     */
    Page<Questionnaire> findQuestionnaireByPage(String orderType,int pageNum, int pageSize);

}
