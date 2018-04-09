package com.naran.core.dao.questionnaire;

import java.util.List;

import com.naran.core.entity.questionnaire.QuestionnaireOption;
import com.naran.foundation.mybatis.page.Page;

/**
 * 问卷选项数据访问接口
 * 
 * @author zefeng.xu
 */
public interface QuestionnaireOptionDao {

    Long addQuestionnaireOption(QuestionnaireOption questionnaireOption);

    void updateQuestionnaireOption(QuestionnaireOption questionnaireOption);

    void deleteQuestionnaireOption(Long id);

    QuestionnaireOption findQuestionnaireOptionById(Long id);

    Page<QuestionnaireOption> findQuestionnaireOptionByPage(int pageNum, int pageSize);

    List<QuestionnaireOption> findQuestionnaireOptionByQuestionnaireId(Long questionnaireId);

}
