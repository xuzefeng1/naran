package com.naran.core.dao.order;

import com.naran.core.entity.order.Order;
import com.naran.foundation.mybatis.page.Page;

/**
 * 订单数据访问接口
 * 
 * @author zefeng.xu
 */
public interface OrderDao {

    Long addOrder(Order order);

    void updateOrder(Order order);

    void deleteOrder(Long id);

    Order findOrderById(Long id);

    Page<Order> findOrderByPage(String orderType, String agingDegree, String commodityType, String city, String province, String orderByType, int pageNum, int pageSize);

    Page<Order> findOrderByInitiatorPage(String orderType, Long initiatorId, int pageNum, int pageSize);

    Page<Order> findOrderByObtainPage(Long accountId, int pageNum, int pageSize);

    Page<Order> findOrderBySendPage(Long accountId, int pageNum, int pageSize);

}
