package com.naran.core.entity.content;

import com.naran.foundation.entity.BaseEntityPO;

public class Content extends BaseEntityPO {

    /**
     * 
     */
    private static final long serialVersionUID = 1L;

    private String contentTitle;

    private String contentBody;

    private String contentImg;

    private Integer shareTimes;

    private Integer collectionTimes;

    private Integer commentTimes;

    private String contentType;

    private String recommendTime;

    public String getContentTitle() {
	return contentTitle;
    }

    public void setContentTitle(String contentTitle) {
	this.contentTitle = contentTitle;
    }

    public String getContentBody() {
	return contentBody;
    }

    public void setContentBody(String contentBody) {
	this.contentBody = contentBody;
    }

    public String getContentImg() {
	return contentImg;
    }

    public void setContentImg(String contentImg) {
	this.contentImg = contentImg;
    }

    public Integer getShareTimes() {
	return shareTimes;
    }

    public void setShareTimes(Integer shareTimes) {
	this.shareTimes = shareTimes;
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

    public String getContentType() {
	return contentType;
    }

    public void setContentType(String contentType) {
	this.contentType = contentType;
    }

    public String getRecommendTime() {
	return recommendTime;
    }

    public void setRecommendTime(String recommendTime) {
	this.recommendTime = recommendTime;
    }

}