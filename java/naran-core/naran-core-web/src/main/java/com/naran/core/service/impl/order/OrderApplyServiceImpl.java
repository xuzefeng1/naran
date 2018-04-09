package com.naran.core.service.impl.order;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.naran.core.dao.order.OrderApplyDao;
import com.naran.core.entity.order.Order;
import com.naran.core.entity.order.OrderApply;
import com.naran.core.enums.OrderApplyStatus;
import com.naran.core.enums.OrderStatus;
import com.naran.core.enums.OrderType;
import com.naran.dubbo.response.DubboResponse;
import com.naran.dubbo.response.DubboResponseCode;
import com.naran.dubbo.service.account.IAccountService;
import com.naran.dubbo.service.order.IOrderApplyService;
import com.naran.dubbo.service.order.IOrderService;
import com.naran.foundation.mybatis.page.Page;

/**
 * 
 * @author zefeng.xu
 */
@Transactional
@Service("orderApplyService")
public class OrderApplyServiceImpl implements IOrderApplyService {

    @Autowired
    private OrderApplyDao orderApplyDao;
    @Autowired
    private IOrderService orderService;
    @Autowired
    private IAccountService accountService;

    @Override
    public Long addOrderApply(OrderApply orderApply) {
	if (orderApply == null) {
	    return null;
	}
	return orderApplyDao.addOrderApply(orderApply);
    }

    @Override
    public void updateOrderApply(OrderApply orderApply) {
	if (orderApply == null || orderApply.getId() == null) {
	    return;
	}
	orderApplyDao.updateOrderApply(orderApply);

    }

    @Override
    public void updateOrderApplyByRevoke(OrderApply orderApply) {
	Order order = orderService.findOrderById(orderApply.getOrderId());
	if (order != null && orderApply != null && OrderType.WISH.name().equals(order.getOrderType())) {
	    order.setOrderStatus(OrderStatus.INITIAL.name());
	    orderService.updateOrder(order);
	}
	orderApply.setApplyStatus(OrderApplyStatus.REVOKE.name());
	orderApplyDao.updateOrderApply(orderApply);
    }

    @Override
    public void deleteOrderApply(Long id) {
	if (id == null) {
	    return;
	}
	orderApplyDao.deleteOrderApply(id);

    }

    @Override
    public OrderApply findOrderApplyById(Long id) {
	if (id == null) {
	    return null;
	}
	return orderApplyDao.findOrderApplyById(id);
    }

    @Override
    public Page<OrderApply> findOrderApplyByPage(Long orderId, int pageNum, int pageSize) {
	return orderApplyDao.findOrderApplyByPage(orderId, pageNum, pageSize);
    }

    @Override
    public DubboResponse<String> addOrderApplyByWish(OrderApply orderApply) {
	DubboResponse<String> dResponse = new DubboResponse<String>();
	Order order = orderService.findOrderById(orderApply.getOrderId());
	if (order == null || !OrderStatus.INITIAL.name().equals(order.getOrderStatus())) {
	    dResponse.setCode(DubboResponseCode.FAIL);
	    dResponse.setMsg("该订单已结束或正在匹配中！");
	    return dResponse;
	}
	if (order.getInitiatorId().equals(orderApply.getAccountId())) {
	    dResponse.setCode(DubboResponseCode.FAIL);
	    dResponse.setMsg("不能申请实现自己的愿望！");
	    return dResponse;
	}
	order.setOrderStatus(OrderStatus.GOING.name());
	orderService.updateOrder(order);
	orderApplyDao.addOrderApply(orderApply);
	return dResponse;
    }

    @Override
    public DubboResponse<String> addOrderApplyByDonation(OrderApply orderApply) {
	DubboResponse<String> dResponse = new DubboResponse<String>();
	Order order = orderService.findOrderById(orderApply.getOrderId());
	if (order == null || OrderStatus.REVOKE.name().equals(order.getOrderStatus()) || OrderStatus.SUCCESS.name().equals(order.getOrderStatus()) || OrderStatus.FAIL.name().equals(order.getOrderStatus())) {
	    dResponse.setCode(DubboResponseCode.FAIL);
	    dResponse.setMsg("该订单已结束！");
	    return dResponse;
	}
	if (order.getInitiatorId().equals(orderApply.getAccountId())) {
	    dResponse.setCode(DubboResponseCode.FAIL);
	    dResponse.setMsg("不能申请获得自己的捐赠！");
	    return dResponse;
	}
	orderApplyDao.addOrderApply(orderApply);
	return dResponse;
    }

    @Override
    public void updateOrderApply(OrderApply orderApply, Order order) {

	if (OrderApplyStatus.SUCCESS.name().equals(orderApply.getApplyStatus())) {
	    order.setOrderStatus(OrderStatus.WAITING.name());
	    orderService.updateOrder(order);
	} else {
	    if (OrderType.WISH.name().equals(order.getOrderType())) {
		order.setOrderStatus(OrderStatus.INITIAL.name());
		orderService.updateOrder(order);
	    }
	}
	orderApplyDao.updateOrderApply(orderApply);
    }

    @Override
    public OrderApply findOrderApplyByAccountId(Long orderId, Long accountId) {
	return orderApplyDao.findOrderApplyByAccountId(orderId, accountId);
    }

}
