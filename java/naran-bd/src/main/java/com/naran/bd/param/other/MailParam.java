package com.naran.bd.param.other;

import com.naran.bd.param.BaseParam;

/**
 * 地址入参
 * 
 * @author zefeng.xu
 * */
public class MailParam extends BaseParam {

    private Long mailId;

    private Boolean nowMail;

    private String mailName;

    private String mailPhone;

    private String mailAddress;

    private String city;

    private String province;

    private String county;

    public Long getMailId() {
	return mailId;
    }

    public void setMailId(Long mailId) {
	this.mailId = mailId;
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

}
