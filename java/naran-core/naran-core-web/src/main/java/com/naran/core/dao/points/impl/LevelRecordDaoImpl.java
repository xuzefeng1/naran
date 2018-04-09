package com.naran.core.dao.points.impl;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.naran.core.dao.points.LevelRecordDao;
import com.naran.core.entity.points.LevelRecord;
import com.naran.foundation.mybatis.dao.MyBatisDAO;
import com.naran.foundation.mybatis.page.Page;
import com.naran.foundation.mybatis.page.PageRequest;

/**
 * 
 * @author zefeng.xu
 */
@Repository
public class LevelRecordDaoImpl implements LevelRecordDao {

    private static final String ADD_LEVEL_RECORD = "addLevelRecord";
    private static final String UPDATE_LEVEL_RECORD = "updateLevelRecord";
    private static final String FIND_LEVEL_RECORD_BY_ID = "findLevelRecordById";
    private static final String FIND_LEVEL_RECORD_BY_PAGE = "findLevelRecordByPage";

    @Autowired
    private MyBatisDAO myBatisDAO;

    @Override
    public Long addLevelRecord(LevelRecord levelRecord) {
	levelRecord.setCreateTime(new Date());
	levelRecord.setUpdateTime(new Date());
	levelRecord.setExpired(Boolean.TRUE);
	myBatisDAO.insert(ADD_LEVEL_RECORD, levelRecord);
	return levelRecord.getId();
    }

    @Override
    public void updateLevelRecord(LevelRecord levelRecord) {
	levelRecord.setUpdateTime(new Date());
	myBatisDAO.update(UPDATE_LEVEL_RECORD, levelRecord);
    }

    @Override
    public void deleteLevelRecord(Long id) {
	LevelRecord levelRecord = new LevelRecord();
	levelRecord.setId(id);
	levelRecord.setExpired(Boolean.FALSE);
	myBatisDAO.update(UPDATE_LEVEL_RECORD, levelRecord);
    }

    @Override
    public LevelRecord findLevelRecordById(Long id) {
	if (id == null) {
	    return null;
	}
	return (LevelRecord) myBatisDAO.findForObject(FIND_LEVEL_RECORD_BY_ID, id);
    }

    @SuppressWarnings("unchecked")
    @Override
    public Page<LevelRecord> findLevelRecordByPage(int pageNum, int pageSize) {
	Map<String, Object> param = new HashMap<String, Object>();
	return myBatisDAO.findForPage(FIND_LEVEL_RECORD_BY_PAGE, new PageRequest(pageNum, pageSize, param));
    }

}
