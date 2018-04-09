package com.naran.core.entity.account;

import com.naran.foundation.entity.BaseEntityPO;

/**
 * 平台账号
 * 
 * @author zefeng.xu
 */
public class Account extends BaseEntityPO {

    /**
	 * 
	 */
    private static final long serialVersionUID = 1L;

    private String username;

    private String password;

    private String nickName;

    private String name;

    private String phone;

    private String photo;

    private String city;

    private String location;

    private String sex;

    private String birthday;

    private String identityCard;

    private Long nowExperience;

    private Long nowLove;

    private String cardTime;

    private String province;// 省

    private String county;// 县

    private String remark;

    private String cardPositive;// 身份证正面

    private String cardOpposite;// 身份证反面

    private String certificationType;

    /* 值传递，假字段 */
    private String accessToken; // 登录授权码

    public String getUsername() {
	return username;
    }

    public void setUsername(String username) {
	this.username = username;
    }

    public String getPassword() {
	return password;
    }

    public void setPassword(String password) {
	this.password = password;
    }

    public String getNickName() {
	return nickName;
    }

    public void setNickName(String nickName) {
	this.nickName = nickName;
    }

    public String getName() {
	return name;
    }

    public void setName(String name) {
	this.name = name;
    }

    public String getPhone() {
	return phone;
    }

    public void setPhone(String phone) {
	this.phone = phone;
    }

    public String getPhoto() {
	return photo;
    }

    public void setPhoto(String photo) {
	this.photo = photo;
    }

    public String getCity() {
	return city;
    }

    public void setCity(String city) {
	this.city = city;
    }

    public String getLocation() {
	return location;
    }

    public void setLocation(String location) {
	this.location = location;
    }

    public String getSex() {
	return sex;
    }

    public void setSex(String sex) {
	this.sex = sex;
    }

    public String getBirthday() {
	return birthday;
    }

    public void setBirthday(String birthday) {
	this.birthday = birthday;
    }

    public String getIdentityCard() {
	return identityCard;
    }

    public void setIdentityCard(String identityCard) {
	this.identityCard = identityCard;
    }

    public Long getNowExperience() {
	return nowExperience;
    }

    public void setNowExperience(Long nowExperience) {
	this.nowExperience = nowExperience;
    }

    public Long getNowLove() {
	return nowLove;
    }

    public void setNowLove(Long nowLove) {
	this.nowLove = nowLove;
    }

    public String getCardTime() {
	return cardTime;
    }

    public void setCardTime(String cardTime) {
	this.cardTime = cardTime;
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

    public String getRemark() {
	return remark;
    }

    public void setRemark(String remark) {
	this.remark = remark;
    }

    public String getCardPositive() {
	return cardPositive;
    }

    public void setCardPositive(String cardPositive) {
	this.cardPositive = cardPositive;
    }

    public String getCardOpposite() {
	return cardOpposite;
    }

    public void setCardOpposite(String cardOpposite) {
	this.cardOpposite = cardOpposite;
    }

    public String getCertificationType() {
	return certificationType;
    }

    public void setCertificationType(String certificationType) {
	this.certificationType = certificationType;
    }

    public String getAccessToken() {
	return accessToken;
    }

    public void setAccessToken(String accessToken) {
	this.accessToken = accessToken;
    }

}
