package com.naran.dubbo.service.order;

import com.naran.core.entity.order.OrderFeedback;
import com.naran.foundation.mybatis.page.Page;

/**
 * 订单反馈服务接口
 * 
 * @author zefeng.xu
 */
public interface IOrderFeedbackService {

    Long addOrderFeedback(OrderFeedback orderFeedback);

    void updateOrderFeedback(OrderFeedback orderFeedback);

    void deleteOrderFeedback(Long id);

    OrderFeedback findOrderFeedbackById(Long id);

    OrderFeedback findOrderFeedbackByOrderId(Long orderId);

    /**
     * 获取（分页）
     * 
     * @return
     */
    Page<OrderFeedback> findOrderFeedbackByPage(int pageNum, int pageSize);

}
