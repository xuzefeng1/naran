package com.naran.web.vo.order;

import org.springframework.beans.BeanUtils;

import com.naran.core.entity.order.OrderApply;
import com.naran.foundation.util.BaseVO;
import com.naran.foundation.util.DateUtil;
import com.naran.foundation.util.file.qiniu.QiniuImageUtil;

public class OrderApplyVO implements BaseVO {

    private Long applyId;

    private Long orderId;

    private Long accountId;

    private String accountNike;

    private String accountImg;

    private String applyStatus;

    private String applyTitle;

    private String applyContent;

    private String applyImgs;

    private Boolean willing;

    private String estimateWeight;

    private String applyPrice;

    private String city;

    private String province;

    private String county;

    private String applyAddress;

    private String backdropImg;

    private String backdropBottomImg;// 背景底部图片

    private String mailAddress;

    private String mailName;

    private String mailPhone;

    private String createTime;

    private String updateTime;

    public Long getApplyId() {
	return applyId;
    }

    public void setApplyId(Long applyId) {
	this.applyId = applyId;
    }

    public Long getOrderId() {
	return orderId;
    }

    public void setOrderId(Long orderId) {
	this.orderId = orderId;
    }

    public Long getAccountId() {
	return accountId;
    }

    public void setAccountId(Long accountId) {
	this.accountId = accountId;
    }

    public String getAccountNike() {
	return accountNike;
    }

    public void setAccountNike(String accountNike) {
	this.accountNike = accountNike;
    }

    public String getAccountImg() {
	return accountImg;
    }

    public void setAccountImg(String accountImg) {
	this.accountImg = accountImg;
    }

    public String getApplyStatus() {
	return applyStatus;
    }

    public void setApplyStatus(String applyStatus) {
	this.applyStatus = applyStatus;
    }

    public String getApplyTitle() {
	return applyTitle;
    }

    public void setApplyTitle(String applyTitle) {
	this.applyTitle = applyTitle;
    }

    public String getApplyContent() {
	return applyContent;
    }

    public void setApplyContent(String applyContent) {
	this.applyContent = applyContent;
    }

    public String getApplyImgs() {
	return applyImgs;
    }

    public void setApplyImgs(String applyImgs) {
	this.applyImgs = applyImgs;
    }

    public Boolean getWilling() {
	return willing;
    }

    public void setWilling(Boolean willing) {
	this.willing = willing;
    }

    public String getEstimateWeight() {
	return estimateWeight;
    }

    public void setEstimateWeight(String estimateWeight) {
	this.estimateWeight = estimateWeight;
    }

    public String getApplyPrice() {
	return applyPrice;
    }

    public void setApplyPrice(String applyPrice) {
	this.applyPrice = applyPrice;
    }

    public String getCity() {
	return city;
    }

    public void setCity(String city) {
	this.city = city;
    }

    public String getProvince() {
	return province;
    }

    public void setProvince(String province) {
	this.province = province;
    }

    public String getCounty() {
	return county;
    }

    public void setCounty(String county) {
	this.county = county;
    }

    public String getApplyAddress() {
	return applyAddress;
    }

    public void setApplyAddress(String applyAddress) {
	this.applyAddress = applyAddress;
    }

    public String getBackdropImg() {
	return backdropImg;
    }

    public void setBackdropImg(String backdropImg) {
	this.backdropImg = backdropImg;
    }

    public String getBackdropBottomImg() {
	return backdropBottomImg;
    }

    public void setBackdropBottomImg(String backdropBottomImg) {
	this.backdropBottomImg = backdropBottomImg;
    }

    public String getMailAddress() {
	return mailAddress;
    }

    public void setMailAddress(String mailAddress) {
	this.mailAddress = mailAddress;
    }

    public String getMailName() {
	return mailName;
    }

    public void setMailName(String mailName) {
	this.mailName = mailName;
    }

    public String getMailPhone() {
	return mailPhone;
    }

    public void setMailPhone(String mailPhone) {
	this.mailPhone = mailPhone;
    }

    public String getCreateTime() {
	return createTime;
    }

    public void setCreateTime(String createTime) {
	this.createTime = createTime;
    }

    public String getUpdateTime() {
	return updateTime;
    }

    public void setUpdateTime(String updateTime) {
	this.updateTime = updateTime;
    }

    @Override
    public void convertPOToVO(Object poObj) {
	if (null == poObj) {
	    return;
	}
	OrderApply po = (OrderApply) poObj;
	BeanUtils.copyProperties(po, this);
	this.applyId = po.getId();
	this.backdropImg = QiniuImageUtil.newImageUrl(po.getBackdropImg());
	this.applyImgs = QiniuImageUtil.newImageUrls(po.getApplyImgs());
	this.backdropBottomImg = QiniuImageUtil.newImageUrls(po.getBackdropBottomImg());
	this.createTime = DateUtil.formatDateYMDHMS(po.getCreateTime());
	this.updateTime = DateUtil.formatDateYMDHMS(po.getUpdateTime());
    }

}
