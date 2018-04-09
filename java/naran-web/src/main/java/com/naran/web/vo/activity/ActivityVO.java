package com.naran.web.vo.activity;

import java.util.Random;

import org.springframework.beans.BeanUtils;

import com.naran.core.entity.activity.Activity;
import com.naran.foundation.util.BaseVO;
import com.naran.foundation.util.DateUtil;
import com.naran.foundation.util.file.qiniu.QiniuImageUtil;

/**
 * 展示封装
 * 
 * @author zefeng.xu
 */
public class ActivityVO implements BaseVO {

    private Long activityId;

    private String activityType;

    private String activityTitle;

    private String activityContent;

    private String activityDay;

    private String activityAddress;

    private String activityImg;

    private String activityStatus;

    private String applyTime;

    private Integer applyTimes;

    private Integer realApplyTimes;

    private Integer commentTimes;

    private Integer shareTimes;

    private String createTime;

    public Long getActivityId() {
	return activityId;
    }

    public void setActivityId(Long activityId) {
	this.activityId = activityId;
    }

    public String getActivityType() {
	return activityType;
    }

    public void setActivityType(String activityType) {
	this.activityType = activityType;
    }

    public String getActivityTitle() {
	return activityTitle;
    }

    public void setActivityTitle(String activityTitle) {
	this.activityTitle = activityTitle;
    }

    public String getActivityContent() {
	return activityContent;
    }

    public void setActivityContent(String activityContent) {
	this.activityContent = activityContent;
    }

    public String getActivityDay() {
	return activityDay;
    }

    public void setActivityDay(String activityDay) {
	this.activityDay = activityDay;
    }

    public String getActivityAddress() {
	return activityAddress;
    }

    public void setActivityAddress(String activityAddress) {
	this.activityAddress = activityAddress;
    }

    public String getActivityImg() {
	return activityImg;
    }

    public void setActivityImg(String activityImg) {
	this.activityImg = activityImg;
    }

    public String getActivityStatus() {
	return activityStatus;
    }

    public void setActivityStatus(String activityStatus) {
	this.activityStatus = activityStatus;
    }

    public String getApplyTime() {
	return applyTime;
    }

    public void setApplyTime(String applyTime) {
	this.applyTime = applyTime;
    }

    public Integer getApplyTimes() {
	return applyTimes;
    }

    public void setApplyTimes(Integer applyTimes) {
	this.applyTimes = applyTimes;
    }

    public Integer getRealApplyTimes() {
	return realApplyTimes;
    }

    public void setRealApplyTimes(Integer realApplyTimes) {
	this.realApplyTimes = realApplyTimes;
    }

    public Integer getCommentTimes() {
	return commentTimes;
    }

    public void setCommentTimes(Integer commentTimes) {
	this.commentTimes = commentTimes;
    }

    public Integer getShareTimes() {
	return shareTimes;
    }

    public void setShareTimes(Integer shareTimes) {
	this.shareTimes = shareTimes;
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

	Activity po = (Activity) poObj;
	BeanUtils.copyProperties(po, this);
	this.activityId = po.getId();
	this.activityImg = QiniuImageUtil.newImageUrl(po.getActivityImg());
	this.applyTime = DateUtil.formatDateYMDHMS(po.getApplyTime());
	this.createTime = DateUtil.formatDateYMDHMS(po.getCreateTime());
	this.realApplyTimes = new Random().nextInt(po.getApplyTimes()) + 1;

    }

}
