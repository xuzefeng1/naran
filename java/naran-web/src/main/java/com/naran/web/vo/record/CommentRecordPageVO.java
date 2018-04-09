package com.naran.web.vo.record;

import java.util.List;

import com.naran.foundation.util.BasePageVO;

/**
 * 展示封装（分页）
 * 
 * @author zefeng.xu
 */
public class CommentRecordPageVO extends BasePageVO {

    private List<CommentRecordVO> comments;

    public List<CommentRecordVO> getComments() {
	return comments;
    }

    public void setComments(List<CommentRecordVO> comments) {
	this.comments = comments;
    }

}
