package com.naran.dubbo.service.order;

import com.naran.core.entity.order.Order;
import com.naran.foundation.mybatis.page.Page;

/**
 * 订单服务接口
 * 
 * @author zefeng.xu
 */
public interface IOrderService {

    Long addOrder(Order order);

    Long addOrderByWish(Order order);

    Long addOrderByDonation(Order order, String questionnaireOptionIds);

    void updateOrder(Order order);

    void deleteOrder(Long id);

    Order findOrderById(Long id);

    /**
     * 获取（分页）
     * 
     * @return
     */
    Page<Order> findOrderByPage(String orderType, String agingDegree, String commodityType, String city, String province, String orderByType, int pageNum, int pageSize);

    Page<Order> findOrderByInitiatorPage(String orderType, Long initiatorId, int pageNum, int pageSize);

    Page<Order> findOrderByObtainPage(Long accountId, int pageNum, int pageSize);

    Page<Order> findOrderBySendPage(Long accountId, int pageNum, int pageSize);

}
