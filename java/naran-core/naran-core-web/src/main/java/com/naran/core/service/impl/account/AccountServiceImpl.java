package com.naran.core.service.impl.account;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.naran.core.dao.account.AccountDao;
import com.naran.core.entity.account.Account;
import com.naran.core.entity.account.AccountRecord;
import com.naran.dubbo.response.DubboResponse;
import com.naran.dubbo.response.DubboResponseCode;
import com.naran.dubbo.service.account.IAccountRecordService;
import com.naran.dubbo.service.account.IAccountService;
import com.naran.dubbo.service.other.IMessageService;
import com.naran.foundation.dto.SmsMessageCategory;
import com.naran.foundation.util.StringUtil;

/**
 * 平台账号服务
 * 
 * @author zefeng.xu
 */
@Transactional
@Service("accountService")
public class AccountServiceImpl implements IAccountService {

    @Autowired
    private AccountDao accountDao;
    @Autowired
    private IMessageService messageService;
    @Autowired
    private IAccountRecordService accountRecordService;

    @Override
    public DubboResponse<Account> register(String deviceNo, String phone, String password, String cPassword, String verifycode) {
	DubboResponse<Account> response = new DubboResponse<Account>();
	// 验证手机号码正确性
	if (!StringUtil.isMobile(phone)) {
	    response.setCode(DubboResponseCode.FAIL);
	    response.setMsg("请输入正确的手机号码！");
	    return response;
	}
	// 账号是否已存在
	Account exist = accountDao.findAccountByPhone(phone);
	if (null != exist) {
	    response.setCode(DubboResponseCode.FAIL);
	    response.setMsg("该账号已存在！");
	    return response;
	}

	// 密码正确性
	if (StringUtil.isBlank(password) || StringUtil.isBlank(cPassword)) {
	    response.setCode(DubboResponseCode.FAIL);
	    response.setMsg("请输入密码！");
	    return response;
	} else if (!StringUtil.equals(password, cPassword)) {
	    response.setCode(DubboResponseCode.FAIL);
	    response.setMsg("两次密码不一致！");
	    return response;
	}

	// 验证码正确性
	if (StringUtil.isBlank(verifycode)) {
	    response.setCode(DubboResponseCode.FAIL);
	    response.setMsg("请输入验证码！");
	    return response;
	} else {
	    String existVerifycode = messageService.getVerifyCode(phone, SmsMessageCategory.REGISTER_VERIFY_CODE);
	    if (StringUtil.isBlank(existVerifycode)) {
		response.setCode(DubboResponseCode.FAIL);
		response.setMsg("验证码不存在或已过期！");
		return response;
	    } else if (!StringUtil.equals(verifycode, existVerifycode)) {
		response.setCode(DubboResponseCode.FAIL);
		response.setMsg("验证码不正确！");
		return response;
	    }
	}

	// 可以注册，执行注册流程
	// 创建account
	Account account = new Account();
	account.setUsername(phone);
	account.setPhone(phone);
	account.setPassword(password);
	account.setNickName(getNickname(phone));
	Long accountId = accountDao.addAccount(account);
	account.setId(accountId);
	AccountRecord record = new AccountRecord();
	record.setAccountId(accountId);
	record.setDeviceNo(deviceNo);
	// 注册成功，自动登录并返回当前登录用户
	record = accountRecordService.addAccountRecord(record);
	account.setAccessToken(record.getAccessToken());
	response.setData(account);
	return response;
    }

    private String getNickname(String str) {
	return str.replaceAll("(\\d{3})\\d{4}(\\d{4})", "$1****$2");
    }

    public DubboResponse<String> sendVerifycodeByRegister(String phone) {

	DubboResponse<String> response = new DubboResponse<String>();
	// 验证手机号码正确性
	if (!StringUtil.isMobile(phone)) {
	    response.setCode(DubboResponseCode.FAIL);
	    response.setMsg("请输入正确的手机号码！");
	    return response;
	}
	// 账号是否已存在
	Account exist = accountDao.findAccountByPhone(phone);
	if (null != exist) {
	    response.setCode(DubboResponseCode.FAIL);
	    response.setMsg("该账号已存在！");
	    return response;
	}
	String verifycode = messageService.generateVerifyCode(phone, SmsMessageCategory.REGISTER_VERIFY_CODE, 1);
	response.setData(verifycode);
	return response;
    }

    @Override
    public DubboResponse<Account> login(String deviceNo, String phone, String password) {
	DubboResponse<Account> response = new DubboResponse<Account>();
	// 输入参数合法性
	if (StringUtil.isBlank(phone)) {
	    response.setCode(DubboResponseCode.FAIL);
	    response.setMsg("请输入正确的账号！");
	    return response;
	}
	if (StringUtil.isBlank(password)) {
	    response.setCode(DubboResponseCode.FAIL);
	    response.setMsg("请输入密码！");
	    return response;
	}
	Account exist = accountDao.findAccountByLogin(phone, password);
	if (null == exist) {
	    response.setCode(DubboResponseCode.FAIL);
	    response.setMsg("用户名或密码错误！");
	    return response;
	}

	// 登录并返回当前登录用户
	AccountRecord record = new AccountRecord();
	record.setAccountId(exist.getId());
	record.setDeviceNo(deviceNo);
	record = accountRecordService.addAccountRecord(record);
	exist.setAccessToken(record.getAccessToken());

	response.setData(exist);
	return response;
    }

