package com.naran.core.dao.questionnaire.impl;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.naran.core.dao.questionnaire.QuestionnaireDao;
import com.naran.core.entity.questionnaire.Questionnaire;
import com.naran.foundation.mybatis.dao.MyBatisDAO;
import com.naran.foundation.mybatis.page.Page;
import com.naran.foundation.mybatis.page.PageRequest;

/**
 * 
 * @author zefeng.xu
 */
@Repository
public class QuestionnaireDaoImpl implements QuestionnaireDao {

    private static final String ADD_QUESTIONNAIRE = "addQuestionnaire";
    private static final String UPDATE_QUESTIONNAIRE = "updateQuestionnaire";
    private static final String FIND_QUESTIONNAIRE_BY_ID = "findQuestionnaireById";
    private static final String FIND_QUESTIONNAIRE_BY_PAGE = "findQuestionnaireByPage";

    @Autowired
    private MyBatisDAO myBatisDAO;

    @Override
    public Long addQuestionnaire(Questionnaire questionnaire) {
	questionnaire.setCreateTime(new Date());
	questionnaire.setUpdateTime(new Date());
	questionnaire.setExpired(Boolean.TRUE);
	myBatisDAO.insert(ADD_QUESTIONNAIRE, questionnaire);
	return questionnaire.getId();
    }

    @Override
    public void updateQuestionnaire(Questionnaire questionnaire) {
	questionnaire.setUpdateTime(new Date());
	myBatisDAO.update(UPDATE_QUESTIONNAIRE, questionnaire);
    }

    @Override
    public void deleteQuestionnaire(Long id) {
	Questionnaire questionnaire = new Questionnaire();
	questionnaire.setId(id);
	questionnaire.setExpired(Boolean.FALSE);
	myBatisDAO.update(UPDATE_QUESTIONNAIRE, questionnaire);
    }

    @Override
    public Questionnaire findQuestionnaireById(Long id) {
	if (id == null) {
	    return null;
	}
	return (Questionnaire) myBatisDAO.findForObject(FIND_QUESTIONNAIRE_BY_ID, id);
    }

    @SuppressWarnings("unchecked")
    @Override
    public Page<Questionnaire> findQuestionnaireByPage(String orderType,int pageNum, int pageSize) {
	Map<String, Object> param = new HashMap<String, Object>();
	param.put("orderType", orderType);
	return myBatisDAO.findForPage(FIND_QUESTIONNAIRE_BY_PAGE, new PageRequest(pageNum, pageSize, param));
    }

}
