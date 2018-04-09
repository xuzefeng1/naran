package com.naran.core.entity.activity;

import java.util.Date;

import com.naran.foundation.entity.BaseEntityPO;

public class Activity extends BaseEntityPO {

    /**
     * 
     */
    private static final long serialVersionUID = 1L;

    private String activityType;

    private String activityTitle;

    private String activityContent;

    private String activityDay;

    private String activityAddress;

    private String activityImg;

    private String activityStatus;

    private Date applyTime;

    private Integer applyTimes;

    private Integer commentTimes;

    private Integer shareTimes;

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

    public Date getApplyTime() {
	return applyTime;
    }

    public void setApplyTime(Date applyTime) {
	this.applyTime = applyTime;
    }

    public Integer getApplyTimes() {
	return applyTimes;
    }

    public void setApplyTimes(Integer applyTimes) {
	this.applyTimes = applyTimes;
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

}