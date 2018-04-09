package com.naran.core.dao.questionnaire.impl;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.naran.core.dao.questionnaire.QuestionnaireOptionDao;
import com.naran.core.entity.questionnaire.QuestionnaireOption;
import com.naran.foundation.mybatis.dao.MyBatisDAO;
import com.naran.foundation.mybatis.page.Page;
import com.naran.foundation.mybatis.page.PageRequest;

/**
 * 
 * @author zefeng.xu
 */
@Repository
public class QuestionnaireOptionDaoImpl implements QuestionnaireOptionDao {

    private static final String ADD_QUESTIONNAIRE_OPTION = "addQuestionnaireOption";
    private static final String UPDATE_QUESTIONNAIRE_OPTION = "updateQuestionnaireOption";
    private static final String FIND_QUESTIONNAIRE_OPTION_BY_ID = "findQuestionnaireOptionById";
    private static final String FIND_QUESTIONNAIRE_OPTION_BY_PAGE = "findQuestionnaireOptionByPage";
    private static final String FIND_QUESTIONNAIRE_OPTION_BY_QUESTIONNAIRE_ID = "findQuestionnaireOptionByQuestionnaireId";
    @Autowired
    private MyBatisDAO myBatisDAO;

    @Override
    public Long addQuestionnaireOption(QuestionnaireOption questionnaireOption) {
	questionnaireOption.setCreateTime(new Date());
	questionnaireOption.setUpdateTime(new Date());
	questionnaireOption.setExpired(Boolean.TRUE);
	myBatisDAO.insert(ADD_QUESTIONNAIRE_OPTION, questionnaireOption);
	return questionnaireOption.getId();
    }

    @Override
    public void updateQuestionnaireOption(QuestionnaireOption questionnaireOption) {
	questionnaireOption.setUpdateTime(new Date());
	myBatisDAO.update(UPDATE_QUESTIONNAIRE_OPTION, questionnaireOption);
    }

    @Override
    public void deleteQuestionnaireOption(Long id) {
	QuestionnaireOption questionnaireOption = new QuestionnaireOption();
	questionnaireOption.setId(id);
	questionnaireOption.setExpired(Boolean.FALSE);
	myBatisDAO.update(UPDATE_QUESTIONNAIRE_OPTION, questionnaireOption);
    }

    @Override
    public QuestionnaireOption findQuestionnaireOptionById(Long id) {
	if (id == null) {
	    return null;
	}
	return (QuestionnaireOption) myBatisDAO.findForObject(FIND_QUESTIONNAIRE_OPTION_BY_ID, id);
    }

    @SuppressWarnings("unchecked")
    @Override
    public Page<QuestionnaireOption> findQuestionnaireOptionByPage(int pageNum, int pageSize) {
	Map<String, Object> param = new HashMap<String, Object>();
	return myBatisDAO.findForPage(FIND_QUESTIONNAIRE_OPTION_BY_PAGE, new PageRequest(pageNum, pageSize, param));
    }

    @SuppressWarnings("unchecked")
    @Override
    public List<QuestionnaireOption> findQuestionnaireOptionByQuestionnaireId(Long questionnaireId) {
	Map<String, Object> param = new HashMap<String, Object>();
	param.put("questionnaireId", questionnaireId);
	return myBatisDAO.findForList(FIND_QUESTIONNAIRE_OPTION_BY_QUESTIONNAIRE_ID, param);
    }

}
