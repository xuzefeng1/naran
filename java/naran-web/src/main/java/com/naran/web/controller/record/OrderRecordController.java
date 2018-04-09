package com.naran.web.controller.record;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletResponse;

import org.apache.commons.collections.CollectionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.naran.core.entity.account.Account;
import com.naran.core.entity.order.Order;
import com.naran.core.enums.OrderType;
import com.naran.dubbo.service.account.IAccountService;
import com.naran.dubbo.service.order.IOrderService;
import com.naran.foundation.mybatis.page.Page;
import com.naran.foundation.util.ResultMessageBuilder;
import com.naran.web.controller.base.AppCode;
import com.naran.web.controller.base.BaseController;
import com.naran.web.param.BasePageParam;
import com.naran.web.vo.order.OrderPageVO;
import com.naran.web.vo.order.OrderSimpleVO;

/**
 * （订单记录）模块控制器
 * 
 * @author zefeng.xu
 */
@Controller
@RequestMapping("/api/record/order")
public class OrderRecordController extends BaseController {

    @Autowired
    private IAccountService accountService;
    @Autowired
    private IOrderService orderService;

    /**
     * 发起的捐赠列表
     * 
     */
    @RequestMapping(value = "/initiator/donation/account/page")
    public void initiatorDonationAccountPage(BasePageParam param, Long accountId, HttpServletResponse response) {
	Account current = accountService.currentAccount(param.getAccessToken());
	if (null == current) {
	    writeAjaxJSONResponse(ResultMessageBuilder.build(AppCode.GO_TO_LOGIN_CODE), response);
	    return;
	}
	Page<Order> page = orderService.findOrderByInitiatorPage(OrderType.DONATION.name(), accountId, param.getPageNum(), param.getPageSize());
	OrderPageVO pageVO = new OrderPageVO();
	if (null != page && CollectionUtils.isNotEmpty(page.getResult())) {
	    pageVO.setPageAll(param.getPageNum(), param.getPageSize(), page.getTotalPage(), page.getTotalCount());
	    pageVO.setOrders(getOrders(page.getResult()));
	}
	writeAjaxJSONResponse(ResultMessageBuilder.build(pageVO), response);
    }

    /**
     * 发起的愿望列表
     * 
     */
    @RequestMapping(value = "/initiator/wish/account/page")
    public void initiatorWishAccountPage(BasePageParam param, Long accountId, HttpServletResponse response) {
	Account current = accountService.currentAccount(param.getAccessToken());
	if (null == current) {
	    writeAjaxJSONResponse(ResultMessageBuilder.build(AppCode.GO_TO_LOGIN_CODE), response);
	    return;
	}
	Page<Order> page = orderService.findOrderByInitiatorPage(OrderType.WISH.name(), accountId, param.getPageNum(), param.getPageSize());
	OrderPageVO pageVO = new OrderPageVO();
	if (null != page && CollectionUtils.isNotEmpty(page.getResult())) {
	    pageVO.setPageAll(param.getPageNum(), param.getPageSize(), page.getTotalPage(), page.getTotalCount());
	    pageVO.setOrders(getOrders(page.getResult()));
	}
	writeAjaxJSONResponse(ResultMessageBuilder.build(pageVO), response);
    }

    /**
     * 获得列表
     * 
     */
    @RequestMapping(value = "/obtain/page")
    public void obtainPage(BasePageParam param, Long accountId, HttpServletResponse response) {
	Account current = accountService.currentAccount(param.getAccessToken());
	if (null == current) {
	    writeAjaxJSONResponse(ResultMessageBuilder.build(AppCode.GO_TO_LOGIN_CODE), response);
	    return;
	}
	Page<Order> page = orderService.findOrderByObtainPage(accountId, param.getPageNum(), param.getPageSize());
	OrderPageVO pageVO = new OrderPageVO();
	if (null != page && CollectionUtils.isNotEmpty(page.getResult())) {
	    pageVO.setPageAll(param.getPageNum(), param.getPageSize(), page.getTotalPage(), page.getTotalCount());
	    pageVO.setOrders(getOrders(page.getResult()));
	}
	writeAjaxJSONResponse(ResultMessageBuilder.build(pageVO), response);
    }

    /**
     * 送出列表
     * 
     */
    @RequestMapping(value = "/send/page")
    public void sendPage(BasePageParam param, Long accountId, HttpServletResponse response) {
	Account current = accountService.currentAccount(param.getAccessToken());
	if (null == current) {
	    writeAjaxJSONResponse(ResultMessageBuilder.build(AppCode.GO_TO_LOGIN_CODE), response);
	    return;
	}
	Page<Order> page = orderService.findOrderBySendPage(accountId, param.getPageNum(), param.getPageSize());
	OrderPageVO pageVO = new OrderPageVO();
	if (null != page && CollectionUtils.isNotEmpty(page.getResult())) {
	    pageVO.setPageAll(param.getPageNum(), param.getPageSize(), page.getTotalPage(), page.getTotalCount());
	    pageVO.setOrders(getOrders(page.getResult()));
	}
	writeAjaxJSONResponse(ResultMessageBuilder.build(pageVO), response);
    }

    private List<OrderSimpleVO> getOrders(List<Order> records) {
	List<OrderSimpleVO> VOs = new ArrayList<OrderSimpleVO>();
	OrderSimpleVO VO = null;
	for (Order order : records) {
	    VO = new OrderSimpleVO();
	    VO.convertPOToVO(order);
	    VOs.add(VO);
	}
	return VOs;
    }

}
