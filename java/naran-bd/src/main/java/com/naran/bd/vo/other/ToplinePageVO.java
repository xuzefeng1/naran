package com.naran.bd.vo.other;

import java.util.List;

import com.naran.foundation.util.BasePageVO;

/**
 * 展示封装（分页）
 * 
 * @author zefeng.xu
 */
public class ToplinePageVO extends BasePageVO {

    private List<ToplineVO> toplines;

    public List<ToplineVO> getToplines() {
	return toplines;
    }

    public void setToplines(List<ToplineVO> toplines) {
	this.toplines = toplines;
    }

}
