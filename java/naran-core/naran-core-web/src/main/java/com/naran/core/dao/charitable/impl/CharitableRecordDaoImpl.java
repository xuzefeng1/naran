package com.naran.core.dao.charitable.impl;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.naran.core.dao.charitable.CharitableRecordDao;
import com.naran.core.entity.charitable.CharitableRecord;
import com.naran.foundation.mybatis.dao.MyBatisDAO;
import com.naran.foundation.mybatis.page.Page;
import com.naran.foundation.mybatis.page.PageRequest;

/**
 * 
 * @author zefeng.xu
 */
@Repository
public class CharitableRecordDaoImpl implements CharitableRecordDao {

    private static final String ADD_CHARITABLE_RECORD = "addCharitableRecord";
    private static final String UPDATE_CHARITABLE_RECORD = "updateCharitableRecord";
    private static final String FIND_CHARITABLE_RECORD_BY_ID = "findCharitableRecordById";
    private static final String FIND_CHARITABLE_RECORD_BY_PAGE = "findCharitableRecordByPage";

    @Autowired
    private MyBatisDAO myBatisDAO;

    @Override
    public Long addCharitableRecord(CharitableRecord charitableRecord) {
	charitableRecord.setCreateTime(new Date());
	charitableRecord.setUpdateTime(new Date());
	charitableRecord.setExpired(Boolean.TRUE);
	myBatisDAO.insert(ADD_CHARITABLE_RECORD, charitableRecord);

	return charitableRecord.getId();
    }

    @Override
    public void updateCharitableRecord(CharitableRecord charitableRecord) {
	charitableRecord.setUpdateTime(new Date());
	myBatisDAO.update(UPDATE_CHARITABLE_RECORD, charitableRecord);
    }

    @Override
    public void deleteCharitableRecord(Long id) {
	CharitableRecord charitableRecord = new CharitableRecord();
	charitableRecord.setId(id);
	charitableRecord.setExpired(Boolean.FALSE);
	myBatisDAO.update(UPDATE_CHARITABLE_RECORD, charitableRecord);
    }

    @Override
    public CharitableRecord findCharitableRecordById(Long id) {
	if (id == null) {
	    return null;
	}
	return (CharitableRecord) myBatisDAO.findForObject(FIND_CHARITABLE_RECORD_BY_ID, id);
    }

    @SuppressWarnings("unchecked")
    @Override
    public Page<CharitableRecord> findCharitableRecordByPage(int pageNum, int pageSize) {
	Map<String, Object> param = new HashMap<String, Object>();
	return myBatisDAO.findForPage(FIND_CHARITABLE_RECORD_BY_PAGE, new PageRequest(pageNum, pageSize, param));
    }

}
