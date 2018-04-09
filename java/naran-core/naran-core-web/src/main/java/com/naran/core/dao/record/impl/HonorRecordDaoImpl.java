package com.naran.core.dao.record.impl;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.naran.core.dao.record.HonorRecordDao;
import com.naran.core.entity.record.HonorRecord;
import com.naran.foundation.mybatis.dao.MyBatisDAO;
import com.naran.foundation.mybatis.page.Page;
import com.naran.foundation.mybatis.page.PageRequest;

/**
 * 
 * @author zefeng.xu
 */
@Repository
public class HonorRecordDaoImpl implements HonorRecordDao {

    private static final String ADD_HONOR_RECORD = "addHonorRecord";
    private static final String UPDATE_HONOR_RECORD = "updateHonorRecord";
    private static final String FIND_HONOR_RECORD_BY_ID = "findHonorRecordById";
    private static final String FIND_HONOR_RECORD_BY_PAGE = "findHonorRecordByPage";

    @Autowired
    private MyBatisDAO myBatisDAO;

    @Override
    public Long addHonorRecord(HonorRecord honorRecord) {
	honorRecord.setCreateTime(new Date());
	honorRecord.setUpdateTime(new Date());
	honorRecord.setExpired(Boolean.TRUE);
	myBatisDAO.insert(ADD_HONOR_RECORD, honorRecord);
	return honorRecord.getId();
    }

    @Override
    public void updateHonorRecord(HonorRecord honorRecord) {
	honorRecord.setUpdateTime(new Date());
	myBatisDAO.update(UPDATE_HONOR_RECORD, honorRecord);
    }

    @Override
    public void deleteHonorRecord(Long id) {
	HonorRecord honorRecord = new HonorRecord();
	honorRecord.setId(id);
	honorRecord.setExpired(Boolean.FALSE);
	myBatisDAO.update(UPDATE_HONOR_RECORD, honorRecord);
    }

    @Override
    public HonorRecord findHonorRecordById(Long id) {
	if (id == null) {
	    return null;
	}
	return (HonorRecord) myBatisDAO.findForObject(FIND_HONOR_RECORD_BY_ID, id);
    }

    @SuppressWarnings("unchecked")
    @Override
    public Page<HonorRecord> findHonorRecordByPage(int pageNum, int pageSize) {
	Map<String, Object> param = new HashMap<String, Object>();
	return myBatisDAO.findForPage(FIND_HONOR_RECORD_BY_PAGE, new PageRequest(pageNum, pageSize, param));
    }

}
