package com.naran.dubbo.service.order;

import com.naran.core.entity.order.Order;
import com.naran.core.entity.order.OrderApply;
import com.naran.dubbo.response.DubboResponse;
import com.naran.foundation.mybatis.page.Page;

/**
 * 订单报名单服务接口
 * 
 * @author zefeng.xu
 */
public interface IOrderApplyService {

    Long addOrderApply(OrderApply orderApply);

    DubboResponse<String> addOrderApplyByWish(OrderApply orderApply);

    DubboResponse<String> addOrderApplyByDonation(OrderApply orderApply);

    void updateOrderApply(OrderApply orderApply, Order order);

    void updateOrderApply(OrderApply orderApply);
    
    void updateOrderApplyByRevoke(OrderApply orderApply);

    void deleteOrderApply(Long id);

    OrderApply findOrderApplyById(Long id);

    /**
     * 获取（分页）
     * 
     * @return
     */
    Page<OrderApply> findOrderApplyByPage(Long orderId, int pageNum, int pageSize);

    OrderApply findOrderApplyByAccountId(Long orderId, Long accountId);

   

}
