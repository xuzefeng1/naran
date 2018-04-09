package com.naran.dubbo.service.account;

import java.util.List;

import com.naran.core.entity.account.AccountRecord;

/**
 * 平台账号记录服务接口
 * 
 * @author zefeng.xu
 */
public interface IAccountRecordService {
    
    AccountRecord addAccountRecord(AccountRecord accountRecord);

    void updateAccountRecord(AccountRecord accountRecord);

    void deleteAccountRecord(Long id);

    List<AccountRecord> findAccountRecordByAccountId(Long accountId);

    AccountRecord findAccountRecordByAccessToken(String accessToken);
}
