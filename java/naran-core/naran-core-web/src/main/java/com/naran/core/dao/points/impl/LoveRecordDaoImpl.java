package com.naran.core.dao.points.impl;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.naran.core.dao.points.LoveRecordDao;
import com.naran.core.entity.points.LoveRecord;
import com.naran.foundation.mybatis.dao.MyBatisDAO;
import com.naran.foundation.mybatis.page.Page;
import com.naran.foundation.mybatis.page.PageRequest;

/**
 * 
 * @author zefeng.xu
 */
@Repository
public class LoveRecordDaoImpl implements LoveRecordDao {

    private static final String ADD_LOVE_RECORD = "addLoveRecord";
    private static final String UPDATE_LOVE_RECORD = "updateLoveRecord";
    private static final String FIND_LOVE_RECORD_BY_ID = "findLoveRecordById";
    private static final String FIND_LOVE_RECORD_BY_PAGE = "findLoveRecordByPage";

    @Autowired
    private MyBatisDAO myBatisDAO;

    @Override
    public Long addLoveRecord(LoveRecord loveRecord) {
	loveRecord.setCreateTime(new Date());
	loveRecord.setUpdateTime(new Date());
	loveRecord.setExpired(Boolean.TRUE);
	myBatisDAO.insert(ADD_LOVE_RECORD, loveRecord);
	return loveRecord.getId();
    }

    @Override
    public void updateLoveRecord(LoveRecord loveRecord) {
	loveRecord.setUpdateTime(new Date());
	myBatisDAO.update(UPDATE_LOVE_RECORD, loveRecord);
    }

    @Override
    public void deleteLoveRecord(Long id) {
	LoveRecord loveRecord = new LoveRecord();
	loveRecord.setId(id);
	loveRecord.setExpired(Boolean.FALSE);
	myBatisDAO.update(UPDATE_LOVE_RECORD, loveRecord);
    }

    @Override
    public LoveRecord findLoveRecordById(Long id) {
	if (id == null) {
	    return null;
	}
	return (LoveRecord) myBatisDAO.findForObject(FIND_LOVE_RECORD_BY_ID, id);
    }

    @SuppressWarnings("unchecked")
    @Override
    public Page<LoveRecord> findLoveRecordByPage(int pageNum, int pageSize) {
	Map<String, Object> param = new HashMap<String, Object>();
	return myBatisDAO.findForPage(FIND_LOVE_RECORD_BY_PAGE, new PageRequest(pageNum, pageSize, param));
    }

}
