package com.naran.core.dao.account;

import java.util.List;

import com.naran.core.entity.account.AccountRecord;

/**
 * 账号记录数据访问接口
 * 
 * @author zefeng.xu
 */
public interface AccountRecordDao {

    Long addAccountRecord(AccountRecord accountRecord);

    void updateAccountRecord(AccountRecord accountRecord);

    void deleteAccountRecord(Long id);

    List<AccountRecord> findAccountRecordByAccountId(Long accountId);

    AccountRecord findAccountRecordByAccessToken(String accessToken);

}
