package com.naran.web.vo.other;

import org.springframework.beans.BeanUtils;

import com.naran.core.entity.other.Publicity;
import com.naran.foundation.util.BaseVO;
import com.naran.foundation.util.DateUtil;
import com.naran.foundation.util.file.qiniu.QiniuImageUtil;

/**
 * 轮播图展示封装
 * 
 * @author zefeng.xu
 */
public class PublicityVO implements BaseVO {

    private Long publicityId;

    private String publicityType;

    private String publicityName;

    private String publicityImg;

    private String publicityCategory;

    private String createTime;

    public Long getPublicityId() {
	return publicityId;
    }

    public void setPublicityId(Long publicityId) {
	this.publicityId = publicityId;
    }

    public String getPublicityType() {
	return publicityType;
    }

    public void setPublicityType(String publicityType) {
	this.publicityType = publicityType;
    }

    public String getPublicityName() {
	return publicityName;
    }

    public void setPublicityName(String publicityName) {
	this.publicityName = publicityName;
    }

    public String getPublicityImg() {
	return publicityImg;
    }

    public void setPublicityImg(String publicityImg) {
	this.publicityImg = publicityImg;
    }

    public String getPublicityCategory() {
	return publicityCategory;
    }

    public void setPublicityCategory(String publicityCategory) {
	this.publicityCategory = publicityCategory;
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

	Publicity po = (Publicity) poObj;
	BeanUtils.copyProperties(po, this);
	this.publicityId = po.getId();
	this.publicityImg = QiniuImageUtil.newImageUrl(po.getPublicityImg());
	this.createTime = DateUtil.formatDateYMDHMS(po.getCreateTime());

    }

}
