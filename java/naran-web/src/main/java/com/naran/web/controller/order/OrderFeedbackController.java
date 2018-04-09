package com.naran.web.controller.order;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.naran.core.entity.account.Account;
import com.naran.core.entity.order.OrderFeedback;
import com.naran.dubbo.service.account.IAccountService;
import com.naran.dubbo.service.order.IOrderFeedbackService;
import com.naran.foundation.util.ResultMessageBuilder;
import com.naran.foundation.util.file.qiniu.QiniuImageUtil;
import com.naran.web.controller.base.AppCode;
import com.naran.web.controller.base.BaseController;
import com.naran.web.param.BaseParam;
import com.naran.web.param.order.OrderFeedbackParam;
import com.naran.web.vo.order.OrderFeedbackVO;

/**
 * （订单反馈）模块控制器
 * 
 * @author zefeng.xu
 */
@Controller
@RequestMapping("/api/order/feedback")
public class OrderFeedbackController extends BaseController {

    @Autowired
    private IOrderFeedbackService orderFeedbackService;
    @Autowired
    private IAccountService accountService;

    /**
     * 订单反馈获取
     */
    @RequestMapping(value = "/get")
    public void get(Long orderId, HttpServletRequest request, HttpServletResponse response) {
	OrderFeedback orderFeedback = orderFeedbackService.findOrderFeedbackByOrderId(orderId);
	OrderFeedbackVO VO = new OrderFeedbackVO();
	VO.convertPOToVO(orderFeedback);
	Account account = accountService.getAccountById(orderFeedback.getAccountId());
	VO.setAccountNike(account.getNickName());
	VO.setAccountImg(QiniuImageUtil.newImageUrl60(account.getPhoto()));
	writeAjaxJSONResponse(ResultMessageBuilder.build(VO), response);
    }

    /**
     * 订单反馈修改或新增
     */
    @RequestMapping(value = "/change")
    public void change(OrderFeedbackParam param, HttpServletRequest request, HttpServletResponse response) {
	Account current = accountService.currentAccount(param.getAccessToken());
	if (null == current) {
	    writeAjaxJSONResponse(ResultMessageBuilder.build(AppCode.GO_TO_LOGIN_CODE), response);
	    return;
	}
	OrderFeedback orderFeedback = null;
	if (param.getFeedbackId() == null) {
	    orderFeedback = orderFeedbackService.findOrderFeedbackByOrderId(param.getOrderId());
	    if (orderFeedback != null) {
		writeAjaxJSONResponse(ResultMessageBuilder.build(AppCode.FAIL_CODE, "该订单已经进行过感谢反馈！"), response);
		return;
	    }
	}
	orderFeedback = new OrderFeedback();
	BeanUtils.copyProperties(param, orderFeedback);
	orderFeedback.setAccountId(current.getId());
	if (param.getFeedbackId() == null) {
	    orderFeedbackService.addOrderFeedback(orderFeedback);
	} else {
	    orderFeedback.setId(param.getFeedbackId());
	    orderFeedbackService.updateOrderFeedback(orderFeedback);
	}

	writeAjaxJSONResponse(ResultMessageBuilder.build(AppCode.SUCCESS_CODE), response);
    }

    /**
     * 订单反馈撤销
     */
    @RequestMapping(value = "/delete")
    public void delete(BaseParam param, Long feedbackId, HttpServletRequest request, HttpServletResponse response) {
	Account current = accountService.currentAccount(param.getAccessToken());
	if (null == current) {
	    writeAjaxJSONResponse(ResultMessageBuilder.build(AppCode.GO_TO_LOGIN_CODE), response);
	    return;
	}
	OrderFeedback orderFeedback = orderFeedbackService.findOrderFeedbackById(feedbackId);
	if (!current.getId().equals(orderFeedback.getAccountId())) {
	    writeAjaxJSONResponse(ResultMessageBuilder.build(AppCode.FAIL_CODE, "不能删除别人的订单反馈！"), response);
	    return;
	}
	orderFeedbackService.deleteOrderFeedback(feedbackId);
	writeAjaxJSONResponse(ResultMessageBuilder.build(AppCode.SUCCESS_CODE), response);
    }
}
