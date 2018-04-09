package com.naran.web.controller.activity;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.collections.CollectionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.naran.core.entity.account.Account;
import com.naran.core.entity.activity.Activity;
import com.naran.core.entity.activity.ActivityApply;
import com.naran.core.enums.ActivityType;
import com.naran.dubbo.service.account.IAccountService;
import com.naran.dubbo.service.activity.IActivityApplyService;
import com.naran.dubbo.service.activity.IActivityService;
import com.naran.foundation.mybatis.page.Page;
import com.naran.foundation.util.ListVO;
import com.naran.foundation.util.ResultMessageBuilder;
import com.naran.web.controller.base.AppCode;
import com.naran.web.controller.base.BaseController;
import com.naran.web.param.BasePageParam;
import com.naran.web.param.BaseParam;
import com.naran.web.vo.activity.ActivityApplyVO;
import com.naran.web.vo.activity.ActivityPageVO;
import com.naran.web.vo.activity.ActivitySimpleVO;
import com.naran.web.vo.activity.ActivityVO;

/**
 * （那然活动）模块控制器
 * 
 * @author zefeng.xu
 */
@Controller
@RequestMapping("/api/activity")
public class ActivityController extends BaseController {

    @Autowired
    private IAccountService accountService;

    @Autowired
    private IActivityService activityService;

    @Autowired
    private IActivityApplyService activityApplyService;

    /**
     * 援助任务列表(分页)
     * 
     * @param request
     * @param response
     */
    @RequestMapping(value = "/aid/page")
    public void aidPage(BasePageParam param, HttpServletRequest request, HttpServletResponse response) {
	ActivityPageVO pageVO = new ActivityPageVO();
	// 加载
	Page<Activity> page = activityService.findActivityByPage(ActivityType.AID.name(), param.getPageNum(), param.getPageSize());
	if (null != page && CollectionUtils.isNotEmpty(page.getResult())) {
	    pageVO.setPageAll(param.getPageNum(), param.getPageSize(), page.getTotalPage(), page.getTotalCount());
	    pageVO.setActivitys(new ListVO<ActivitySimpleVO>(page.getResult(), ActivitySimpleVO.class).getVoList());
	}
	writeAjaxJSONResponse(ResultMessageBuilder.build(pageVO), response);
    }

    /**
     * 援助任务详情页
     * 
     */
    @RequestMapping(value = "/aid/detail")
    public void aidDetail(BaseParam param, Long activityId, HttpServletRequest request, HttpServletResponse response) {
	Activity activity = activityService.findActivityById(activityId);
	ActivityVO VO = new ActivityVO();
	VO.convertPOToVO(activity);
	writeAjaxJSONResponse(ResultMessageBuilder.build(VO), response);
    }

    /**
     * 援助任务我的报名单
     * 
     */
    @RequestMapping(value = "/apply/aid/with/me")
    public void applyAidDetail(BaseParam param, Long activityId, HttpServletRequest request, HttpServletResponse response) {
	Account current = accountService.currentAccount(param.getAccessToken());
	if (null == current) {
	    writeAjaxJSONResponse(ResultMessageBuilder.build(AppCode.GO_TO_LOGIN_CODE), response);
	    return;
	}
	ActivityApply activityApply = activityApplyService.findActivityApplyByAccountId(activityId, current.getId());
	ActivityApplyVO VO=new ActivityApplyVO();
	VO.convertPOToVO(activityApply);
	writeAjaxJSONResponse(ResultMessageBuilder.build(VO), response);
    }

    /**
     * 援助任务报名
     * 
     */
    @RequestMapping(value = "/apply/aid")
    public void applyAid(BaseParam param, Long activityId, String name, String phone, HttpServletRequest request, HttpServletResponse response) {
	Account current = accountService.currentAccount(param.getAccessToken());
	if (null == current) {
	    writeAjaxJSONResponse(ResultMessageBuilder.build(AppCode.GO_TO_LOGIN_CODE), response);
	    return;
	}
	ActivityApply activityApply = activityApplyService.findActivityApplyByAccountId(activityId, current.getId());
	if (activityApply != null) {
	    writeAjaxJSONResponse(ResultMessageBuilder.build(AppCode.FAIL_CODE, "不能重复报名！"), response);
	    return;
	}
	activityApply = new ActivityApply();
	activityApply.setAccountId(current.getId());
	activityApply.setAccountName(name);
	activityApply.setAccountPhone(phone);
	activityApply.setActivityId(activityId);
	activityApplyService.addActivityApply(activityApply);
	writeAjaxJSONResponse(ResultMessageBuilder.build(AppCode.SUCCESS_CODE), response);
    }

