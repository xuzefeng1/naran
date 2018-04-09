package com.naran.core.dao.order;

import com.naran.core.entity.order.OrderFeedback;
import com.naran.foundation.mybatis.page.Page;

/**
 * 订单报名单数据访问接口
 * 
 * @author zefeng.xu
 */
public interface OrderFeedbackDao {

    Long addOrderFeedback(OrderFeedback orderFeedback);

    void updateOrderFeedback(OrderFeedback orderFeedback);

    void deleteOrderFeedback(Long id);

    OrderFeedback findOrderFeedbackById(Long id);

    OrderFeedback findOrderFeedbackByOrderId(Long orderId);

    Page<OrderFeedback> findOrderFeedbackByPage(int pageNum, int pageSize);

}
