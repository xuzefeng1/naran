package com.naran.bd.vo.other;

import java.util.List;

import com.naran.foundation.util.BasePageVO;

/**
 * 展示封装（分页）
 * 
 * @author zefeng.xu
 */
public class MailPageVO extends BasePageVO {

    private List<MailVO> mails;

    public List<MailVO> getMails() {
	return mails;
    }

    public void setMails(List<MailVO> mails) {
	this.mails = mails;
    }

}
