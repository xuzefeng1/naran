package com.naran.core.service.impl.questionnaire;

import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.naran.core.dao.questionnaire.QuestionnaireRecordDao;
import com.naran.core.entity.questionnaire.QuestionnaireOption;
import com.naran.core.entity.questionnaire.QuestionnaireRecord;
import com.naran.dubbo.response.DubboResponse;
import com.naran.dubbo.service.questionnaire.IQuestionnaireOptionService;
import com.naran.dubbo.service.questionnaire.IQuestionnaireRecordService;
import com.naran.foundation.mybatis.page.Page;
import com.naran.foundation.util.StringUtil;

/**
 * 
 * @author zefeng.xu
 */
@Transactional
@Service("questionnaireRecordService")
public class QuestionnaireRecordServiceImpl implements IQuestionnaireRecordService {

    @Autowired
    private IQuestionnaireOptionService questionnaireOptionService;

    @Autowired
    private QuestionnaireRecordDao questionnaireRecordDao;

    @Override
    public Long addQuestionnaireRecord(QuestionnaireRecord questionnaireRecord) {
	if (questionnaireRecord == null) {
	    return null;
	}
	questionnaireRecord.setCreateTime(new Date());
	questionnaireRecord.setUpdateTime(new Date());
	return questionnaireRecordDao.addQuestionnaireRecord(questionnaireRecord);
    }

    @Override
    public void updateQuestionnaireRecord(QuestionnaireRecord questionnaireRecord) {
	if (questionnaireRecord == null || questionnaireRecord.getId() == null) {
	    return;
	}
	questionnaireRecord.setUpdateTime(new Date());
	questionnaireRecordDao.updateQuestionnaireRecord(questionnaireRecord);

    }

    @Override
    public void deleteQuestionnaireRecord(Long id) {
	if (id == null) {
	    return;
	}
	questionnaireRecordDao.deleteQuestionnaireRecord(id);

    }

    @Override
    public QuestionnaireRecord findQuestionnaireRecordById(Long id) {
	if (id == null) {
	    return null;
	}
	return questionnaireRecordDao.findQuestionnaireRecordById(id);
    }

    @Override
    public Page<QuestionnaireRecord> findQuestionnaireRecordByPage(int pageNum, int pageSize) {

	return questionnaireRecordDao.findQuestionnaireRecordByPage(pageNum, pageSize);
    }

    @Override
    public DubboResponse<String> addQuestionnaireRecords(Long orderId, Long accountId, String optionIds) {
	DubboResponse<String> response = new DubboResponse<String>();
	String[] strOptionIds = StringUtil.split(optionIds, StringUtil.DEFAULT_BOUND_SYMBOL);
	Long optionId = null;
	QuestionnaireOption option = null;
	QuestionnaireRecord record = null;
	for (String strOptionId : strOptionIds) {
	    optionId = Long.valueOf(strOptionId);
	    if (optionId != null) {
		option = questionnaireOptionService.findQuestionnaireOptionById(optionId);
		if (option != null) {
		    record = new QuestionnaireRecord();
		    record.setOptionId(optionId);
		    record.setAccountId(accountId);
		    record.setOrderId(orderId);
		    record.setQuestionnaireId(option.getQuestionnaireId());
		    record.setOptionContent(option.getOptionContent());
		    addQuestionnaireRecord(record);
		}

	    }

	}
	return response;
    }
}
