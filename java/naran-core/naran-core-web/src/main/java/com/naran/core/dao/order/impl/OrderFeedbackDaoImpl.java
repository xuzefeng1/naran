package com.naran.core.dao.order.impl;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.naran.core.dao.order.OrderFeedbackDao;
import com.naran.core.entity.order.OrderFeedback;
import com.naran.foundation.mybatis.dao.MyBatisDAO;
import com.naran.foundation.mybatis.page.Page;
import com.naran.foundation.mybatis.page.PageRequest;

/**
 * 
 * @author zefeng.xu
 */
@Repository
public class OrderFeedbackDaoImpl implements OrderFeedbackDao {

    private static final String ADD_ORDER_FEEDBACK = "addOrderFeedback";
    private static final String UPDATE_ORDER_FEEDBACK = "updateOrderFeedback";
    private static final String FIND_ORDER_FEEDBACK_BY_ID = "findOrderFeedbackById";
    private static final String FIND_ORDER_FEEDBACK_BY_ORDERID = "findOrderFeedbackByOrderId";
    private static final String FIND_ORDER_FEEDBACK_BY_PAGE = "findOrderFeedbackByPage";

    @Autowired
    private MyBatisDAO myBatisDAO;

    @Override
    public Long addOrderFeedback(OrderFeedback orderFeedback) {
	orderFeedback.setCreateTime(new Date());
	orderFeedback.setUpdateTime(new Date());
	orderFeedback.setExpired(Boolean.TRUE);
	myBatisDAO.insert(ADD_ORDER_FEEDBACK, orderFeedback);

	return orderFeedback.getId();
    }

    @Override
    public void updateOrderFeedback(OrderFeedback orderFeedback) {
	orderFeedback.setUpdateTime(new Date());
	myBatisDAO.update(UPDATE_ORDER_FEEDBACK, orderFeedback);
    }

    @Override
    public void deleteOrderFeedback(Long id) {
	OrderFeedback orderFeedback = new OrderFeedback();
	orderFeedback.setId(id);
	orderFeedback.setExpired(Boolean.FALSE);
	myBatisDAO.update(UPDATE_ORDER_FEEDBACK, orderFeedback);
    }

    @Override
    public OrderFeedback findOrderFeedbackById(Long id) {
	if (id == null) {
	    return null;
	}
	return (OrderFeedback) myBatisDAO.findForObject(FIND_ORDER_FEEDBACK_BY_ID, id);
    }

    @Override
    public OrderFeedback findOrderFeedbackByOrderId(Long orderId) {
	if (orderId == null) {
	    return null;
	}
	return (OrderFeedback) myBatisDAO.findForObject(FIND_ORDER_FEEDBACK_BY_ORDERID, orderId);
    }

    @SuppressWarnings("unchecked")
    @Override
    public Page<OrderFeedback> findOrderFeedbackByPage(int pageNum, int pageSize) {
	Map<String, Object> param = new HashMap<String, Object>();
	return myBatisDAO.findForPage(FIND_ORDER_FEEDBACK_BY_PAGE, new PageRequest(pageNum, pageSize, param));
    }

}
