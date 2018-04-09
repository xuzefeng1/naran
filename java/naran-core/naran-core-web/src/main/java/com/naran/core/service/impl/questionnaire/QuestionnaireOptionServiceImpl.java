package com.naran.core.service.impl.questionnaire;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.naran.core.dao.questionnaire.QuestionnaireOptionDao;
import com.naran.core.entity.questionnaire.QuestionnaireOption;
import com.naran.dubbo.service.questionnaire.IQuestionnaireOptionService;
import com.naran.foundation.mybatis.page.Page;

/**
 * 
 * @author zefeng.xu
 */
@Transactional
@Service("questionnaireOptionService")
public class QuestionnaireOptionServiceImpl implements IQuestionnaireOptionService {

    @Autowired
    private QuestionnaireOptionDao questionnaireOptionDao;

    @Override
    public Long addQuestionnaireOption(QuestionnaireOption questionnaireOption) {
	if (questionnaireOption == null) {
	    return null;
	}
	questionnaireOption.setCreateTime(new Date());
	questionnaireOption.setUpdateTime(new Date());
	return questionnaireOptionDao.addQuestionnaireOption(questionnaireOption);
    }

    @Override
    public void updateQuestionnaireOption(QuestionnaireOption questionnaireOption) {
	if (questionnaireOption == null || questionnaireOption.getId() == null) {
	    return;
	}
	questionnaireOption.setUpdateTime(new Date());
	questionnaireOptionDao.updateQuestionnaireOption(questionnaireOption);

    }

    @Override
    public void deleteQuestionnaireOption(Long id) {
	if (id == null) {
	    return;
	}
	questionnaireOptionDao.deleteQuestionnaireOption(id);

    }

    @Override
    public QuestionnaireOption findQuestionnaireOptionById(Long id) {
	if (id == null) {
	    return null;
	}
	return questionnaireOptionDao.findQuestionnaireOptionById(id);
    }

    @Override
    public Page<QuestionnaireOption> findQuestionnaireOptionByPage(int pageNum, int pageSize) {

	return questionnaireOptionDao.findQuestionnaireOptionByPage(pageNum, pageSize);
    }

    @Override
    public List<QuestionnaireOption> findQuestionnaireOptionByQuestionnaireId(Long questionnaireId) {
	if (questionnaireId == null) {
	    return null;
	}
	List<QuestionnaireOption> options=questionnaireOptionDao.findQuestionnaireOptionByQuestionnaireId(questionnaireId);
	return options;
    }

}
