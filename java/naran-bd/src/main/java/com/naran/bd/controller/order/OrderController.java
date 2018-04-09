package com.naran.bd.controller.order;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.collections.CollectionUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.naran.bd.controller.base.AppCode;
import com.naran.bd.controller.base.BaseController;
import com.naran.bd.param.BasePageParam;
import com.naran.bd.param.BaseParam;
import com.naran.bd.param.order.ApplyDonationParam;
import com.naran.bd.param.order.ApplyWishParam;
import com.naran.bd.param.order.OrderDonationParam;
import com.naran.bd.param.order.OrderPageParam;
import com.naran.bd.param.order.OrderWishParam;
import com.naran.bd.vo.order.OrderApplyPageVO;
import com.naran.bd.vo.order.OrderApplySimpleVO;
import com.naran.bd.vo.order.OrderApplyVO;
import com.naran.bd.vo.order.OrderDonationVO;
import com.naran.bd.vo.order.OrderPageVO;
import com.naran.bd.vo.order.OrderSimpleVO;
import com.naran.bd.vo.order.OrderWishVO;
import com.naran.bd.vo.order.OrderWithMeVO;
import com.naran.bd.vo.order.StationeryPageVO;
import com.naran.bd.vo.order.StationeryVO;
import com.naran.core.entity.account.Account;
import com.naran.core.entity.order.Order;
import com.naran.core.entity.order.OrderApply;
import com.naran.core.enums.CertificationType;
import com.naran.core.enums.MailStatus;
import com.naran.core.enums.OrderApplyStatus;
import com.naran.core.enums.OrderStatus;
import com.naran.core.enums.OrderType;
import com.naran.dubbo.response.DubboResponse;
import com.naran.dubbo.response.DubboResponseCode;
import com.naran.dubbo.service.account.IAccountService;
import com.naran.dubbo.service.order.IOrderApplyService;
import com.naran.dubbo.service.order.IOrderService;
import com.naran.foundation.mybatis.page.Page;
import com.naran.foundation.util.ListVO;
import com.naran.foundation.util.ResultMessageBuilder;
import com.naran.foundation.util.file.qiniu.QiniuImageUtil;

/**
 * （订单）模块控制器
 * 
 * @author zefeng.xu
 */
@Controller
@RequestMapping("/bd/order")
public class OrderController extends BaseController {

    @Autowired
    private IOrderService orderService;
    @Autowired
    private IOrderApplyService orderApplyService;
    @Autowired
    private IAccountService accountService;

    /**
     * 列表(分页)
     * 
     * @param request
     * @param response
     */
    @RequestMapping(value = "/page")
    public void orderPage(OrderPageParam param, HttpServletRequest request, HttpServletResponse response) {
	OrderPageVO pageVO = new OrderPageVO();
	// 加载
	Page<Order> page = orderService.findOrderByPage(null, param.getAgingDegree(), param.getCommodityType(), param.getCity(), param.getProvince(), param.getOrderByType(), param.getPageNum(), param.getPageSize());
	if (null != page && CollectionUtils.isNotEmpty(page.getResult())) {
	    pageVO.setPageAll(param.getPageNum(), param.getPageSize(), page.getTotalPage(), page.getTotalCount());
	    pageVO.setOrders(new ListVO<OrderSimpleVO>(page.getResult(), OrderSimpleVO.class).getVoList());
	}
	writeAjaxJSONResponse(ResultMessageBuilder.build(pageVO), response);
    }

    /**
     * 心愿列表(分页)
     * 
     * @param request
     * @param response
     */
    @RequestMapping(value = "/wish/page")
    public void wishPage(OrderPageParam param, HttpServletRequest request, HttpServletResponse response) {
	OrderPageVO pageVO = new OrderPageVO();

	// 加载
	Page<Order> page = orderService.findOrderByPage(OrderType.WISH.name(), param.getAgingDegree(), param.getCommodityType(), param.getCity(), param.getProvince(), param.getOrderByType(), param.getPageNum(), param.getPageSize());
	if (null != page && CollectionUtils.isNotEmpty(page.getResult())) {
	    pageVO.setPageAll(param.getPageNum(), param.getPageSize(), page.getTotalPage(), page.getTotalCount());
	    pageVO.setOrders(new ListVO<OrderSimpleVO>(page.getResult(), OrderSimpleVO.class).getVoList());
	}
	writeAjaxJSONResponse(ResultMessageBuilder.build(pageVO), response);
    }

