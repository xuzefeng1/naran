package com.naran.core.dao.record.impl;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.naran.core.dao.record.CollectionRecordDao;
import com.naran.core.entity.record.CollectionRecord;
import com.naran.foundation.mybatis.dao.MyBatisDAO;
import com.naran.foundation.mybatis.page.Page;
import com.naran.foundation.mybatis.page.PageRequest;

/**
 * 
 * @author zefeng.xu
 */
@Repository
public class CollectionRecordDaoImpl implements CollectionRecordDao {

    private static final String ADD_COLLECTION_RECORD = "addCollectionRecord";
    private static final String UPDATE_COLLECTION_RECORD = "updateCollectionRecord";
    private static final String FIND_COLLECTION_RECORD_BY_ID = "findCollectionRecordById";
    private static final String FIND_COLLECTION_RECORD_BY_ACCOUNT = "findCollectionRecordByAccount";
    private static final String FIND_COLLECTION_RECORD_BY_PAGE = "findCollectionRecordByPage";

    @Autowired
    private MyBatisDAO myBatisDAO;

    @Override
    public Long addCollectionRecord(CollectionRecord collectionRecord) {
	collectionRecord.setCreateTime(new Date());
	collectionRecord.setUpdateTime(new Date());
	collectionRecord.setExpired(Boolean.TRUE);
	myBatisDAO.insert(ADD_COLLECTION_RECORD, collectionRecord);
	return collectionRecord.getId();
    }

    @Override
    public void updateCollectionRecord(CollectionRecord collectionRecord) {
	collectionRecord.setUpdateTime(new Date());
	myBatisDAO.update(UPDATE_COLLECTION_RECORD, collectionRecord);
    }

    @Override
    public void deleteCollectionRecord(Long id) {
	CollectionRecord collectionRecord = new CollectionRecord();
	collectionRecord.setId(id);
	collectionRecord.setExpired(Boolean.FALSE);
	myBatisDAO.update(UPDATE_COLLECTION_RECORD, collectionRecord);
    }

    @Override
    public CollectionRecord findCollectionRecordById(Long id) {
	if (id == null) {
	    return null;
	}
	return (CollectionRecord) myBatisDAO.findForObject(FIND_COLLECTION_RECORD_BY_ID, id);
    }

    @SuppressWarnings("unchecked")
    @Override
    public Page<CollectionRecord> findCollectionRecordByPage(String businessType,String secondType, Long accountId, int pageNum, int pageSize) {
	Map<String, Object> param = new HashMap<String, Object>();
	param.put("businessType", businessType);
	param.put("secondType", secondType);
	param.put("accountId", accountId);
	return myBatisDAO.findForPage(FIND_COLLECTION_RECORD_BY_PAGE, new PageRequest(pageNum, pageSize, param));
    }

    @Override
    public void deleteCollectionRecordByAccount(Long businessId, String businessType, Long accountId) {
	CollectionRecord record = findCollectionRecordByAccount(businessId, businessType, accountId);
	if (record != null) {
	    deleteCollectionRecord(record.getId());
	}
    }

    @Override
    public CollectionRecord findCollectionRecordByAccount(Long businessId, String businessType, Long accountId) {
	Map<String, Object> param = new HashMap<String, Object>();
	param.put("businessId", businessId);
	param.put("businessType", businessType);
	param.put("accountId", accountId);
	return (CollectionRecord) myBatisDAO.findForObject(FIND_COLLECTION_RECORD_BY_ACCOUNT, param);
    }

}
