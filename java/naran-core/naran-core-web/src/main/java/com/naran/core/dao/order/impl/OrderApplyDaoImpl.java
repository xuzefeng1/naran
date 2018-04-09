package com.naran.core.dao.order.impl;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.naran.core.dao.order.OrderApplyDao;
import com.naran.core.entity.order.OrderApply;
import com.naran.core.enums.OrderApplyStatus;
import com.naran.foundation.mybatis.dao.MyBatisDAO;
import com.naran.foundation.mybatis.page.Page;
import com.naran.foundation.mybatis.page.PageRequest;

/**
 * 
 * @author zefeng.xu
 */
@Repository
public class OrderApplyDaoImpl implements OrderApplyDao {

    private static final String ADD_ORDER_APPLY = "addOrderApply";
    private static final String UPDATE_ORDER_APPLY = "updateOrderApply";
    private static final String FIND_ORDER_APPLY_BY_ID = "findOrderApplyById";
    private static final String FIND_ORDER_APPLY_BY_ACCOUNT_ID = "findOrderApplyByAccountId";
    private static final String FIND_ORDER_APPLY_BY_PAGE = "findOrderApplyByPage";

    @Autowired
    private MyBatisDAO myBatisDAO;

    @Override
    public Long addOrderApply(OrderApply orderApply) {
	orderApply.setCreateTime(new Date());
	orderApply.setUpdateTime(new Date());
	orderApply.setExpired(Boolean.TRUE);
	orderApply.setApplyStatus(OrderApplyStatus.INITIAL.name());
	myBatisDAO.insert(ADD_ORDER_APPLY, orderApply);

	return orderApply.getId();
    }

    @Override
    public void updateOrderApply(OrderApply orderApply) {
	orderApply.setUpdateTime(new Date());
	myBatisDAO.update(UPDATE_ORDER_APPLY, orderApply);
    }

    @Override
    public void deleteOrderApply(Long id) {
	OrderApply orderApply = new OrderApply();
	orderApply.setId(id);
	orderApply.setExpired(Boolean.FALSE);
	myBatisDAO.update(UPDATE_ORDER_APPLY, orderApply);
    }

    @Override
    public OrderApply findOrderApplyById(Long id) {
	if (id == null) {
	    return null;
	}
	return (OrderApply) myBatisDAO.findForObject(FIND_ORDER_APPLY_BY_ID, id);
    }

    @Override
    public OrderApply findOrderApplyByAccountId(Long orderId, Long accountId) {
	if (orderId == null || accountId == null) {
	    return null;
	}
	Map<String, Object> param = new HashMap<String, Object>();
	param.put("orderId", orderId);
	param.put("accountId", accountId);
	return (OrderApply) myBatisDAO.findForObject(FIND_ORDER_APPLY_BY_ACCOUNT_ID, param);
    }

    @SuppressWarnings("unchecked")
    @Override
    public Page<OrderApply> findOrderApplyByPage(Long orderId, int pageNum, int pageSize) {
	Map<String, Object> param = new HashMap<String, Object>();
	param.put("orderId", orderId);
	return myBatisDAO.findForPage(FIND_ORDER_APPLY_BY_PAGE, new PageRequest(pageNum, pageSize, param));
    }

}