    /**
     * 心愿详情页
     * 
     * @param request
     * @param response
     */
    @RequestMapping(value = "/wish/detail")
    public void wishDetail(BaseParam param, Long orderId, HttpServletRequest request, HttpServletResponse response) {
	Order order = orderService.findOrderById(orderId);
	OrderWishVO VO = new OrderWishVO();
	VO.convertPOToVO(order);
	writeAjaxJSONResponse(ResultMessageBuilder.build(VO), response);
    }

    /**
     * 发布心愿
     * 
     * @param request
     * @param response
     */
    @RequestMapping(value = "/wish")
    public void wish(OrderWishParam param, HttpServletRequest request, HttpServletResponse response) {
	Account current = accountService.currentAccount(param.getAccessToken());
	if (null == current) {
	    writeAjaxJSONResponse(ResultMessageBuilder.build(AppCode.GO_TO_LOGIN_CODE), response);
	    return;
	}
	if (!CertificationType.SUCCESS.name().equals(current.getCertificationType())) {
	    writeAjaxJSONResponse(ResultMessageBuilder.build(AppCode.ACCOUNT_CERTIFICATION_CODE), response);
	    return;
	}
	Order order = new Order();
	BeanUtils.copyProperties(param, order);
	order.setInitiatorId(current.getId());
	order.setInitiatorNike(current.getNickName());
	order.setBackdropImg(QiniuImageUtil.replaceUrl(param.getBackdropImg()));
	order.setBackdropBottomImg(QiniuImageUtil.replaceUrl(param.getBackdropBottomImg()));
	orderService.addOrderByWish(order);
	writeAjaxJSONResponse(ResultMessageBuilder.build(AppCode.SUCCESS_CODE), response);
    }

    /**
     * 撤回心愿
     * 
     * @param request
     * @param response
     */
    @RequestMapping(value = "/wish/withdraw")
    public void wishWithdraw(BaseParam param, Long orderId, HttpServletRequest request, HttpServletResponse response) {
	Account current = accountService.currentAccount(param.getAccessToken());
	if (null == current) {
	    writeAjaxJSONResponse(ResultMessageBuilder.build(AppCode.GO_TO_LOGIN_CODE), response);
	    return;
	}
	Order order = orderService.findOrderById(orderId);
	if (order != null) {
	    if (current.getId().equals(order.getInitiatorId())) {
		writeAjaxJSONResponse(ResultMessageBuilder.build(AppCode.FAIL_CODE, "不能删除非本人生成的数据！"), response);
		return;
	    }
	    order.setOrderStatus(OrderStatus.REVOKE.name());
	    orderService.updateOrder(order);
	}
	writeAjaxJSONResponse(ResultMessageBuilder.build(AppCode.SUCCESS_CODE), response);
    }

    /**
     * 心愿确认
     * 
     */
    @RequestMapping(value = "/wish/confirm")
    public void wishConfirm(BaseParam param, Long applyId, String applyStatus, HttpServletRequest request, HttpServletResponse response) {
	Account current = accountService.currentAccount(param.getAccessToken());
	if (null == current) {
	    writeAjaxJSONResponse(ResultMessageBuilder.build(AppCode.GO_TO_LOGIN_CODE), response);
	    return;
	}
	OrderApply orderApply = orderApplyService.findOrderApplyById(applyId);
	if (orderApply == null || !OrderApplyStatus.INITIAL.name().equals(orderApply.getApplyStatus())) {
	    writeAjaxJSONResponse(ResultMessageBuilder.build(AppCode.FAIL_CODE, "心愿申请不存在或状态已改变！"), response);
	    return;
	}
	Order order = orderService.findOrderById(orderApply.getOrderId());
	if (order == null || !OrderStatus.GOING.name().equals(order.getOrderStatus())) {
	    writeAjaxJSONResponse(ResultMessageBuilder.build(AppCode.FAIL_CODE, "心愿不存在或状态已改变！"), response);
	    return;
	}
	if (!current.getId().equals(order.getInitiatorId())) {
	    writeAjaxJSONResponse(ResultMessageBuilder.build(AppCode.FAIL_CODE, "只能实现自己的心愿！"), response);
	    return;
	}

	orderApply.setApplyStatus(applyStatus);
	orderApplyService.updateOrderApply(orderApply, order);

	writeAjaxJSONResponse(ResultMessageBuilder.build(AppCode.SUCCESS_CODE), response);
    }

