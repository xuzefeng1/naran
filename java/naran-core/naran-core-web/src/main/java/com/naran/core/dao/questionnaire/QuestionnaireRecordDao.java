package com.naran.core.dao.questionnaire;

import com.naran.core.entity.questionnaire.QuestionnaireRecord;
import com.naran.foundation.mybatis.page.Page;

/**
 * 问卷记录数据访问接口
 * 
 * @author zefeng.xu
 */
public interface QuestionnaireRecordDao {

    Long addQuestionnaireRecord(QuestionnaireRecord questionnaireRecord);

    void updateQuestionnaireRecord(QuestionnaireRecord questionnaireRecord);

    void deleteQuestionnaireRecord(Long id);

    QuestionnaireRecord findQuestionnaireRecordById(Long id);

    Page<QuestionnaireRecord> findQuestionnaireRecordByPage(int pageNum, int pageSize);

}
