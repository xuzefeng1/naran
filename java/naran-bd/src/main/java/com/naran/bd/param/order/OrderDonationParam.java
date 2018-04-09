package com.naran.bd.param.order;

import com.naran.bd.param.BaseParam;

/**
 * 捐赠入参
 * 
 * @author zefeng.xu
 * */
public class OrderDonationParam extends BaseParam {

    private String commodityType;// 商品类别 

    private String orderTitle;// 标题

    private String orderContent;// 介绍

    private String orderImgs;// 配图以,号隔开

    private String questionnaireOptionIds;// 问卷结果ID以,号隔开

    private Boolean willing;// 运费承担

    private String agingDegree;// 新旧程度

    private String estimateWeight;// 预估重量

    private String orderPrice;// 价格 （参照全局变量属性orderPrice）

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

    public String getQuestionnaireOptionIds() {
	return questionnaireOptionIds;
    }

    public void setQuestionnaireOptionIds(String questionnaireOptionIds) {
	this.questionnaireOptionIds = questionnaireOptionIds;
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

    public String getAgingDegree() {
	return agingDegree;
    }

    public void setAgingDegree(String agingDegree) {
	this.agingDegree = agingDegree;
    }

    public String getEstimateWeight() {
	return estimateWeight;
    }

    public void setEstimateWeight(String estimateWeight) {
	this.estimateWeight = estimateWeight;
    }

    public String getOrderPrice() {
	return orderPrice;
    }

    public void setOrderPrice(String orderPrice) {
	this.orderPrice = orderPrice;
    }

}