    /**
     * 心愿申请单
     * 
     * @param request
     * @param response
     */
    @RequestMapping(value = "/apply/wish/detail")
    public void wishApplyDetail(Long orderId, HttpServletRequest request, HttpServletResponse response) {
	OrderApply orderApply = null;
	Page<OrderApply> page = orderApplyService.findOrderApplyByPage(orderId, 1, 1);
	if (null != page && CollectionUtils.isNotEmpty(page.getResult())) {
	    orderApply = page.getResult().get(0);
	}
	OrderApplyVO VO = new OrderApplyVO();
	VO.convertPOToVO(orderApply);
	Account account = accountService.getAccountById(orderApply.getAccountId());
	if (account != null) {
	    VO.setAccountNike(account.getNickName());
	    VO.setAccountImg(QiniuImageUtil.newImageUrl60(account.getPhoto()));
	}
	writeAjaxJSONResponse(ResultMessageBuilder.build(VO), response);
    }

    /**
     * 申请实现心愿
     * 
     * @param request
     * @param response
     */
    @RequestMapping(value = "/apply/wish")
    public void applyWish(ApplyWishParam param, HttpServletRequest request, HttpServletResponse response) {
	Account current = accountService.currentAccount(param.getAccessToken());
	if (null == current) {
	    writeAjaxJSONResponse(ResultMessageBuilder.build(AppCode.GO_TO_LOGIN_CODE), response);
	    return;
	}
	if (!CertificationType.SUCCESS.name().equals(current.getCertificationType())) {
	    writeAjaxJSONResponse(ResultMessageBuilder.build(AppCode.ACCOUNT_CERTIFICATION_CODE), response);
	    return;
	}
	OrderApply orderApply = new OrderApply();
	BeanUtils.copyProperties(param, orderApply);
	orderApply.setAccountId(current.getId());
	orderApply.setApplyType(OrderType.WISH.name());

	DubboResponse<String> dResponse = orderApplyService.addOrderApplyByWish(orderApply);
	if (!DubboResponseCode.SUCCESS.equals(dResponse.getCode())) {
	    writeAjaxJSONResponse(ResultMessageBuilder.build(AppCode.FAIL_CODE, dResponse.getMsg()), response);
	    return;
	}
	writeAjaxJSONResponse(ResultMessageBuilder.build(AppCode.SUCCESS_CODE), response);
    }

    /**
     * 撤回心愿申请
     * 
     * @param request
     * @param response
     */
    @RequestMapping(value = "/apply/wish/withdraw")
    public void applyWishWithdraw(BaseParam param, Long applyId, HttpServletRequest request, HttpServletResponse response) {
	Account current = accountService.currentAccount(param.getAccessToken());
	if (null == current) {
	    writeAjaxJSONResponse(ResultMessageBuilder.build(AppCode.GO_TO_LOGIN_CODE), response);
	    return;
	}

	OrderApply orderApply = orderApplyService.findOrderApplyById(applyId);
	if (orderApply != null) {
	    if (current.getId().equals(orderApply.getAccountId())) {
		writeAjaxJSONResponse(ResultMessageBuilder.build(AppCode.FAIL_CODE, "不能删除非本人生成的数据！"), response);
		return;
	    }
	    orderApply.setApplyStatus(OrderApplyStatus.REVOKE.name());
	    orderApplyService.updateOrderApply(orderApply);
	}

	writeAjaxJSONResponse(ResultMessageBuilder.build(AppCode.SUCCESS_CODE), response);
    }

    /**
     * 实现心愿发货
     * 
     */
    @RequestMapping(value = "/apply/wish/deliver")
    public void applywishDeliver(BaseParam param, Long orderId, String mailCode, HttpServletRequest request, HttpServletResponse response) {
	Account current = accountService.currentAccount(param.getAccessToken());
	if (null == current) {
	    writeAjaxJSONResponse(ResultMessageBuilder.build(AppCode.GO_TO_LOGIN_CODE), response);
	    return;
	}

	Order order = orderService.findOrderById(orderId);
	order.setMailStatus(MailStatus.GOING.name());
	order.setMailCode(mailCode);
	orderService.updateOrder(order);
	writeAjaxJSONResponse(ResultMessageBuilder.build(AppCode.SUCCESS_CODE), response);
    }

