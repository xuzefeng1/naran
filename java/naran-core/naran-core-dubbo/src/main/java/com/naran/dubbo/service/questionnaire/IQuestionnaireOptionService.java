package com.naran.dubbo.service.questionnaire;

import java.util.List;

import com.naran.core.entity.questionnaire.QuestionnaireOption;
import com.naran.foundation.mybatis.page.Page;

/**
 * 问卷选项服务接口
 * 
 * @author zefeng.xu
 */
public interface IQuestionnaireOptionService {

    Long addQuestionnaireOption(QuestionnaireOption questionnaireOption);

    void updateQuestionnaireOption(QuestionnaireOption questionnaireOption);

    void deleteQuestionnaireOption(Long id);

    QuestionnaireOption findQuestionnaireOptionById(Long id);

    /**
     * 获取（分页）
     * 
     * @return
     */
    Page<QuestionnaireOption> findQuestionnaireOptionByPage(int pageNum, int pageSize);

    List<QuestionnaireOption> findQuestionnaireOptionByQuestionnaireId(Long questionnaireId);

}
