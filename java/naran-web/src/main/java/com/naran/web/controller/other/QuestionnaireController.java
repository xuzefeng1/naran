package com.naran.web.controller.other;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.collections.CollectionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.naran.core.entity.questionnaire.Questionnaire;
import com.naran.core.entity.questionnaire.QuestionnaireOption;
import com.naran.dubbo.response.DubboResponse;
import com.naran.dubbo.response.DubboResponseCode;
import com.naran.dubbo.service.questionnaire.IQuestionnaireOptionService;
import com.naran.dubbo.service.questionnaire.IQuestionnaireRecordService;
import com.naran.dubbo.service.questionnaire.IQuestionnaireService;
import com.naran.foundation.mybatis.page.Page;
import com.naran.foundation.util.ResultMessageBuilder;
import com.naran.foundation.util.StringUtil;
import com.naran.web.controller.base.AppCode;
import com.naran.web.controller.base.BaseController;
import com.naran.web.param.other.QuestionnairePageParam;
import com.naran.web.vo.questionnaire.QuestionnaireOptionVO;
import com.naran.web.vo.questionnaire.QuestionnairePageVO;
import com.naran.web.vo.questionnaire.QuestionnaireVO;

/**
 * （问卷）模块控制器
 * 
 * @author zefeng.xu
 */
@Controller
@RequestMapping("/api/questionnaire")
public class QuestionnaireController extends BaseController {

    @Autowired
    private IQuestionnaireService questionnaireService;
    @Autowired
    private IQuestionnaireOptionService questionnaireOptionService;
    @Autowired
    private IQuestionnaireRecordService questionnaireRecordService;

    /**
     * APP问卷列表(分页)
     * 
     * @param request
     * @param response
     */
    @RequestMapping(value = "/page/5")
    public void questionnairePage(QuestionnairePageParam param, HttpServletRequest request, HttpServletResponse response) {
	QuestionnairePageVO pageVO = new QuestionnairePageVO();
	// 加载
	Page<Questionnaire> page3 = questionnaireService.findQuestionnaireByPage("all", param.getPageNum(), 3);
	Page<Questionnaire> page2 = questionnaireService.findQuestionnaireByPage("bx", param.getPageNum(), 2);
	List<QuestionnaireVO> VOList = new ArrayList<QuestionnaireVO>();
	pageVO.setPageAll(param.getPageNum(), 5, null, null);
	QuestionnaireVO VO = null;
	// 通用题库
	if (null != page3 && CollectionUtils.isNotEmpty(page3.getResult())) {
	    for (Questionnaire questionnaire : page3.getResult()) {
		VO = getQuestionnaireVO(questionnaire);
		VOList.add(VO);
	    }
	}
	// 捐赠类型获取题库
	if (null != page2 && CollectionUtils.isNotEmpty(page2.getResult())) {
	    for (Questionnaire questionnaire : page2.getResult()) {
		VO = getQuestionnaireVO(questionnaire);
		VOList.add(VO);
	    }
	}
	pageVO.setQuestionnaires(VOList);
	writeAjaxJSONResponse(ResultMessageBuilder.build(pageVO), response);
    }

    private QuestionnaireVO getQuestionnaireVO(Questionnaire po) {
	QuestionnaireVO VO = new QuestionnaireVO();
	VO.convertPOToVO(po);
	List<QuestionnaireOption> options = questionnaireOptionService.findQuestionnaireOptionByQuestionnaireId(po.getId());
	List<QuestionnaireOptionVO> optionVOs = new ArrayList<QuestionnaireOptionVO>();
	QuestionnaireOptionVO optionVO = null;
	if (options != null && CollectionUtils.isNotEmpty(options)) {
	    for (QuestionnaireOption questionnaireOption : options) {
		optionVO = new QuestionnaireOptionVO();
		optionVO.convertPOToVO(questionnaireOption);
		optionVOs.add(optionVO);
	    }
	}
	VO.setOptions(optionVOs);
	return VO;
    }

    /**
     * TODO
     * 
     * @param request
     * @param response
     */
    @RequestMapping(value = "/add/records")
    public void questionnairePage(Long orderId, String optionIds, HttpServletRequest request, HttpServletResponse response) {
	Long accountId = 1l;
	if (StringUtil.isNotBlank(optionIds)) {
	    DubboResponse<String> dResponse = questionnaireRecordService.addQuestionnaireRecords(orderId, accountId, optionIds);
	    if (DubboResponseCode.SUCCESS.equals(dResponse.getCode())) {
		// 正确响应
		writeAjaxJSONResponse(ResultMessageBuilder.build(AppCode.SUCCESS_CODE), response);
		return;
	    } else {
		writeAjaxJSONResponse(ResultMessageBuilder.build(AppCode.FAIL_CODE, dResponse.getMsg()), response);
		return;
	    }

	}
	writeAjaxJSONResponse(ResultMessageBuilder.build(AppCode.FAIL_CODE), response);

    }
}
