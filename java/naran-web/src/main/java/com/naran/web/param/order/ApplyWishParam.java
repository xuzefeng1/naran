package com.naran.web.param.order;

import com.naran.web.param.BaseParam;

public class ApplyWishParam extends BaseParam {

    private Long orderId;

    private String applyTitle;// 标题

    private String applyContent;// 介绍

    private String applyAddress;// 地址

    private String city;

    private String province;

    private String county;

    private String estimateWeight;// 预估重量

    private String applyPrice;// 预估价格

    private Boolean willing;// 运费承担
    
    private String applyImgs;

    public Long getOrderId() {
	return orderId;
    }

    public void setOrderId(Long orderId) {
	this.orderId = orderId;
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

    public String getApplyAddress() {
	return applyAddress;
    }

    public void setApplyAddress(String applyAddress) {
	this.applyAddress = applyAddress;
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

    public Boolean getWilling() {
	return willing;
    }

    public void setWilling(Boolean willing) {
	this.willing = willing;
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

    public String getApplyImgs() {
        return applyImgs;
    }

    public void setApplyImgs(String applyImgs) {
        this.applyImgs = applyImgs;
    }
    

}