    /**
     * 捐赠列表(分页)
     * 
     * @param request
     * @param response
     */
    @RequestMapping(value = "/donation/page")
    public void donationPage(OrderPageParam param, HttpServletRequest request, HttpServletResponse response) {
	OrderPageVO pageVO = new OrderPageVO();
	// 加载
	Page<Order> page = orderService.findOrderByPage(OrderType.DONATION.name(), param.getAgingDegree(), param.getCommodityType(), param.getCity(), param.getProvince(), param.getOrderByType(), param.getPageNum(), param.getPageSize());
	if (null != page && CollectionUtils.isNotEmpty(page.getResult())) {
	    pageVO.setPageAll(param.getPageNum(), param.getPageSize(), page.getTotalPage(), page.getTotalCount());
	    pageVO.setOrders(new ListVO<OrderSimpleVO>(page.getResult(), OrderSimpleVO.class).getVoList());
	}
	writeAjaxJSONResponse(ResultMessageBuilder.build(pageVO), response);
    }

    /**
     * 捐赠详情页
     * 
     * @param request
     * @param response
     */
    @RequestMapping(value = "/donation/detail")
    public void donationDetail(BaseParam param, Long orderId, HttpServletRequest request, HttpServletResponse response) {
	Order order = orderService.findOrderById(orderId);
	OrderDonationVO VO = new OrderDonationVO();
	VO.convertPOToVO(order);
	writeAjaxJSONResponse(ResultMessageBuilder.build(VO), response);
    }

    /**
     * 发布捐赠
     * 
     * @param request
     * @param response
     */
    @RequestMapping(value = "/donation")
    public void donation(OrderDonationParam param, HttpServletRequest request, HttpServletResponse response) {
	Account current = accountService.currentAccount(param.getAccessToken());
	if (null == current) {
	    writeAjaxJSONResponse(ResultMessageBuilder.build(AppCode.GO_TO_LOGIN_CODE), response);
	    return;
	}
	if (!CertificationType.SUCCESS.name().equals(current.getCertificationType())) {
	    writeAjaxJSONResponse(ResultMessageBuilder.build(AppCode.ACCOUNT_CERTIFICATION_CODE), response);
	    return;
	}
	Order order = new Order();
	BeanUtils.copyProperties(param, order);
	order.setInitiatorId(current.getId());
	order.setInitiatorNike(current.getNickName());
	orderService.addOrderByDonation(order, param.getQuestionnaireOptionIds());
	writeAjaxJSONResponse(ResultMessageBuilder.build(AppCode.SUCCESS_CODE), response);
    }

    /**
     * 撤回捐赠
     * 
     * @param request
     * @param response
     */
    @RequestMapping(value = "/donation/withdraw")
    public void donationWithdraw(BaseParam param, Long orderId, HttpServletRequest request, HttpServletResponse response) {
	Account current = accountService.currentAccount(param.getAccessToken());
	if (null == current) {
	    writeAjaxJSONResponse(ResultMessageBuilder.build(AppCode.GO_TO_LOGIN_CODE), response);
	    return;
	}
	Order order = orderService.findOrderById(orderId);
	if (order != null) {
	    if (current.getId().equals(order.getInitiatorId())) {
		writeAjaxJSONResponse(ResultMessageBuilder.build(AppCode.FAIL_CODE, "不能删除非本人生成的数据！"), response);
		return;
	    }
	    order.setOrderStatus(OrderStatus.REVOKE.name());
	    orderService.updateOrder(order);
	}
	writeAjaxJSONResponse(ResultMessageBuilder.build(AppCode.SUCCESS_CODE), response);
    }

