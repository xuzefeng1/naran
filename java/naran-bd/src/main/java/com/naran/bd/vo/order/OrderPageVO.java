package com.naran.bd.vo.order;

import java.util.List;

import com.naran.foundation.util.BasePageVO;

/**
 * 展示封装（分页）
 * 
 * @author zefeng.xu
 */
public class OrderPageVO extends BasePageVO {

    private List<OrderSimpleVO> orders;

    public List<OrderSimpleVO> getOrders() {
	return orders;
    }

    public void setOrders(List<OrderSimpleVO> orders) {
	this.orders = orders;
    }

}
