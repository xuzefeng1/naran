package com.naran.bd.param.order;

import com.naran.bd.param.BaseParam;

public class ApplyDonationParam extends BaseParam {

    private Long orderId;// 订单ID

    private String applyTitle;// 标题

    private String applyContent;// 介绍

    private String applyImgs;// 配图以,号隔开

    private String backdropImg;// 背景图片

    private String backdropBottomImg;// 背景底部图片

    private Boolean willing;// 运费承担

    private String city;

    private String province;

    private String county;

    private String mailAddress;// 收货地址

    private String mailName;// 收货人

    private String mailPhone;// 收货电话

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

    public String getApplyImgs() {
	return applyImgs;
    }

    public void setApplyImgs(String applyImgs) {
	this.applyImgs = applyImgs;
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

    public Boolean getWilling() {
	return willing;
    }

    public void setWilling(Boolean willing) {
	this.willing = willing;
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

}
