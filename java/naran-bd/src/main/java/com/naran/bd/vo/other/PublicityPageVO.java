package com.naran.bd.vo.other;

import java.util.List;

import com.naran.foundation.util.BasePageVO;

/**
 * 展示封装（分页）
 * 
 * @author zefeng.xu
 */
public class PublicityPageVO extends BasePageVO {

    private List<PublicityVO> publicitys;

    public List<PublicityVO> getPublicitys() {
	return publicitys;
    }

    public void setPublicitys(List<PublicityVO> publicitys) {
	this.publicitys = publicitys;
    }

}
