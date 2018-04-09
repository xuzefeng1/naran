package com.naran.core.dao.account.impl;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.naran.core.dao.account.AccountDao;
import com.naran.core.entity.account.Account;
import com.naran.core.enums.CertificationType;
import com.naran.foundation.mybatis.dao.MyBatisDAO;
import com.naran.foundation.mybatis.page.Page;
import com.naran.foundation.mybatis.page.PageRequest;
import com.naran.foundation.util.StringUtil;

/**
 * 
 * @author zefeng.xu
 */
@Repository
public class AccountDaoImpl implements AccountDao {

    private static final String ADD_ACCOUNT = "addAccount";
    private static final String UPDATE_ACCOUNT = "updateAccount";
    private static final String FIND_ACCOUNT_BY_ID = "findAccountById";
    private static final String FIND_ACCOUNT_BY_PHONE = "findAccountByPhone";
    private static final String FIND_ACCOUNT_BY_LOGIN = "findAccountByLogin";
    private static final String FIND_ACCOUNT_BY_PAGE = "findAccountByPage";

    @Autowired
    private MyBatisDAO myBatisDAO;

    @Override
    public Long addAccount(Account account) {
	account.setCreateTime(new Date());
	account.setUpdateTime(new Date());
	account.setExpired(Boolean.TRUE);
	account.setCertificationType(CertificationType.INITIAL.name());
	myBatisDAO.insert(ADD_ACCOUNT, account);

	return account.getId();
    }

    @Override
    public void updateAccount(Account account) {
	account.setUpdateTime(new Date());
	myBatisDAO.update(UPDATE_ACCOUNT, account);
    }

    @Override
    public void updateAccountPassword(Long accountId, String password) {
	Map<String, Object> param = new HashMap<String, Object>();
	param.put("id", accountId);
	param.put("password", password);
	myBatisDAO.update(UPDATE_ACCOUNT, param);
    }

    @Override
    public void deleteAccount(Long id) {
	Account account = new Account();
	account.setId(id);
	account.setExpired(Boolean.FALSE);
	myBatisDAO.update(UPDATE_ACCOUNT, account);
    }

    @Override
    public Account findAccountById(Long id) {
	if (id == null) {
	    return null;
	}
	return (Account) myBatisDAO.findForObject(FIND_ACCOUNT_BY_ID, id);
    }

    @Override
    public Account findAccountByPhone(String phone) {
	if (StringUtil.isBlank(phone)) {
	    return null;
	}
	return (Account) myBatisDAO.findForObject(FIND_ACCOUNT_BY_PHONE, phone);
    }

    @Override
    public Account findAccountByLogin(String phone, String password) {
	if (StringUtil.isBlank(phone) || StringUtil.isBlank(password)) {
	    return null;
	}
	Map<String, Object> param = new HashMap<String, Object>();
	param.put("phone", phone);
	param.put("password", password);
	return (Account) myBatisDAO.findForObject(FIND_ACCOUNT_BY_LOGIN, param);
    }

    @SuppressWarnings("unchecked")
    @Override
    public Page<Account> findAccountByPage(int pageNum, int pageSize) {
	Map<String, Object> param = new HashMap<String, Object>();
	return myBatisDAO.findForPage(FIND_ACCOUNT_BY_PAGE, new PageRequest(pageNum, pageSize, param));
    }
}