    /**
     * 申请获得捐赠
     * 
     * @param request
     * @param response
     */
    @RequestMapping(value = "/apply/donation")
    public void applyDonation(ApplyDonationParam param, HttpServletRequest request, HttpServletResponse response) {
	Account current = accountService.currentAccount(param.getAccessToken());
	if (null == current) {
	    writeAjaxJSONResponse(ResultMessageBuilder.build(AppCode.GO_TO_LOGIN_CODE), response);
	    return;
	}
	if (!CertificationType.SUCCESS.name().equals(current.getCertificationType())) {
	    writeAjaxJSONResponse(ResultMessageBuilder.build(AppCode.ACCOUNT_CERTIFICATION_CODE), response);
	    return;
	}

	OrderApply orderApply = orderApplyService.findOrderApplyByAccountId(param.getOrderId(), current.getId());
	if (orderApply != null && !OrderApplyStatus.REVOKE.name().equals(orderApply.getApplyStatus())) {
	    writeAjaxJSONResponse(ResultMessageBuilder.build(AppCode.FAIL_CODE, "您已经申请过此捐赠！"), response);
	    return;
	}

	orderApply = new OrderApply();
	BeanUtils.copyProperties(param, orderApply);
	orderApply.setAccountId(current.getId());
	orderApply.setApplyType(OrderType.DONATION.name());
	orderApply.setBackdropImg(QiniuImageUtil.replaceUrl(param.getBackdropImg()));
	orderApply.setBackdropBottomImg(QiniuImageUtil.replaceUrl(param.getBackdropBottomImg()));
	DubboResponse<String> dResponse = orderApplyService.addOrderApplyByDonation(orderApply);
	if (!DubboResponseCode.SUCCESS.equals(dResponse.getCode())) {
	    writeAjaxJSONResponse(ResultMessageBuilder.build(AppCode.FAIL_CODE, dResponse.getMsg()), response);
	    return;
	}
	writeAjaxJSONResponse(ResultMessageBuilder.build(AppCode.SUCCESS_CODE), response);

    }

    /**
     * 撤回获得捐赠申请
     * 
     * @param request
     * @param response
     */
    @RequestMapping(value = "/apply/donation/withdraw")
    public void applyDonationWithdraw(BaseParam param, Long applyId, HttpServletRequest request, HttpServletResponse response) {
	Account current = accountService.currentAccount(param.getAccessToken());
	if (null == current) {
	    writeAjaxJSONResponse(ResultMessageBuilder.build(AppCode.GO_TO_LOGIN_CODE), response);
	    return;
	}

	OrderApply orderApply = orderApplyService.findOrderApplyById(applyId);
	if (orderApply != null) {
	    if (current.getId().equals(orderApply.getAccountId())) {
		writeAjaxJSONResponse(ResultMessageBuilder.build(AppCode.FAIL_CODE, "不能删除非本人生成的数据！"), response);
		return;
	    }
	    orderApply.setApplyStatus(OrderApplyStatus.REVOKE.name());
	    orderApplyService.updateOrderApply(orderApply);
	}

	writeAjaxJSONResponse(ResultMessageBuilder.build(AppCode.SUCCESS_CODE), response);
    }

    /**
     * 实现捐赠确认
     * 
     * @param request
     * @param response
     */
    @RequestMapping(value = "/apply/donation/confirm")
    public void applyDonationConfirm(BaseParam param, Long applyId, String mailCode, HttpServletRequest request, HttpServletResponse response) {
	Account current = accountService.currentAccount(param.getAccessToken());
	if (null == current) {
	    writeAjaxJSONResponse(ResultMessageBuilder.build(AppCode.GO_TO_LOGIN_CODE), response);
	    return;
	}
	OrderApply orderApply = orderApplyService.findOrderApplyById(applyId);
	if (orderApply == null || !OrderApplyStatus.INITIAL.name().equals(orderApply.getApplyStatus())) {
	    writeAjaxJSONResponse(ResultMessageBuilder.build(AppCode.FAIL_CODE, "捐赠申请不存在或状态已改变！"), response);
	    return;
	}
	Order order = orderService.findOrderById(orderApply.getOrderId());
	if (order == null || !OrderStatus.INITIAL.name().equals(order.getOrderStatus())) {
	    writeAjaxJSONResponse(ResultMessageBuilder.build(AppCode.FAIL_CODE, "捐赠不存在或状态已改变！"), response);
	    return;
	}
	if (!current.getId().equals(order.getInitiatorId())) {
	    writeAjaxJSONResponse(ResultMessageBuilder.build(AppCode.FAIL_CODE, "只能实现自己的捐赠！"), response);
	    return;
	}
	orderApply.setApplyStatus(OrderApplyStatus.SUCCESS.name());
	order.setMailAddress(orderApply.getMailAddress());
	order.setMailName(orderApply.getMailName());
	order.setMailPhone(orderApply.getMailPhone());
	order.setMailCode(mailCode);
	order.setMailStatus(MailStatus.GOING.name());
	orderApplyService.updateOrderApply(orderApply, order);
	writeAjaxJSONResponse(ResultMessageBuilder.build(AppCode.SUCCESS_CODE), response);
    }

