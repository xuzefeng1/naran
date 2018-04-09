package com.naran.core.entity.other;

import java.util.Date;

import com.naran.foundation.entity.BaseEntityPO;

public class Rank extends BaseEntityPO {

    /**
     * 
     */
    private static final long serialVersionUID = 1L;

    private Long accountId;

    private Integer rankNum;

    private Integer rankScore;

    private Date rankTime;

    private String rankType;

    private String accountName;

    private String accountImg;

    public Long getAccountId() {
	return accountId;
    }

    public void setAccountId(Long accountId) {
	this.accountId = accountId;
    }

    public Integer getRankNum() {
	return rankNum;
    }

    public void setRankNum(Integer rankNum) {
	this.rankNum = rankNum;
    }

    public Integer getRankScore() {
	return rankScore;
    }

    public void setRankScore(Integer rankScore) {
	this.rankScore = rankScore;
    }

    public Date getRankTime() {
	return rankTime;
    }

    public void setRankTime(Date rankTime) {
	this.rankTime = rankTime;
    }

    public String getRankType() {
	return rankType;
    }

    public void setRankType(String rankType) {
	this.rankType = rankType;
    }

    public String getAccountName() {
	return accountName;
    }

    public void setAccountName(String accountName) {
	this.accountName = accountName;
    }

    public String getAccountImg() {
	return accountImg;
    }

    public void setAccountImg(String accountImg) {
	this.accountImg = accountImg;
    }

}