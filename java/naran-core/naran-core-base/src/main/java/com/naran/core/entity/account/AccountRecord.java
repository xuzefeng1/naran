package com.naran.core.entity.account;

import com.naran.foundation.entity.BaseEntityPO;

/**
 * 账号登录记录
 * 
 * @author zefeng.xu
 */
public class AccountRecord extends BaseEntityPO {

    /**
	 * 
	 */
    private static final long serialVersionUID = 1L;

    private Long accountId;

    private String deviceNo;

    private String accessToken;

    public Long getAccountId() {
	return accountId;
    }

    public void setAccountId(Long accountId) {
	this.accountId = accountId;
    }

    public String getDeviceNo() {
	return deviceNo;
    }

    public void setDeviceNo(String deviceNo) {
	this.deviceNo = deviceNo;
    }

    public String getAccessToken() {
	return accessToken;
    }

    public void setAccessToken(String accessToken) {
	this.accessToken = accessToken;
    }

}
