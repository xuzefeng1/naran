package com.naran.core.entity.other;

import com.naran.foundation.entity.BaseEntityPO;

public class Letter extends BaseEntityPO {

    /**
     * 
     */
    private static final long serialVersionUID = 1L;

    private String letterTitle;

    private String letterContent;

    private String letterType;

    private String letterStatus;

    private Long accountId;

    public String getLetterTitle() {
	return letterTitle;
    }

    public void setLetterTitle(String letterTitle) {
	this.letterTitle = letterTitle;
    }

    public String getLetterContent() {
	return letterContent;
    }

    public void setLetterContent(String letterContent) {
	this.letterContent = letterContent;
    }

    public String getLetterType() {
	return letterType;
    }

    public void setLetterType(String letterType) {
	this.letterType = letterType;
    }

    public String getLetterStatus() {
	return letterStatus;
    }

    public void setLetterStatus(String letterStatus) {
	this.letterStatus = letterStatus;
    }

    public Long getAccountId() {
	return accountId;
    }

    public void setAccountId(Long accountId) {
	this.accountId = accountId;
    }

}