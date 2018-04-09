package com.naran.web.param.content;

import com.naran.web.param.BasePageParam;

public class ContentPageParam extends BasePageParam {

    private Long accountId;

    private String contentType;

    public Long getAccountId() {
	return accountId;
    }

    public void setAccountId(Long accountId) {
	this.accountId = accountId;
    }

    public String getContentType() {
	return contentType;
    }

    public void setContentType(String contentType) {
	this.contentType = contentType;
    }

}
