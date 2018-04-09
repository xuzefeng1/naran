package com.naran.web.vo.content;

import org.springframework.beans.BeanUtils;

import com.naran.core.entity.content.Content;
import com.naran.foundation.util.BaseVO;
import com.naran.foundation.util.DateUtil;
import com.naran.foundation.util.file.qiniu.QiniuImageUtil;

/**
 * 文章展示封装
 * 
 * @author zefeng.xu
 */
public class ContentVO implements BaseVO {

    private Long contentId;

    private String contentTitle;

    private String contentBody;

    private String contentImg;

    private Integer shareTimes;

    private Integer collectionTimes;

    private Integer commentTimes;

    private String contentType;

    private String recommendTime;

    private String createTime;

    public Long getContentId() {
	return contentId;
    }

    public void setContentId(Long contentId) {
	this.contentId = contentId;
    }

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
	Content po = (Content) poObj;
	BeanUtils.copyProperties(po, this);
	this.contentId = po.getId();
	this.contentImg = QiniuImageUtil.newImageUrls(po.getContentImg());
	this.createTime = DateUtil.formatDateYMDHMS(po.getCreateTime());
    }

}
