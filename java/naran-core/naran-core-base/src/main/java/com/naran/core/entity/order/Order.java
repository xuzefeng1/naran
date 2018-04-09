package com.naran.core.entity.order;

import com.naran.foundation.entity.BaseEntityPO;

public class Order extends BaseEntityPO {

    /**
     * 
     */
    private static final long serialVersionUID = 1L;

    private Long initiatorId;

    private String initiatorNike;

    private String orderType;

    private Long recipientId;

    private String recipientNike;

    private String orderImgs;

    private String orderTitle;

    private String orderContent;

    private String mailAddress;

    private String mailName;

    private String orderStatus;

    private String mailPhone;

    private Integer collectionTimes;

    private Integer commentTimes;

    private Boolean willing;

    private Boolean anonymous;

    private Integer shareTimes;

    private String backdropImg;

    private String backdropBottomImg;

    private Integer readTimes;

    private String city;

    private String province;

    private String county;

    private String commodityType;

    private String questionnaireOptions;

    private String agingDegree;

    private String estimateWeight;

    private String orderPrice;

    private String mailCode;

    private String mailStatus;

    public Long getInitiatorId() {
	return initiatorId;
    }

    public void setInitiatorId(Long initiatorId) {
	this.initiatorId = initiatorId;
    }

    public String getInitiatorNike() {
	return initiatorNike;
    }

    public void setInitiatorNike(String initiatorNike) {
	this.initiatorNike = initiatorNike;
    }

    public String getOrderType() {
	return orderType;
    }

    public void setOrderType(String orderType) {
	this.orderType = orderType;
    }

    public Long getRecipientId() {
	return recipientId;
    }

    public void setRecipientId(Long recipientId) {
	this.recipientId = recipientId;
    }

    public String getRecipientNike() {
	return recipientNike;
    }

    public void setRecipientNike(String recipientNike) {
	this.recipientNike = recipientNike;
    }

    public String getOrderImgs() {
	return orderImgs;
    }

    public void setOrderImgs(String orderImgs) {
	this.orderImgs = orderImgs;
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

    public String getOrderStatus() {
	return orderStatus;
    }

    public void setOrderStatus(String orderStatus) {
	this.orderStatus = orderStatus;
    }

    public String getMailPhone() {
	return mailPhone;
    }

    public void setMailPhone(String mailPhone) {
	this.mailPhone = mailPhone;
    }

    public Integer getCollectionTimes() {
	return collectionTimes;
    }

    public void setCollectionTimes(Integer collectionTimes) {
	this.collectionTimes = collectionTimes;
    }

    public Integer getCommentTimes() {
	return commentTimes;
    }

    public void setCommentTimes(Integer commentTimes) {
	this.commentTimes = commentTimes;
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

    public Integer getShareTimes() {
	return shareTimes;
    }

    public void setShareTimes(Integer shareTimes) {
	this.shareTimes = shareTimes;
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

    public Integer getReadTimes() {
	return readTimes;
    }

    public void setReadTimes(Integer readTimes) {
	this.readTimes = readTimes;
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

    public String getCommodityType() {
	return commodityType;
    }

    public void setCommodityType(String commodityType) {
	this.commodityType = commodityType;
    }

    public String getQuestionnaireOptions() {
	return questionnaireOptions;
    }

    public void setQuestionnaireOptions(String questionnaireOptions) {
	this.questionnaireOptions = questionnaireOptions;
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

    public String getMailCode() {
	return mailCode;
    }

    public void setMailCode(String mailCode) {
	this.mailCode = mailCode;
    }

    public String getMailStatus() {
	return mailStatus;
    }

    public void setMailStatus(String mailStatus) {
	this.mailStatus = mailStatus;
    }

}