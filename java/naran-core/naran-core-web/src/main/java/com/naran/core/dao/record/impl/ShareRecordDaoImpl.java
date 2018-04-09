package com.naran.core.dao.record.impl;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.naran.core.dao.record.ShareRecordDao;
import com.naran.core.entity.record.ShareRecord;
import com.naran.foundation.mybatis.dao.MyBatisDAO;
import com.naran.foundation.mybatis.page.Page;
import com.naran.foundation.mybatis.page.PageRequest;

/**
 * 
 * @author zefeng.xu
 */
@Repository
public class ShareRecordDaoImpl implements ShareRecordDao {

    private static final String ADD_SHARE_RECORD = "addShareRecord";
    private static final String UPDATE_SHARE_RECORD = "updateShareRecord";
    private static final String FIND_SHARE_RECORD_BY_ID = "findShareRecordById";
    private static final String FIND_SHARE_RECORD_BY_RECORD = "findShareRecordByRecord";
    private static final String FIND_SHARE_RECORD_BY_PAGE = "findShareRecordByPage";

    @Autowired
    private MyBatisDAO myBatisDAO;

    @Override
    public Long addShareRecord(ShareRecord shareRecord) {
	shareRecord.setCreateTime(new Date());
	shareRecord.setUpdateTime(new Date());
	shareRecord.setExpired(Boolean.TRUE);
	myBatisDAO.insert(ADD_SHARE_RECORD, shareRecord);
	return shareRecord.getId();
    }

    @Override
    public void updateShareRecord(ShareRecord shareRecord) {
	shareRecord.setUpdateTime(new Date());
	myBatisDAO.update(UPDATE_SHARE_RECORD, shareRecord);
    }

    @Override
    public void deleteShareRecord(Long id) {
	ShareRecord shareRecord = new ShareRecord();
	shareRecord.setId(id);
	shareRecord.setExpired(Boolean.FALSE);
	myBatisDAO.update(UPDATE_SHARE_RECORD, shareRecord);
    }

    @Override
    public ShareRecord findShareRecordById(Long id) {
	if (id == null) {
	    return null;
	}
	return (ShareRecord) myBatisDAO.findForObject(FIND_SHARE_RECORD_BY_ID, id);
    }

    @Override
    public ShareRecord findShareRecordByRecord(Long accountId, Long businessId, String businessType) {
	if (accountId == null || businessId == null || businessType == null) {
	    return null;
	}
	Map<String, Object> param = new HashMap<String, Object>();
	param.put("accountId", accountId);
	param.put("businessId", businessId);
	param.put("businessType", businessType);

	return (ShareRecord) myBatisDAO.findForObject(FIND_SHARE_RECORD_BY_RECORD, param);
    }

    @SuppressWarnings("unchecked")
    @Override
    public Page<ShareRecord> findShareRecordByPage(int pageNum, int pageSize) {
	Map<String, Object> param = new HashMap<String, Object>();
	return myBatisDAO.findForPage(FIND_SHARE_RECORD_BY_PAGE, new PageRequest(pageNum, pageSize, param));
    }

}
