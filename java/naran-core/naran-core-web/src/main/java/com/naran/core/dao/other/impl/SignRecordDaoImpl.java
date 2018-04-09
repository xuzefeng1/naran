package com.naran.core.dao.other.impl;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.naran.core.dao.other.SignRecordDao;
import com.naran.core.entity.record.SignRecord;
import com.naran.foundation.mybatis.dao.MyBatisDAO;
import com.naran.foundation.mybatis.page.Page;
import com.naran.foundation.mybatis.page.PageRequest;

/**
 * 
 * @author zefeng.xu
 */
@Repository
public class SignRecordDaoImpl implements SignRecordDao {

    private static final String ADD_SIGN_RECORD = "addSignRecord";
    private static final String UPDATE_SIGN_RECORD = "updateSignRecord";
    private static final String FIND_SIGN_RECORD_BY_ID = "findSignRecordById";
    private static final String FIND_SIGN_RECORD_BY_LAST = "findSignRecordByLast";
    private static final String FIND_SIGN_RECORD_BY_PAGE = "findSignRecordByPage";

    @Autowired
    private MyBatisDAO myBatisDAO;

    @Override
    public Long addSignRecord(SignRecord signRecord) {
	signRecord.setCreateTime(new Date());
	signRecord.setUpdateTime(new Date());
	signRecord.setExpired(Boolean.TRUE);
	myBatisDAO.insert(ADD_SIGN_RECORD, signRecord);
	return signRecord.getId();
    }

    @Override
    public void updateSignRecord(SignRecord signRecord) {
	signRecord.setUpdateTime(new Date());
	myBatisDAO.update(UPDATE_SIGN_RECORD, signRecord);
    }

    @Override
    public void deleteSignRecord(Long id) {
	SignRecord signRecord = new SignRecord();
	signRecord.setId(id);
	signRecord.setExpired(Boolean.FALSE);
	myBatisDAO.update(UPDATE_SIGN_RECORD, signRecord);
    }

    @Override
    public SignRecord findSignRecordById(Long id) {
	if (id == null) {
	    return null;
	}
	return (SignRecord) myBatisDAO.findForObject(FIND_SIGN_RECORD_BY_ID, id);
    }

    @SuppressWarnings("unchecked")
    @Override
    public Page<SignRecord> findSignRecordByPage(Long accountId, String startDate, String endDate, int pageNum, int pageSize) {
	Map<String, Object> param = new HashMap<String, Object>();
	param.put("accountId", accountId);
	param.put("startDate", startDate);
	param.put("endDate", endDate);
	return myBatisDAO.findForPage(FIND_SIGN_RECORD_BY_PAGE, new PageRequest(pageNum, pageSize, param));
    }

    @Override
    public SignRecord findSignRecordByLast(Long accountId) {
	if (accountId == null) {
	    return null;
	}
	return (SignRecord) myBatisDAO.findForObject(FIND_SIGN_RECORD_BY_LAST, accountId);
    }

}