    @Override
    public void logout(Long accountId) {
	List<AccountRecord> records = accountRecordService.findAccountRecordByAccountId(accountId);
	if (null != records) {
	    for (AccountRecord record : records) {
		if (null == record) {
		    continue;
		}
		accountRecordService.deleteAccountRecord(record.getId());
	    }
	}
    }

    @Override
    public DubboResponse<String> sendVerifycodeByForgetPwd(String phone) {
	DubboResponse<String> response = new DubboResponse<String>();

	// 输入参数合法性
	// 验证手机号码正确性
	if (StringUtil.isBlank(phone) || !StringUtil.isMobile(phone)) {
	    response.setCode(DubboResponseCode.FAIL);
	    response.setMsg("请输入正确的手机号码！");
	    return response;
	}

	// 账号是否存在
	Account exist = accountDao.findAccountByPhone(phone);
	if (null == exist) {
	    response.setCode(DubboResponseCode.FAIL);
	    response.setMsg("该账号不存在！");
	    return response;
	}

	String verifycode = messageService.generateVerifyCode(phone, SmsMessageCategory.FORGET_PASSWORD_VERIFY_CODE, 1);

	response.setData(verifycode);
	return response;
    }

    @Override
    public DubboResponse<Account> forgetPwd(String deviceNo, String phone, String password, String cPassword, String verifycode) {
	DubboResponse<Account> response = new DubboResponse<Account>();

	// 输入参数合法性
	// 验证手机号码正确性
	if (!StringUtil.isMobile(phone)) {
	    response.setCode(DubboResponseCode.FAIL);
	    response.setMsg("请输入正确的手机号码！");
	    return response;
	}

	// 账号是否已存在
	Account exist = accountDao.findAccountByPhone(phone);
	if (null == exist) {
	    response.setCode(DubboResponseCode.FAIL);
	    response.setMsg("该账号不存在！");
	    return response;
	}

	// 新密码正确性
	if (StringUtil.isBlank(password) || StringUtil.isBlank(cPassword)) {
	    response.setCode(DubboResponseCode.FAIL);
	    response.setMsg("请输入新密码！");
	    return response;
	} else if (!StringUtil.equals(password, cPassword)) {
	    response.setCode(DubboResponseCode.FAIL);
	    response.setMsg("两次密码不一致！");
	    return response;
	}

	// 验证码正确性
	if (StringUtil.isBlank(verifycode)) {
	    response.setCode(DubboResponseCode.FAIL);
	    response.setMsg("请输入验证码！");
	    return response;
	} else {
	    String existVerifycode = messageService.getVerifyCode(phone, SmsMessageCategory.FORGET_PASSWORD_VERIFY_CODE);
	    if (StringUtil.isBlank(existVerifycode)) {
		response.setCode(DubboResponseCode.FAIL);
		response.setMsg("验证码不存在或已过期！");
		return response;
	    } else if (!StringUtil.equals(verifycode, existVerifycode)) {
		response.setCode(DubboResponseCode.FAIL);
		response.setMsg("验证码不正确！");
		return response;
	    }
	}

	// 重置（修改）密码
	accountDao.updateAccountPassword(exist.getId(), password);

	// 自动登录并返回当前登录用户
	AccountRecord record = new AccountRecord();
	record.setAccountId(exist.getId());
	record.setDeviceNo(deviceNo);
	record = accountRecordService.addAccountRecord(record);
	exist.setAccessToken(record.getAccessToken());
	response.setData(exist);
	return response;
    }

    @Override
    public DubboResponse<Long> updatePwdBySuper(Long accountId, String password) {
	DubboResponse<Long> response = new DubboResponse<Long>();
	// 修改密码
	accountDao.updateAccountPassword(accountId, password);
	response.setData(accountId);
	return response;
    }

    @Override
    public Account findAccountByPhone(String phone) {
	return accountDao.findAccountByPhone(phone);
    }

    @Override
    public Account currentAccount(String accessToken) {
	AccountRecord record = accountRecordService.findAccountRecordByAccessToken(accessToken);
	if (null == record) {
	    return null;
	}
	return accountDao.findAccountById(record.getAccountId());
    }

    @Override
    public Account getAccountById(Long accountId) {
	return accountDao.findAccountById(accountId);
    }

    @Override
    public DubboResponse<Long> updateAccount(Account account) {
	DubboResponse<Long> response = new DubboResponse<Long>();
	accountDao.updateAccount(account);
	response.setData(account.getId());
	return response;
    }

    @Override
    public DubboResponse<String> certification(Account account) {
	DubboResponse<String> response = new DubboResponse<String>();
	accountDao.updateAccount(account);
	response.setMsg("成功！");
	return response;
    }

}
