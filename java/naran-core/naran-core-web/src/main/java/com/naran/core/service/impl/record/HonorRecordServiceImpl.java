package com.naran.core.service.impl.record;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.naran.core.dao.record.HonorRecordDao;
import com.naran.core.entity.record.HonorRecord;
import com.naran.dubbo.service.record.IHonorRecordService;
import com.naran.foundation.mybatis.page.Page;

/**
 * 
 * @author zefeng.xu
 */
@Transactional
@Service("honorRecordService")
public class HonorRecordServiceImpl implements IHonorRecordService {

    @Autowired
    private HonorRecordDao honorRecordDao;

    @Override
    public Long addHonorRecord(HonorRecord honorRecord) {
	if (honorRecord == null) {
	    return null;
	}
	return honorRecordDao.addHonorRecord(honorRecord);
    }

    @Override
    public void updateHonorRecord(HonorRecord honorRecord) {
	if (honorRecord == null || honorRecord.getId() == null) {
	    return;
	}
	honorRecordDao.updateHonorRecord(honorRecord);

    }

    @Override
    public void deleteHonorRecord(Long id) {
	if (id == null) {
	    return;
	}
	honorRecordDao.deleteHonorRecord(id);

    }

    @Override
    public HonorRecord findHonorRecordById(Long id) {
	if (id == null) {
	    return null;
	}
	return honorRecordDao.findHonorRecordById(id);
    }

    @Override
    public Page<HonorRecord> findHonorRecordByPage(int pageNum, int pageSize) {

	return honorRecordDao.findHonorRecordByPage(pageNum, pageSize);
    }

}
