package com.naran.core.dao.record.impl;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.naran.core.dao.record.CommentRecordDao;
import com.naran.core.entity.record.CommentRecord;
import com.naran.foundation.mybatis.dao.MyBatisDAO;
import com.naran.foundation.mybatis.page.Page;
import com.naran.foundation.mybatis.page.PageRequest;

/**
 * 
 * @author zefeng.xu
 */
@Repository
public class CommentRecordDaoImpl implements CommentRecordDao {

    private static final String ADD_COMMENT_RECORD = "addCommentRecord";
    private static final String UPDATE_COMMENT_RECORD = "updateCommentRecord";
    private static final String FIND_COMMENT_RECORD_BY_ID = "findCommentRecordById";
    private static final String FIND_COMMENT_RECORD_BY_ACCOUNT = "findCommentRecordByAccount";
    private static final String FIND_COMMENT_RECORD_BY_PAGE = "findCommentRecordByPage";

    @Autowired
    private MyBatisDAO myBatisDAO;

    @Override
    public Long addCommentRecord(CommentRecord commentRecord) {
	commentRecord.setCreateTime(new Date());
	commentRecord.setUpdateTime(new Date());
	commentRecord.setExpired(Boolean.TRUE);
	myBatisDAO.insert(ADD_COMMENT_RECORD, commentRecord);
	return commentRecord.getId();
    }

    @Override
    public void updateCommentRecord(CommentRecord commentRecord) {
	commentRecord.setUpdateTime(new Date());
	myBatisDAO.update(UPDATE_COMMENT_RECORD, commentRecord);
    }

    @Override
    public void deleteCommentRecord(Long id) {
	CommentRecord commentRecord = new CommentRecord();
	commentRecord.setId(id);
	commentRecord.setExpired(Boolean.FALSE);
	myBatisDAO.update(UPDATE_COMMENT_RECORD, commentRecord);
    }

    @Override
    public CommentRecord findCommentRecordById(Long id) {
	if (id == null) {
	    return null;
	}
	return (CommentRecord) myBatisDAO.findForObject(FIND_COMMENT_RECORD_BY_ID, id);
    }

    @SuppressWarnings("unchecked")
    @Override
    public Page<CommentRecord> findCommentRecordByPage(Long businessId, String businessType, Long commentedTopId, int pageNum, int pageSize) {

	Map<String, Object> param = new HashMap<String, Object>();
	param.put("businessId", businessId);
	param.put("businessType", businessType);
	param.put("commentedTopId", commentedTopId);

	return myBatisDAO.findForPage(FIND_COMMENT_RECORD_BY_PAGE, new PageRequest(pageNum, pageSize, param));
    }

    @Override
    public CommentRecord findCommentRecordByAccount(Long businessId, String businessType, Long accountId) {
	if (businessId == null || accountId == null) {
	    return null;
	}
	Map<String, Object> param = new HashMap<String, Object>();
	param.put("businessId", businessId);
	param.put("businessType", businessType);
	param.put("initiatorId", accountId);
	return (CommentRecord) myBatisDAO.findForObject(FIND_COMMENT_RECORD_BY_ACCOUNT, param);
    }

}
