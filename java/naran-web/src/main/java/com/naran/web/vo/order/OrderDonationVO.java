package com.naran.web.vo.order;

import java.util.Arrays;
import java.util.List;

import org.springframework.beans.BeanUtils;

import com.naran.core.entity.order.Order;
import com.naran.foundation.util.BaseVO;
import com.naran.foundation.util.DateUtil;
import com.naran.foundation.util.StringUtil;
import com.naran.foundation.util.file.qiniu.QiniuImageUtil;

/**
 * 捐赠详情展示封装
 * 
 * @author zefeng.xu
 */
public class OrderDonationVO implements BaseVO {

    private Long orderId;

    private Long initiatorId;

    private String initiatorNike;

    private String orderType;

    private String orderImgs;

    private String orderTitle;

    private String orderContent;

    private String mailAddress;

    private String mailName;

    private String mailStatus;

    private String mailCode;

    private String orderStatus;

    private String mailPhone;

    private Integer collectionTimes;

    private Integer commentTimes;

    private Boolean willing;

    private String estimateWeight;

    private Integer shareTimes;

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

    public String getMailCode() {
	return mailCode;
    }

    public void setMailCode(String mailCode) {
	this.mailCode = mailCode;
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

    public String getEstimateWeight() {
	return estimateWeight;
    }

    public void setEstimateWeight(String estimateWeight) {
	this.estimateWeight = estimateWeight;
    }

    public Integer getShareTimes() {
	return shareTimes;
    }

    public void setShareTimes(Integer shareTimes) {
	this.shareTimes = shareTimes;
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

    public String getMailStatus() {
	return mailStatus;
    }

    public void setMailStatus(String mailStatus) {
	this.mailStatus = mailStatus;
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
	this.orderImgs = QiniuImageUtil.newImageUrls(po.getOrderImgs());
	this.createTime = DateUtil.formatDateYMDHMS(po.getCreateTime());
	this.updateTime = DateUtil.formatDateYMDHMS(po.getUpdateTime());
	if (StringUtil.isNotBlank(po.getQuestionnaireOptions())) {
	    this.questionnaireOptions = Arrays.asList(po.getQuestionnaireOptions().split(StringUtil.DEFAULT_COMBINER));
	}
    }

}
