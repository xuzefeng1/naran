package com.naran.web.vo.record;

import org.springframework.beans.BeanUtils;

import com.naran.core.entity.record.CommentRecord;
import com.naran.foundation.util.BaseVO;
import com.naran.foundation.util.DateUtil;

/**
 * 评论展示封装
 * 
 * @author zefeng.xu
 */
public class CommentRecordVO implements BaseVO {

    private Long commentId;

    private Long initiatorId;

    private String initiatorNike;

    private String initiatorImg;

    private Long recipientId;

    private String recipientNike;

    private String commentContent;

    private String createTime;

    private CommentRecordPageVO records;

    public Long getCommentId() {
	return commentId;
    }

    public void setCommentId(Long commentId) {
	this.commentId = commentId;
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

    public String getCommentContent() {
	return commentContent;
    }

    public void setCommentContent(String commentContent) {
	this.commentContent = commentContent;
    }

    public String getCreateTime() {
	return createTime;
    }

    public void setCreateTime(String createTime) {
	this.createTime = createTime;
    }

    public CommentRecordPageVO getRecords() {
	return records;
    }

    public void setRecords(CommentRecordPageVO records) {
	this.records = records;
    }

    @Override
    public void convertPOToVO(Object poObj) {
	if (null == poObj) {
	    return;
	}

	CommentRecord po = (CommentRecord) poObj;
	BeanUtils.copyProperties(po, this);
	this.commentId = po.getId();
	this.createTime = DateUtil.formatDateYMDHMS(po.getCreateTime());

    }
}
