package com.naran.core.dao.account.impl;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.naran.core.dao.account.AccountRecordDao;
import com.naran.core.entity.account.AccountRecord;
import com.naran.foundation.mybatis.dao.MyBatisDAO;
import com.naran.foundation.util.StringUtil;

/**
 * 
 * @author zefeng.xu
 */
@Repository
public class AccountRecordDaoImpl implements AccountRecordDao {

    private static final String ADD_ACCOUNT_RECORD = "addAccountRecord";
    private static final String UPDATE_ACCOUNT_RECORD = "updateAccountRecord";
    private static final String FIND_ACCOUNT_RECORD_BY_ACCOUNT_ID = "findAccountRecordByAccountId";
    private static final String FIND_ACCOUNT_RECORD_BY_ACCESS_TOKEN = "findAccountRecordByAccessToken";

    @Autowired
    private MyBatisDAO myBatisDAO;

    @Override
    public Long addAccountRecord(AccountRecord accountRecord) {
	accountRecord.setCreateTime(new Date());
	accountRecord.setUpdateTime(new Date());
	accountRecord.setExpired(Boolean.TRUE);
	myBatisDAO.insert(ADD_ACCOUNT_RECORD, accountRecord);

	return accountRecord.getId();
    }

    @Override
    public void updateAccountRecord(AccountRecord accountRecord) {
	accountRecord.setUpdateTime(new Date());
	myBatisDAO.update(UPDATE_ACCOUNT_RECORD, accountRecord);
    }

    @Override
    public void deleteAccountRecord(Long id) {
	AccountRecord accountRecord = new AccountRecord();
	accountRecord.setId(id);
	accountRecord.setExpired(Boolean.FALSE);
	myBatisDAO.update(UPDATE_ACCOUNT_RECORD, accountRecord);
    }

    @SuppressWarnings("unchecked")
    @Override
    public List<AccountRecord> findAccountRecordByAccountId(Long accountId) {
	if (accountId == null) {
	    return null;
	}
	return myBatisDAO.findForList(FIND_ACCOUNT_RECORD_BY_ACCOUNT_ID, accountId);
    }

    @Override
    public AccountRecord findAccountRecordByAccessToken(String accessToken) {
	if (StringUtil.isBlank(accessToken)) {
	    return null;
	}
	return (AccountRecord) myBatisDAO.findForObject(FIND_ACCOUNT_RECORD_BY_ACCESS_TOKEN, accessToken);
    }

}
