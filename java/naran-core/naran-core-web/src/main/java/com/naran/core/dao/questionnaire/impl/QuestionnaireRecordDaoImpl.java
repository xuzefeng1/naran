package com.naran.core.dao.questionnaire.impl;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.naran.core.dao.questionnaire.QuestionnaireRecordDao;
import com.naran.core.entity.questionnaire.QuestionnaireRecord;
import com.naran.foundation.mybatis.dao.MyBatisDAO;
import com.naran.foundation.mybatis.page.Page;
import com.naran.foundation.mybatis.page.PageRequest;

/**
 * 
 * @author zefeng.xu
 */
@Repository
public class QuestionnaireRecordDaoImpl implements QuestionnaireRecordDao {

    private static final String ADD_QUESTIONNAIRE_RECORD = "addQuestionnaireRecord";
    private static final String UPDATE_QUESTIONNAIRE_RECORD = "updateQuestionnaireRecord";
    private static final String FIND_QUESTIONNAIRE_RECORD_BY_ID = "findQuestionnaireRecordById";
    private static final String FIND_QUESTIONNAIRE_RECORD_BY_PAGE = "findQuestionnaireRecordByPage";

    @Autowired
    private MyBatisDAO myBatisDAO;

    @Override
    public Long addQuestionnaireRecord(QuestionnaireRecord questionnaireRecord) {
	questionnaireRecord.setCreateTime(new Date());
	questionnaireRecord.setUpdateTime(new Date());
	questionnaireRecord.setExpired(Boolean.TRUE);
	myBatisDAO.insert(ADD_QUESTIONNAIRE_RECORD, questionnaireRecord);
	return questionnaireRecord.getId();
    }

    @Override
    public void updateQuestionnaireRecord(QuestionnaireRecord questionnaireRecord) {
	questionnaireRecord.setUpdateTime(new Date());
	myBatisDAO.update(UPDATE_QUESTIONNAIRE_RECORD, questionnaireRecord);
    }

    @Override
    public void deleteQuestionnaireRecord(Long id) {
	QuestionnaireRecord questionnaireRecord = new QuestionnaireRecord();
	questionnaireRecord.setId(id);
	questionnaireRecord.setExpired(Boolean.FALSE);
	myBatisDAO.update(UPDATE_QUESTIONNAIRE_RECORD, questionnaireRecord);
    }

    @Override
    public QuestionnaireRecord findQuestionnaireRecordById(Long id) {
	if (id == null) {
	    return null;
	}
	return (QuestionnaireRecord) myBatisDAO.findForObject(FIND_QUESTIONNAIRE_RECORD_BY_ID, id);
    }

    @SuppressWarnings("unchecked")
    @Override
    public Page<QuestionnaireRecord> findQuestionnaireRecordByPage(int pageNum, int pageSize) {
	Map<String, Object> param = new HashMap<String, Object>();
	return myBatisDAO.findForPage(FIND_QUESTIONNAIRE_RECORD_BY_PAGE, new PageRequest(pageNum, pageSize, param));
    }

}
