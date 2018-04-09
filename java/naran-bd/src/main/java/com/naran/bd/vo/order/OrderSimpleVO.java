package com.naran.bd.vo.order;

import java.util.Arrays;
import java.util.List;

import org.springframework.beans.BeanUtils;

import com.naran.core.entity.order.Order;
import com.naran.core.enums.OrderType;
import com.naran.foundation.util.BaseVO;
import com.naran.foundation.util.DateUtil;
import com.naran.foundation.util.StringUtil;
import com.naran.foundation.util.file.qiniu.QiniuImageUtil;

/**
 * 轮播图展示封装
 * 
 * @author zefeng.xu
 */
public class OrderSimpleVO implements BaseVO {

    private Long orderId;

    private Long initiatorId;

    private String initiatorNike;

    private String initiatorImg;

    private String orderType;

    private String typeName;

    private String orderImgs;

    private String orderTitle;

    private String orderContent;

    private Integer collectionTimes;

    private Integer commentTimes;

    private Integer shareTimes;

    private String backdropBottomImg;// 背景底部图片

    private String city;

    private String province;

    private String county;

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

    public String getInitiatorImg() {
	return initiatorImg;
    }

    public void setInitiatorImg(String initiatorImg) {
	this.initiatorImg = initiatorImg;
    }

    public String getOrderType() {
	return orderType;
    }

    public void setOrderType(String orderType) {
	this.orderType = orderType;
    }

    public String getTypeName() {
	return typeName;
    }

    public void setTypeName(String typeName) {
	this.typeName = typeName;
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

    public String getBackdropBottomImg() {
	return backdropBottomImg;
    }

    public void setBackdropBottomImg(String backdropBottomImg) {
	this.backdropBottomImg = backdropBottomImg;
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

    public Integer getShareTimes() {
	return shareTimes;
    }

    public void setShareTimes(Integer shareTimes) {
	this.shareTimes = shareTimes;
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
	if (this.orderType != null) {
	    this.typeName = OrderType.valueOf(this.orderType).getChinese();
	}
	this.initiatorImg = QiniuImageUtil.newImageUrl60("home1.png");
	this.orderImgs = QiniuImageUtil.newImageUrls(po.getOrderImgs());
	this.backdropBottomImg = QiniuImageUtil.newImageUrls(po.getBackdropBottomImg());
	this.createTime = DateUtil.formatDateYMDHMS(po.getCreateTime());
	this.updateTime = DateUtil.formatDateYMDHMS(po.getUpdateTime());
	if (StringUtil.isNotBlank(po.getQuestionnaireOptions())) {
	    this.questionnaireOptions = Arrays.asList(po.getQuestionnaireOptions().split(StringUtil.DEFAULT_COMBINER));
	}
    }
}
