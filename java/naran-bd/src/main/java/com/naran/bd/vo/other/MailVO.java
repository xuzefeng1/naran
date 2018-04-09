package com.naran.bd.vo.other;

import org.springframework.beans.BeanUtils;

import com.naran.core.entity.other.Mail;
import com.naran.foundation.util.BaseVO;
import com.naran.foundation.util.DateUtil;

/**
 * 轮播图展示封装
 * 
 * @author zefeng.xu
 */
public class MailVO implements BaseVO {

    private Long mailId;

    private Long accountId;

    private Boolean nowMail;

    private String mailName;

    private String mailPhone;

    private String mailAddress;

    private String city;

    private String province;

    private String county;

    private String createTime;

    public Long getMailId() {
	return mailId;
    }

    public void setMailId(Long mailId) {
	this.mailId = mailId;
    }

    public Long getAccountId() {
	return accountId;
    }

    public void setAccountId(Long accountId) {
	this.accountId = accountId;
    }

    public Boolean getNowMail() {
	return nowMail;
    }

    public void setNowMail(Boolean nowMail) {
	this.nowMail = nowMail;
    }

    public String getMailName() {
	return mailName;
    }

    public void setMailName(String mailName) {
	this.mailName = mailName;
    }

    public String getMailPhone() {
	return mailPhone;
    }

    public void setMailPhone(String mailPhone) {
	this.mailPhone = mailPhone;
    }

    public String getMailAddress() {
	return mailAddress;
    }

    public void setMailAddress(String mailAddress) {
	this.mailAddress = mailAddress;
    }

    public String getCreateTime() {
	return createTime;
    }

    public void setCreateTime(String createTime) {
	this.createTime = createTime;
    }

    public String getCity() {
	return city;
    }

    public void setCity(String city) {
	this.city = city;
    }

    public String getProvince() {
	return province;
    }

    public void setProvince(String province) {
	this.province = province;
    }

    public String getCounty() {
	return county;
    }

    public void setCounty(String county) {
	this.county = county;
    }

    @Override
    public void convertPOToVO(Object poObj) {
	if (null == poObj) {
	    return;
	}

	Mail po = (Mail) poObj;
	BeanUtils.copyProperties(po, this);
	this.mailId = po.getId();
	this.createTime = DateUtil.formatDateYMDHMS(po.getCreateTime());

    }

}
