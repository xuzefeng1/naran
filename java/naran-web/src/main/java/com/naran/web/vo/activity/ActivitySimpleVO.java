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
public class ActivitySimpleVO implements BaseVO {

    private Long activityId;

    private String activityImg;

    private String activityTitle;

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

    public String getActivityImg() {
	return activityImg;
    }

    public void setActivityImg(String activityImg) {
	this.activityImg = activityImg;
    }

    public String getActivityTitle() {
	return activityTitle;
    }

    public void setActivityTitle(String activityTitle) {
	this.activityTitle = activityTitle;
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
	this.createTime = DateUtil.formatDateYMDHMS(po.getCreateTime());
	this.realApplyTimes = new Random().nextInt(po.getApplyTimes()) + 1;
	

    }
}
