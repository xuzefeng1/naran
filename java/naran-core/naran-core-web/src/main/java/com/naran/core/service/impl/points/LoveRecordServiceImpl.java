package com.naran.core.service.impl.points;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.naran.core.dao.points.LoveRecordDao;
import com.naran.core.entity.points.LoveRecord;
import com.naran.dubbo.service.points.ILoveRecordService;
import com.naran.foundation.mybatis.page.Page;

/**
 * 
 * @author zefeng.xu
 */
@Transactional
@Service("loveRecordService")
public class LoveRecordServiceImpl implements ILoveRecordService {

    @Autowired
    private LoveRecordDao loveRecordDao;

    @Override
    public Long addLoveRecord(LoveRecord loveRecord) {
	if (loveRecord == null) {
	    return null;
	}
	return loveRecordDao.addLoveRecord(loveRecord);
    }

    @Override
    public void updateLoveRecord(LoveRecord loveRecord) {
	if (loveRecord == null || loveRecord.getId() == null) {
	    return;
	}
	loveRecordDao.updateLoveRecord(loveRecord);

    }

    @Override
    public void deleteLoveRecord(Long id) {
	if (id == null) {
	    return;
	}
	loveRecordDao.deleteLoveRecord(id);

    }

    @Override
    public LoveRecord findLoveRecordById(Long id) {
	if (id == null) {
	    return null;
	}
	return loveRecordDao.findLoveRecordById(id);
    }

    @Override
    public Page<LoveRecord> findLoveRecordByPage(int pageNum, int pageSize) {

	return loveRecordDao.findLoveRecordByPage(pageNum, pageSize);
    }

}
