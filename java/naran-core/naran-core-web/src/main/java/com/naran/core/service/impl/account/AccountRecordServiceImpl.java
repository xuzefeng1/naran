package com.naran.core.service.impl.account;

import java.util.Date;
import java.util.List;

import org.apache.commons.collections.CollectionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.naran.core.dao.account.AccountRecordDao;
import com.naran.core.entity.account.AccountRecord;
import com.naran.dubbo.service.account.IAccountRecordService;
import com.naran.foundation.util.StringUtil;

/**
 * 平台账号服务
 * 
 * @author zefeng.xu
 */
@Transactional
@Service("accountRecordService")
public class AccountRecordServiceImpl implements IAccountRecordService {

    @Autowired
    private AccountRecordDao accountRecordDao;

    @Override
    public AccountRecord addAccountRecord(AccountRecord accountRecord) {
	// 先将之前的登录授权码注销，再生成新的登录授权码
	List<AccountRecord> records = findAccountRecordByAccountId(accountRecord.getAccountId());
	if (CollectionUtils.isNotEmpty(records)) {
	    for (AccountRecord exist : records) {
		if (null != exist && exist.getExpired()) {
		    deleteAccountRecord(exist.getId());
		}
	    }
	}

	String accessToken = StringUtil.generateLoginToken();
	accountRecord.setAccessToken(accessToken);
	accountRecord.setCreateTime(new Date());
	accountRecordDao.addAccountRecord(accountRecord);
	return accountRecord;
    }

    @Override
    public void updateAccountRecord(AccountRecord accountRecord) {
	accountRecordDao.updateAccountRecord(accountRecord);
    }

    @Override
    public void deleteAccountRecord(Long id) {
	accountRecordDao.deleteAccountRecord(id);
    }

    @Override
    public List<AccountRecord> findAccountRecordByAccountId(Long accountId) {
	return accountRecordDao.findAccountRecordByAccountId(accountId);
    }

    @Override
    public AccountRecord findAccountRecordByAccessToken(String accessToken) {
	return accountRecordDao.findAccountRecordByAccessToken(accessToken);
    }

}
