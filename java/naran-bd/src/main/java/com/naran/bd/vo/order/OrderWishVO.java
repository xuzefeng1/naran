package com.naran.bd.vo.order;

import java.util.Arrays;
import java.util.List;

import org.springframework.beans.BeanUtils;

import com.naran.core.entity.order.Order;
import com.naran.foundation.util.BaseVO;
import com.naran.foundation.util.DateUtil;
import com.naran.foundation.util.StringUtil;
import com.naran.foundation.util.file.qiniu.QiniuImageUtil;

/**
 * 订单展示封装
 * 
 * @author zefeng.xu
 */
public class OrderWishVO implements BaseVO {

    private Long orderId;

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

    private String backdropBottomImg;// 背景底部图片

    private Integer readTimes;

    private String city;

    private String province;

    private String county;

    private String commodityType;

    private String createTime;

    private String updateTime;

    private List<String> questionnaireOptions;

    public Long getOrderId() {
	return orderId;
    }

    public void setOrderId(Long orderId) {
	this.orderId = orderId;
    }

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

    public List<String> getQuestionnaireOptions() {
	return questionnaireOptions;
    }

    public void setQuestionnaireOptions(List<String> questionnaireOptions) {
	this.questionnaireOptions = questionnaireOptions;
    }

    @Override
    public void convertPOToVO(Object poObj) {
	if (null == poObj) {
	    return;
	}
	Order po = (Order) poObj;
	BeanUtils.copyProperties(po, this);
	this.orderId = po.getId();
	this.backdropImg = QiniuImageUtil.newImageUrl(po.getBackdropImg());
	this.backdropBottomImg = QiniuImageUtil.newImageUrls(po.getBackdropBottomImg());
	this.orderImgs = QiniuImageUtil.newImageUrls(po.getOrderImgs());
	this.createTime = DateUtil.formatDateYMDHMS(po.getCreateTime());
	this.updateTime = DateUtil.formatDateYMDHMS(po.getUpdateTime());
	if (StringUtil.isNotBlank(po.getQuestionnaireOptions())) {
	    this.questionnaireOptions = Arrays.asList(po.getQuestionnaireOptions().split(StringUtil.DEFAULT_COMBINER));
	}
    }

}
