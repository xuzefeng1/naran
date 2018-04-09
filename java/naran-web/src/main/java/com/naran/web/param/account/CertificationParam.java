package com.naran.web.param.account;

import com.naran.web.param.BaseParam;

public class CertificationParam extends BaseParam {

    private String name;

    private String sex;

    private String identityCard;

    private String cardTime;

    private String cardPositive;// 身份证正面

    private String cardOpposite;// 身份证反面

    public String getName() {
	return name;
    }

    public void setName(String name) {
	this.name = name;
    }

    public String getSex() {
	return sex;
    }

    public void setSex(String sex) {
	this.sex = sex;
    }

    public String getIdentityCard() {
	return identityCard;
    }

    public void setIdentityCard(String identityCard) {
	this.identityCard = identityCard;
    }

    public String getCardTime() {
	return cardTime;
    }

    public void setCardTime(String cardTime) {
	this.cardTime = cardTime;
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

}
