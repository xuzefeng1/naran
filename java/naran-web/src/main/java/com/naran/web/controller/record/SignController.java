package com.naran.web.controller.record;

import java.util.Date;

import javax.servlet.http.HttpServletResponse;

import org.apache.commons.collections.CollectionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.naran.core.entity.account.Account;
import com.naran.core.entity.record.SignRecord;
import com.naran.dubbo.service.account.IAccountService;
import com.naran.dubbo.service.record.ISignRecordService;
import com.naran.foundation.mybatis.page.Page;
import com.naran.foundation.util.DateUtil;
import com.naran.foundation.util.ListVO;
import com.naran.foundation.util.ResultMessageBuilder;
import com.naran.web.controller.base.AppCode;
import com.naran.web.controller.base.BaseController;
import com.naran.web.param.BasePageParam;
import com.naran.web.param.BaseParam;
import com.naran.web.vo.record.SignRecordPageVO;
import com.naran.web.vo.record.SignRecordVO;

/**
 * （签到操作）模块控制器
 * 
 * @author zefeng.xu
 */
@Controller
@RequestMapping("/api/record/sign")
public class SignController extends BaseController {

    @Autowired
    private ISignRecordService signRecordService;

    @Autowired
    private IAccountService accountService;

    /**
     * 所有签到记录
     * 
     */
    @RequestMapping(value = "/page")
    public void page(BasePageParam param, HttpServletResponse response) {
	Account current = accountService.currentAccount(param.getAccessToken());
	if (null == current) {
	    writeAjaxJSONResponse(ResultMessageBuilder.build(AppCode.GO_TO_LOGIN_CODE), response);
	    return;
	}

	SignRecordPageVO pageVO = new SignRecordPageVO();
	// 加载
	Page<SignRecord> page = signRecordService.findSignRecordByPage(current.getId(), null, null, param.getPageNum(), param.getPageSize());
	if (null != page && CollectionUtils.isNotEmpty(page.getResult())) {
	    pageVO.setPageAll(param.getPageNum(), param.getPageSize(), page.getTotalPage(), page.getTotalCount());
	    pageVO.setSigns(new ListVO<SignRecordVO>(page.getResult(), SignRecordVO.class).getVoList());
	}

	writeAjaxJSONResponse(ResultMessageBuilder.build(pageVO), response);
    }

    /**
     * 获取当月签到记录
     * 
     */
    @RequestMapping(value = "/month")
    public void month(BasePageParam param, Long orderId, HttpServletResponse response) {

	Account current = accountService.currentAccount(param.getAccessToken());
	if (null == current) {
	    writeAjaxJSONResponse(ResultMessageBuilder.build(AppCode.GO_TO_LOGIN_CODE), response);
	    return;
	}

	SignRecordPageVO pageVO = new SignRecordPageVO();
	String startDate = DateUtil.formatDateYMDHMS(DateUtil.getMonthBeginTime(new Date()));
	String endDate = DateUtil.formatDateYMDHMS(DateUtil.getMonthEndTime(new Date()));

	// 加载
	Page<SignRecord> page = signRecordService.findSignRecordByPage(current.getId(), startDate, endDate, 1, 31);
	if (null != page && CollectionUtils.isNotEmpty(page.getResult())) {
	    pageVO.setSigns(new ListVO<SignRecordVO>(page.getResult(), SignRecordVO.class).getVoList());
	}

	writeAjaxJSONResponse(ResultMessageBuilder.build(pageVO), response);
    }

    /**
     * 签到操作
     * 
     */
    @RequestMapping(value = "/add")
    public void add(BaseParam param, HttpServletResponse response) {
	Account current = accountService.currentAccount(param.getAccessToken());
	if (null == current) {
	    writeAjaxJSONResponse(ResultMessageBuilder.build(AppCode.GO_TO_LOGIN_CODE), response);
	    return;
	}
	SignRecord exist = signRecordService.findSignRecordByLast(current.getId());
	if (exist != null && DateUtil.isSameDate(exist.getSignDay(), new Date())) {
	    writeAjaxJSONResponse(ResultMessageBuilder.build(AppCode.FAIL_CODE, "不能重复签到！"), response);
	    return;
	}
	SignRecord record = new SignRecord();
	record.setAccountId(current.getId());
	record.setSignDay(new Date());
	if (exist != null && DateUtil.isSameDate(DateUtil.getAddDayDate(exist.getSignDay(), 1), record.getSignDay())) {
	    record.setContinuityDay(exist.getContinuityDay() + 1);
	} else {
	    record.setContinuityDay(1);
	}
	record.setSignTitle("连续签到" + record.getContinuityDay() + "次");
	record.setSignContent((exist != null ? "" : "恭喜获得“崭露头角”称号，") + "经验值+5 爱心值+5");
	Long id = signRecordService.addSignRecord(record);
	record.setId(id);
	
	writeAjaxJSONResponse(ResultMessageBuilder.build(record), response);
    }
}
