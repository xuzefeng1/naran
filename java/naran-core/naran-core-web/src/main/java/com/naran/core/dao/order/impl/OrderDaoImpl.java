package com.naran.core.dao.order.impl;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.naran.core.dao.order.OrderDao;
import com.naran.core.entity.order.Order;
import com.naran.core.enums.OrderByType;
import com.naran.foundation.mybatis.dao.MyBatisDAO;
import com.naran.foundation.mybatis.page.Page;
import com.naran.foundation.mybatis.page.PageRequest;

/**
 * 
 * @author zefeng.xu
 */
@Repository
public class OrderDaoImpl implements OrderDao {

    private static final String ADD_ORDER = "addOrder";
    private static final String UPDATE_ORDER = "updateOrder";
    private static final String FIND_ORDER_BY_ID = "findOrderById";
    private static final String FIND_ORDER_BY_PAGE = "findOrderByPage";
    private static final String FIND_ORDER_BY_OBTAIN_PAGE = "findOrderByObtainPage";
    private static final String FIND_ORDER_BY_SEND_PAGE = "findOrderBySendPage";

    @Autowired
    private MyBatisDAO myBatisDAO;

    @Override
    public Long addOrder(Order order) {
	order.setCreateTime(new Date());
	order.setUpdateTime(new Date());
	myBatisDAO.insert(ADD_ORDER, order);

	return order.getId();
    }

    @Override
    public void updateOrder(Order order) {
	order.setUpdateTime(new Date());
	myBatisDAO.update(UPDATE_ORDER, order);
    }

    @Override
    public void deleteOrder(Long id) {
	Order order = new Order();
	order.setId(id);
	order.setExpired(Boolean.FALSE);
	myBatisDAO.update(UPDATE_ORDER, order);
    }

    @Override
    public Order findOrderById(Long id) {
	if (id == null) {
	    return null;
	}
	return (Order) myBatisDAO.findForObject(FIND_ORDER_BY_ID, id);
    }

    @SuppressWarnings("unchecked")
    @Override
    public Page<Order> findOrderByPage(String orderType, String agingDegree, String commodityType, String city, String province, String orderByType, int pageNum, int pageSize) {
	Map<String, Object> param = new HashMap<String, Object>();
	param.put("orderType", orderType);
	param.put("agingDegree", agingDegree);
	param.put("commodityType", commodityType);
	param.put("city", city);
	param.put("province", province);
	param.put("orderByType", orderByType);

	return myBatisDAO.findForPage(FIND_ORDER_BY_PAGE, new PageRequest(pageNum, pageSize, param));
    }

    @SuppressWarnings("unchecked")
    @Override
    public Page<Order> findOrderByInitiatorPage(String orderType, Long initiatorId, int pageNum, int pageSize) {
	Map<String, Object> param = new HashMap<String, Object>();
	param.put("orderType", orderType);
	param.put("initiatorId", initiatorId);
	param.put("orderByType", OrderByType.NEW.name());
	return myBatisDAO.findForPage(FIND_ORDER_BY_PAGE, new PageRequest(pageNum, pageSize, param));
    }

    @SuppressWarnings("unchecked")
    @Override
    public Page<Order> findOrderByObtainPage(Long accountId, int pageNum, int pageSize) {
	Map<String, Object> param = new HashMap<String, Object>();
	param.put("accountId", accountId);
	return myBatisDAO.findForPage(FIND_ORDER_BY_OBTAIN_PAGE, new PageRequest(pageNum, pageSize, param));
    }

    @SuppressWarnings("unchecked")
    @Override
    public Page<Order> findOrderBySendPage(Long accountId, int pageNum, int pageSize) {
	Map<String, Object> param = new HashMap<String, Object>();
	param.put("accountId", accountId);
	return myBatisDAO.findForPage(FIND_ORDER_BY_SEND_PAGE, new PageRequest(pageNum, pageSize, param));
    }

}
