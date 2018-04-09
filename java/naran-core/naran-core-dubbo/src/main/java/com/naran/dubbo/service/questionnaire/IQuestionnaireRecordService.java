package com.naran.dubbo.service.questionnaire;

import com.naran.core.entity.questionnaire.QuestionnaireRecord;
import com.naran.dubbo.response.DubboResponse;
import com.naran.foundation.mybatis.page.Page;

/**
 * 问卷记录服务接口
 * 
 * @author zefeng.xu
 */
public interface IQuestionnaireRecordService {

    Long addQuestionnaireRecord(QuestionnaireRecord questionnaireRecord);

    void updateQuestionnaireRecord(QuestionnaireRecord questionnaireRecord);

    void deleteQuestionnaireRecord(Long id);

    QuestionnaireRecord findQuestionnaireRecordById(Long id);

    /**
     * 获取（分页）
     * 
     * @return
     */
    Page<QuestionnaireRecord> findQuestionnaireRecordByPage(int pageNum, int pageSize);

    DubboResponse<String> addQuestionnaireRecords(Long orderId,Long accountId, String optionIds);

}
