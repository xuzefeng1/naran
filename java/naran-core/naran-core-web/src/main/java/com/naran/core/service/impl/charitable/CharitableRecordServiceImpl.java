package com.naran.core.service.impl.charitable;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.naran.core.dao.charitable.CharitableRecordDao;
import com.naran.core.entity.charitable.CharitableRecord;
import com.naran.dubbo.service.charitable.ICharitableRecordService;
import com.naran.foundation.mybatis.page.Page;

/**
 * 
 * @author zefeng.xu
 */
@Transactional
@Service("charitableRecordService")
public class CharitableRecordServiceImpl implements ICharitableRecordService {

    @Autowired
    private CharitableRecordDao charitableRecordDao;

    @Override
    public Long addCharitableRecord(CharitableRecord charitableRecord) {
	if (charitableRecord == null) {
	    return null;
	}
	return charitableRecordDao.addCharitableRecord(charitableRecord);
    }

    @Override
    public void updateCharitableRecord(CharitableRecord charitableRecord) {
	if (charitableRecord == null || charitableRecord.getId() == null) {
	    return;
	}
	charitableRecordDao.updateCharitableRecord(charitableRecord);

    }

    @Override
    public void deleteCharitableRecord(Long id) {
	if (id == null) {
	    return;
	}
	charitableRecordDao.deleteCharitableRecord(id);

    }

    @Override
    public CharitableRecord findCharitableRecordById(Long id) {
	if (id == null) {
	    return null;
	}
	return charitableRecordDao.findCharitableRecordById(id);
    }

    @Override
    public Page<CharitableRecord> findCharitableRecordByPage(int pageNum, int pageSize) {

	return charitableRecordDao.findCharitableRecordByPage(pageNum, pageSize);
    }

}
