package com.naran.core.dao.account;

import com.naran.core.entity.account.Account;
import com.naran.foundation.mybatis.page.Page;

/**
 * 账号数据访问接口
 * 
 * @author zefeng.xu
 */
public interface AccountDao {

    Long addAccount(Account account);

    void updateAccount(Account account);

    void updateAccountPassword(Long accountId, String password);

    void deleteAccount(Long id);

    Account findAccountById(Long id);

    Account findAccountByPhone(String phone);

    Account findAccountByLogin(String phone, String password);

    Page<Account> findAccountByPage(int pageNum, int pageSize);

}
