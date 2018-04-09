package com.naran.core.service.impl.record;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.naran.core.dao.other.SignRecordDao;
import com.naran.core.entity.record.SignRecord;
import com.naran.dubbo.service.record.ISignRecordService;
import com.naran.foundation.mybatis.page.Page;

/**
 * 
 * @author zefeng.xu
 */
@Transactional
@Service("signRecordService")
public class SignRecordServiceImpl implements ISignRecordService {

    @Autowired
    private SignRecordDao signRecordDao;

    @Override
    public Long addSignRecord(SignRecord signRecord) {
	if (signRecord == null) {
	    return null;
	}
	return signRecordDao.addSignRecord(signRecord);
    }

    @Override
    public void updateSignRecord(SignRecord signRecord) {
	if (signRecord == null || signRecord.getId() == null) {
	    return;
	}
	signRecordDao.updateSignRecord(signRecord);

    }

    @Override
    public void deleteSignRecord(Long id) {
	if (id == null) {
	    return;
	}
	signRecordDao.deleteSignRecord(id);

    }

    @Override
    public SignRecord findSignRecordById(Long id) {
	if (id == null) {
	    return null;
	}
	return signRecordDao.findSignRecordById(id);
    }

    @Override
    public Page<SignRecord> findSignRecordByPage(Long accountId, String startDate, String endDate, int pageNum, int pageSize) {
	return signRecordDao.findSignRecordByPage(accountId, startDate, endDate, pageNum, pageSize);
    }

    @Override
    public SignRecord findSignRecordByLast(Long accountId) {
	return signRecordDao.findSignRecordByLast(accountId);
    }

}
