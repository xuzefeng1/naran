package com.naran.core.dao.order;

import com.naran.core.entity.order.OrderApply;
import com.naran.foundation.mybatis.page.Page;

/**
 * 订单报名单数据访问接口
 * 
 * @author zefeng.xu
 */
public interface OrderApplyDao {

    Long addOrderApply(OrderApply orderApply);

    void updateOrderApply(OrderApply orderApply);

    void deleteOrderApply(Long id);

    OrderApply findOrderApplyById(Long id);

    Page<OrderApply> findOrderApplyByPage(Long orderId, int pageNum, int pageSize);

    OrderApply findOrderApplyByAccountId(Long orderId, Long accountId);

}
