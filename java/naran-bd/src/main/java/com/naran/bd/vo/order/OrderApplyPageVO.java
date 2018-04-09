package com.naran.bd.vo.order;

import java.util.List;

import com.naran.foundation.util.BasePageVO;

/**
 * 展示封装（分页）
 * 
 * @author zefeng.xu
 */
public class OrderApplyPageVO extends BasePageVO {

    private List<OrderApplySimpleVO> applys;

    public List<OrderApplySimpleVO> getApplys() {
	return applys;
    }

    public void setApplys(List<OrderApplySimpleVO> applys) {
	this.applys = applys;
    }

}
