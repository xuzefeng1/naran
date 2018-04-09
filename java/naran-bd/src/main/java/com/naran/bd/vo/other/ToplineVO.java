package com.naran.bd.vo.other;

import org.springframework.beans.BeanUtils;

import com.naran.core.entity.other.Topline;
import com.naran.foundation.util.BaseVO;
import com.naran.foundation.util.DateUtil;

/**
 * 轮播图展示封装
 * 
 * @author zefeng.xu
 */
public class ToplineVO implements BaseVO {

    private Long toplineId;

    private String toplineTitle;

    private String toplineContent;

    private String toplineType;

    private String createTime;

    public Long getToplineId() {
	return toplineId;
    }

    public void setToplineId(Long toplineId) {
	this.toplineId = toplineId;
    }

    public String getToplineTitle() {
	return toplineTitle;
    }

    public void setToplineTitle(String toplineTitle) {
	this.toplineTitle = toplineTitle;
    }

    public String getToplineContent() {
	return toplineContent;
    }

    public void setToplineContent(String toplineContent) {
	this.toplineContent = toplineContent;
    }

    public String getToplineType() {
	return toplineType;
    }

    public void setToplineType(String toplineType) {
	this.toplineType = toplineType;
    }

    public String getCreateTime() {
	return createTime;
    }

    public void setCreateTime(String createTime) {
	this.createTime = createTime;
    }

    @Override
    public void convertPOToVO(Object poObj) {
	if (null == poObj) {
	    return;
	}

	Topline po = (Topline) poObj;
	BeanUtils.copyProperties(po, this);
	this.toplineId = po.getId();
	this.createTime = DateUtil.formatDateYMDHMS(po.getCreateTime());

    }

}
