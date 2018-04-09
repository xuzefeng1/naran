package com.naran.bd.param.order;

import com.naran.bd.param.BaseParam;

/**
 * 心愿入参
 * 
 * @author zefeng.xu
 * */
public class OrderWishParam extends BaseParam {

    private String commodityType;// 商品类别

    private String orderTitle;// 标题

    private String orderContent;// 介绍

    private String orderImgs;// 配图以,号隔开

    private String backdropImg;// 背景图片

    private String backdropBottomImg;// 背景底部图片

    private Boolean willing;// 运费承担

    private Boolean anonymous;// 匿名

    private String mailAddress;// 收货地址

    private String mailName;// 收货人

    private String mailPhone;// 收货电话

    private String city;

    private String province;

    private String county;

    public String getCommodityType() {
	return commodityType;
    }

    public void setCommodityType(String commodityType) {
	this.commodityType = commodityType;
    }

    public String getOrderTitle() {
	return orderTitle;
    }

    public void setOrderTitle(String orderTitle) {
	this.orderTitle = orderTitle;
    }

    public String getOrderContent() {
	return orderContent;
    }

    public void setOrderContent(String orderContent) {
	this.orderContent = orderContent;
    }

    public String getOrderImgs() {
	return orderImgs;
    }

    public void setOrderImgs(String orderImgs) {
	this.orderImgs = orderImgs;
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

    public Boolean getAnonymous() {
	return anonymous;
    }

    public void setAnonymous(Boolean anonymous) {
	this.anonymous = anonymous;
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
