package com.naran.core.service.impl.order;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.naran.core.dao.order.OrderFeedbackDao;
import com.naran.core.entity.order.Order;
import com.naran.core.entity.order.OrderFeedback;
import com.naran.core.enums.MailStatus;
import com.naran.core.enums.OrderStatus;
import com.naran.dubbo.service.order.IOrderFeedbackService;
import com.naran.dubbo.service.order.IOrderService;
import com.naran.foundation.mybatis.page.Page;

/**
 * 
 * @author zefeng.xu
 */
@Transactional
@Service("orderFeedbackService")
public class OrderFeedbackServiceImpl implements IOrderFeedbackService {

    @Autowired
    private OrderFeedbackDao orderFeedbackDao;

    @Autowired
    private IOrderService orderService;

    @Override
    public Long addOrderFeedback(OrderFeedback orderFeedback) {
	if (orderFeedback == null) {
	    return null;
	}
	Order order = orderService.findOrderById(orderFeedback.getOrderId());
	order.setOrderStatus(OrderStatus.SUCCESS.name());
	order.setMailStatus(MailStatus.SUCCESS.name());
	order.setRecipientId(orderFeedback.getAccountId());
	orderService.updateOrder(order);
	return orderFeedbackDao.addOrderFeedback(orderFeedback);
    }

    @Override
    public void updateOrderFeedback(OrderFeedback orderFeedback) {
	if (orderFeedback == null || orderFeedback.getId() == null) {
	    return;
	}
	orderFeedbackDao.updateOrderFeedback(orderFeedback);

    }

    @Override
    public void deleteOrderFeedback(Long id) {
	if (id == null) {
	    return;
	}
	orderFeedbackDao.deleteOrderFeedback(id);

    }

    @Override
    public OrderFeedback findOrderFeedbackById(Long id) {
	if (id == null) {
	    return null;
	}
	return orderFeedbackDao.findOrderFeedbackById(id);
    }

    @Override
    public OrderFeedback findOrderFeedbackByOrderId(Long orderId) {
	if (orderId == null) {
	    return null;
	}
	return orderFeedbackDao.findOrderFeedbackByOrderId(orderId);
    }

    @Override
    public Page<OrderFeedback> findOrderFeedbackByPage(int pageNum, int pageSize) {

	return orderFeedbackDao.findOrderFeedbackByPage(pageNum, pageSize);
    }

}
