package com.naran.core.service.impl.order;

import java.util.Date;

import org.apache.commons.lang.math.NumberUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.naran.core.dao.order.OrderDao;
import com.naran.core.entity.order.Order;
import com.naran.core.entity.questionnaire.QuestionnaireOption;
import com.naran.core.enums.MailStatus;
import com.naran.core.enums.OrderStatus;
import com.naran.core.enums.OrderType;
import com.naran.dubbo.service.order.IOrderService;
import com.naran.dubbo.service.questionnaire.IQuestionnaireOptionService;
import com.naran.foundation.mybatis.page.Page;
import com.naran.foundation.util.StringUtil;

/**
 * 
 * @author zefeng.xu
 */
@Transactional
@Service("orderService")
public class OrderServiceImpl implements IOrderService {

    @Autowired
    private OrderDao orderDao;
    @Autowired
    private IQuestionnaireOptionService questionnaireOptionService;

    @Override
    public Long addOrder(Order order) {
	if (order == null) {
	    return null;
	}
	order.setCreateTime(new Date());
	order.setUpdateTime(new Date());
	order.setExpired(Boolean.TRUE);
	order.setOrderStatus(OrderStatus.INITIAL.name());
	order.setMailStatus(MailStatus.INITIAL.name());
	return orderDao.addOrder(order);
    }

    @Override
    public Long addOrderByWish(Order order) {
	order.setOrderType(OrderType.WISH.name());
	return addOrder(order);
    }

    @Override
    public Long addOrderByDonation(Order order, String questionnaireOptionIds) {
	order.setOrderType(OrderType.DONATION.name());
	if (StringUtil.isNotBlank(questionnaireOptionIds)) {
	    String[] optionIds = questionnaireOptionIds.split(StringUtil.DEFAULT_BOUND_SYMBOL);
	    String[] allnames = new String[optionIds.length];
	    Long optionId = null;
	    QuestionnaireOption option = null;
	    for (int i = 0; i < optionIds.length; i++) {
		optionId = NumberUtils.toLong(optionIds[i]);
		option = questionnaireOptionService.findQuestionnaireOptionById(optionId);
		if (option != null) {
		    allnames[i] = option.getOptionContent();
		}
	    }
	    order.setQuestionnaireOptions(StringUtil.join(allnames, StringUtil.DEFAULT_COMBINER));
	}
	return addOrder(order);
    }

    @Override
    public void updateOrder(Order order) {
	if (order == null || order.getId() == null) {
	    return;
	}
	order.setUpdateTime(new Date());
	orderDao.updateOrder(order);

    }

    @Override
    public void deleteOrder(Long id) {
	if (id == null) {
	    return;
	}
	orderDao.deleteOrder(id);

    }

    @Override
    public Order findOrderById(Long id) {
	if (id == null) {
	    return null;
	}
	Order order = orderDao.findOrderById(id);
	order.setReadTimes(addTimes(order.getReadTimes()));
	orderDao.updateOrder(order);
	return order;
    }

    private Integer addTimes(Integer times) {
	if (times == null) {
	    times = 0;
	}
	return times + 1;
    }

    @Override
    public Page<Order> findOrderByPage(String orderType, String agingDegree, String commodityType, String city, String province, String orderByType, int pageNum, int pageSize) {

	return orderDao.findOrderByPage(orderType, agingDegree, commodityType, city, province, orderByType, pageNum, pageSize);
    }

    @Override
    public Page<Order> findOrderByInitiatorPage(String orderType, Long initiatorId, int pageNum, int pageSize) {
	return orderDao.findOrderByInitiatorPage(orderType, initiatorId, pageNum, pageSize);
    }

    @Override
    public Page<Order> findOrderByObtainPage(Long accountId, int pageNum, int pageSize) {
	if (accountId == null) {
	    return null;
	}
	return orderDao.findOrderByObtainPage(accountId, pageNum, pageSize);
    }

    @Override
    public Page<Order> findOrderBySendPage(Long accountId, int pageNum, int pageSize) {
	if (accountId == null) {
	    return null;
	}
	return orderDao.findOrderBySendPage(accountId, pageNum, pageSize);
    }

}
