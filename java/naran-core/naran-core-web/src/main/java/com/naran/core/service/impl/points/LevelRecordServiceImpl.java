package com.naran.core.service.impl.points;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.naran.core.dao.points.LevelRecordDao;
import com.naran.core.entity.points.LevelRecord;
import com.naran.dubbo.service.points.ILevelRecordService;
import com.naran.foundation.mybatis.page.Page;

/**
 * 
 * @author zefeng.xu
 */
@Transactional
@Service("levelRecordService")
public class LevelRecordServiceImpl implements ILevelRecordService {

    @Autowired
    private LevelRecordDao levelRecordDao;

    @Override
    public Long addLevelRecord(LevelRecord levelRecord) {
	if (levelRecord == null) {
	    return null;
	}
	return levelRecordDao.addLevelRecord(levelRecord);
    }

    @Override
    public void updateLevelRecord(LevelRecord levelRecord) {
	if (levelRecord == null || levelRecord.getId() == null) {
	    return;
	}
	levelRecordDao.updateLevelRecord(levelRecord);

    }

    @Override
    public void deleteLevelRecord(Long id) {
	if (id == null) {
	    return;
	}
	levelRecordDao.deleteLevelRecord(id);

    }

    @Override
    public LevelRecord findLevelRecordById(Long id) {
	if (id == null) {
	    return null;
	}
	return levelRecordDao.findLevelRecordById(id);
    }

    @Override
    public Page<LevelRecord> findLevelRecordByPage(int pageNum, int pageSize) {

	return levelRecordDao.findLevelRecordByPage(pageNum, pageSize);
    }

}
