package com.naran.core.service.impl.questionnaire;

import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.naran.core.dao.questionnaire.QuestionnaireDao;
import com.naran.core.entity.questionnaire.Questionnaire;
import com.naran.dubbo.service.questionnaire.IQuestionnaireService;
import com.naran.foundation.mybatis.page.Page;

/**
 * 
 * @author zefeng.xu
 */
@Transactional
@Service("questionnaireService")
public class QuestionnaireServiceImpl implements IQuestionnaireService {

    @Autowired
    private QuestionnaireDao questionnaireDao;

    @Override
    public Long addQuestionnaire(Questionnaire questionnaire) {
	if (questionnaire == null) {
	    return null;
	}
	questionnaire.setCreateTime(new Date());
	questionnaire.setUpdateTime(new Date());
	return questionnaireDao.addQuestionnaire(questionnaire);
    }

    @Override
    public void updateQuestionnaire(Questionnaire questionnaire) {
	if (questionnaire == null || questionnaire.getId() == null) {
	    return;
	}
	questionnaire.setUpdateTime(new Date());
	questionnaireDao.updateQuestionnaire(questionnaire);

    }

    @Override
    public void deleteQuestionnaire(Long id) {
	if (id == null) {
	    return;
	}
	questionnaireDao.deleteQuestionnaire(id);

    }

    @Override
    public Questionnaire findQuestionnaireById(Long id) {
	if (id == null) {
	    return null;
	}
	return questionnaireDao.findQuestionnaireById(id);
    }

    @Override
    public Page<Questionnaire> findQuestionnaireByPage(String orderType, int pageNum, int pageSize) {

	return questionnaireDao.findQuestionnaireByPage(orderType, pageNum, pageSize);
    }

}
