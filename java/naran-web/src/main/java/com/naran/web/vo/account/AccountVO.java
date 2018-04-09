package com.naran.web.vo.account;

import org.springframework.beans.BeanUtils;

import com.naran.core.entity.account.Account;
import com.naran.foundation.util.BaseVO;
import com.naran.foundation.util.file.qiniu.QiniuImageUtil;

/**
 * 平台账号展示封装
 * 
 * @author zefeng.xu
 */
public class AccountVO implements BaseVO {

    private Long accountId;

    private String nickName;

    private String phone;

    private String photo;

    private String city;

    private String location;

    private String sex;

    private String birthday;

    private String identityCard;

    private Long nowExperience;

    private Long nowLove;

    private String name;

    private String cardTime;

    private String province;// 省

    private String county;// 县

    private String cardPositive;// 身份证正面

    private String cardOpposite;// 身份证反面

    private String certificationType;

    private String accessToken; // 当前登录授权码

    public Long getAccountId() {
	return accountId;
    }

    public void setAccountId(Long accountId) {
	this.accountId = accountId;
    }

    public String getNickName() {
	return nickName;
    }

    public void setNickName(String nickName) {
	this.nickName = nickName;
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

    public String getAccessToken() {
	return accessToken;
    }

    public void setAccessToken(String accessToken) {
	this.accessToken = accessToken;
    }

    public String getName() {
	return name;
    }

    public void setName(String name) {
	this.name = name;
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

    @Override
    public void convertPOToVO(Object poObj) {
	if (null == poObj) {
	    return;
	}
	Account po = (Account) poObj;
	BeanUtils.copyProperties(po, this);
	this.accountId = po.getId();
	this.cardPositive = QiniuImageUtil.newImageUrl(po.getCardPositive());// 身份证正面
	this.cardOpposite = QiniuImageUtil.newImageUrl(po.getCardPositive());// 身份证反面
	this.photo = QiniuImageUtil.newImageUrl60(po.getPhoto());
    }
}