    /**
     * 援助任务报名确认
     * 
     */
    @RequestMapping(value = "/apply/aid/confirm")
    public void confirmAid(BaseParam param, Long applyId, String applyStatus, HttpServletRequest request, HttpServletResponse response) {
	Account current = accountService.currentAccount(param.getAccessToken());
	if (null == current) {
	    writeAjaxJSONResponse(ResultMessageBuilder.build(AppCode.GO_TO_LOGIN_CODE), response);
	    return;
	}
	ActivityApply activityApply = activityApplyService.findActivityApplyById(applyId);
	if (activityApply == null || applyStatus == null || !current.getId().equals(activityApply.getAccountId())) {
	    writeAjaxJSONResponse(ResultMessageBuilder.build(AppCode.FAIL_CODE), response);
	}
	activityApply.setApplyStatus(applyStatus);
	activityApplyService.updateActivityApply(activityApply);
	writeAjaxJSONResponse(ResultMessageBuilder.build(AppCode.SUCCESS_CODE), response);
    }

    /**
     * 公益活动列表(分页)
     * 
     * @param request
     * @param response
     */
    @RequestMapping(value = "/volunteer/page")
    public void volunteerPage(BasePageParam param, HttpServletRequest request, HttpServletResponse response) {
	ActivityPageVO pageVO = new ActivityPageVO();
	// 加载
	Page<Activity> page = activityService.findActivityByPage(ActivityType.VOLUNTEER.name(), param.getPageNum(), param.getPageSize());
	if (null != page && CollectionUtils.isNotEmpty(page.getResult())) {
	    pageVO.setPageAll(param.getPageNum(), param.getPageSize(), page.getTotalPage(), page.getTotalCount());
	    pageVO.setActivitys(new ListVO<ActivitySimpleVO>(page.getResult(), ActivitySimpleVO.class).getVoList());
	}
	writeAjaxJSONResponse(ResultMessageBuilder.build(pageVO), response);
    }

    /**
     * 公益活动详情页
     * 
     */
    @RequestMapping(value = "/volunteer/detail")
    public void volunteerDetail(BaseParam param, Long activityId, HttpServletRequest request, HttpServletResponse response) {
	Activity activity = activityService.findActivityById(activityId);
	ActivityVO VO = new ActivityVO();
	VO.convertPOToVO(activity);
	writeAjaxJSONResponse(ResultMessageBuilder.build(VO), response);
    }

    /**
     * 公益活动报名详情
     * 
     */
    @RequestMapping(value = "/apply/volunteer/with/me")
    public void applyVolunteerDetail(BaseParam param, Long activityId, HttpServletRequest request, HttpServletResponse response) {
	Account current = accountService.currentAccount(param.getAccessToken());
	if (null == current) {
	    writeAjaxJSONResponse(ResultMessageBuilder.build(AppCode.GO_TO_LOGIN_CODE), response);
	    return;
	}
	ActivityApply activityApply = activityApplyService.findActivityApplyByAccountId(activityId, current.getId());
	writeAjaxJSONResponse(ResultMessageBuilder.build(activityApply), response);
    }

    /**
     * 公益活动报名
     * 
     */
    @RequestMapping(value = "/apply/volunteer")
    public void applyVolunteer(BaseParam param, Long activityId, String name, String phone, HttpServletRequest request, HttpServletResponse response) {
	Account current = accountService.currentAccount(param.getAccessToken());
	if (null == current) {
	    writeAjaxJSONResponse(ResultMessageBuilder.build(AppCode.GO_TO_LOGIN_CODE), response);
	    return;
	}
	ActivityApply activityApply = activityApplyService.findActivityApplyByAccountId(activityId, current.getId());
	if (activityApply != null) {
	    writeAjaxJSONResponse(ResultMessageBuilder.build(AppCode.FAIL_CODE, "不能重复报名！"), response);
	    return;
	}
	activityApply = new ActivityApply();
	activityApply.setAccountId(current.getId());
	activityApply.setAccountName(name);
	activityApply.setAccountPhone(phone);
	activityApply.setActivityId(activityId);
	activityApplyService.addActivityApply(activityApply);
	writeAjaxJSONResponse(ResultMessageBuilder.build(AppCode.SUCCESS_CODE), response);
    }

    /**
     * 公益活动报名确认
     * 
     */
    @RequestMapping(value = "/apply/volunteer/confirm")
    public void confirmVolunteer(BaseParam param, Long applyId, String applyStatus, HttpServletRequest request, HttpServletResponse response) {
	Account current = accountService.currentAccount(param.getAccessToken());
	if (null == current) {
	    writeAjaxJSONResponse(ResultMessageBuilder.build(AppCode.GO_TO_LOGIN_CODE), response);
	    return;
	}
	ActivityApply activityApply = activityApplyService.findActivityApplyById(applyId);
	if (activityApply == null || applyStatus == null || !current.getId().equals(activityApply.getAccountId())) {
	    writeAjaxJSONResponse(ResultMessageBuilder.build(AppCode.FAIL_CODE), response);
	}
	activityApply.setApplyStatus(applyStatus);
	activityApplyService.updateActivityApply(activityApply);
	writeAjaxJSONResponse(ResultMessageBuilder.build(AppCode.SUCCESS_CODE), response);
    }

}