    /**
     * 捐赠申请单列表
     * 
     * @param request
     * @param response
     */
    @RequestMapping(value = "/apply/donation/page")
    public void applyPage(BasePageParam param, Long orderId, HttpServletRequest request, HttpServletResponse response) {
	OrderApplyPageVO pageVO = new OrderApplyPageVO();
	Page<OrderApply> page = orderApplyService.findOrderApplyByPage(orderId, param.getPageNum(), param.getPageSize());

	// 加载
	if (null != page && CollectionUtils.isNotEmpty(page.getResult())) {
	    pageVO.setPageAll(param.getPageNum(), param.getPageSize(), page.getTotalPage(), page.getTotalCount());
	    OrderApplySimpleVO VO = null;
	    Account account = null;
	    List<OrderApplySimpleVO> VOs = new ArrayList<OrderApplySimpleVO>();
	    for (OrderApply orderApply : page.getResult()) {
		VO = new OrderApplySimpleVO();
		VO.convertPOToVO(orderApply);
		account = accountService.getAccountById(orderApply.getAccountId());
		if (account != null) {
		    VO.setAccountNike(account.getNickName());
		    VO.setAccountImg(QiniuImageUtil.newImageUrl60(account.getPhoto()));
		}
		VOs.add(VO);
	    }
	    pageVO.setApplys(VOs);

	}
	writeAjaxJSONResponse(ResultMessageBuilder.build(pageVO), response);
    }

    /**
     * 申请单详情
     * 
     * @param request
     * @param response
     */
    @RequestMapping(value = "/apply/detail")
    public void applyDetail(Long applyId, HttpServletRequest request, HttpServletResponse response) {
	OrderApply orderApply = orderApplyService.findOrderApplyById(applyId);
	OrderApplyVO VO = new OrderApplyVO();
	VO.convertPOToVO(orderApply);
	Account account = accountService.getAccountById(orderApply.getAccountId());
	if (account != null) {
	    VO.setAccountNike(account.getNickName());
	    VO.setAccountImg(QiniuImageUtil.newImageUrl60(account.getPhoto()));
	}
	writeAjaxJSONResponse(ResultMessageBuilder.build(VO), response);
    }

    /**
     * 我和订单的关系
     * 
     */
    @RequestMapping(value = "/with/me")
    public void withMe(BaseParam param, Long orderId, HttpServletRequest request, HttpServletResponse response) {
	Account current = accountService.currentAccount(param.getAccessToken());
	if (null == current) {
	    writeAjaxJSONResponse(ResultMessageBuilder.build(AppCode.GO_TO_LOGIN_CODE), response);
	    return;
	}
	OrderWithMeVO VO = new OrderWithMeVO();
	Order order = orderService.findOrderById(orderId);
	if (order != null && current.getId().equals(order.getInitiatorId())) {
	    VO.setMyOrder(Boolean.TRUE);
	} else {
	    VO.setMyOrder(Boolean.FALSE);
	}

	OrderApply orderApply = orderApplyService.findOrderApplyByAccountId(orderId, current.getId());
	if (orderApply != null) {
	    VO.setMyApply(Boolean.TRUE);
	    VO.setApplyStatus(orderApply.getApplyStatus());
	} else {
	    VO.setMyApply(Boolean.FALSE);
	}

	writeAjaxJSONResponse(ResultMessageBuilder.build(VO), response);
    }

    /**
     * 信纸列表
     * 
     * @param request
     * @param response
     */
    @RequestMapping(value = "/stationery")
    public void stationery(HttpServletRequest request, HttpServletResponse response) {
	List<StationeryVO> stationerys = new ArrayList<StationeryVO>();
	StationeryVO VO = null;
	for (int i = 0; i < 9; i++) {
	    VO = new StationeryVO();
	    VO.setStationeryTitle("经典" + i);
	    VO.setStationeryImg(QiniuImageUtil.newImageUrl("xz1.jpg"));
	    VO.setStationeryTopImg(QiniuImageUtil.newImageUrl("xz1.jpg"));
	    VO.setStationeryBottomImg(QiniuImageUtil.newImageUrl("xz1.jpg"));
	    stationerys.add(VO);
	}
	StationeryPageVO pageVO = new StationeryPageVO();
	// 加载
	pageVO.setStationerys(stationerys);
	writeAjaxJSONResponse(ResultMessageBuilder.build(pageVO), response);
    }

}
