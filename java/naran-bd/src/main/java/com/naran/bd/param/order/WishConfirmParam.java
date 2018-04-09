package com.naran.bd.param.order;

import com.naran.bd.param.BaseParam;

/**
 * 心愿确认入参
 * 
 * */
public class WishConfirmParam extends BaseParam {

    private Long applyId;

    private Long applyStatus;// 确认状态

    public Long getApplyId() {
	return applyId;
    }

    public void setApplyId(Long applyId) {
	this.applyId = applyId;
    }

    public Long getApplyStatus() {
	return applyStatus;
    }

    public void setApplyStatus(Long applyStatus) {
	this.applyStatus = applyStatus;
    }

}
