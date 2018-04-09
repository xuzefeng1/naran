package com.naran.core.service.impl.other;

import org.apache.commons.collections.CollectionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.naran.core.dao.other.MailDao;
import com.naran.core.entity.other.Mail;
import com.naran.dubbo.service.other.IMailService;
import com.naran.foundation.mybatis.page.Page;

/**
 * 
 * @author zefeng.xu
 */
@Transactional
@Service("mailService")
public class MailServiceImpl implements IMailService {

    @Autowired
    private MailDao mailDao;

    @Override
    public Long addMail(Mail mail) {
	if (mail == null) {
	    return null;
	}
	updateNowMail(mail);
	return mailDao.addMail(mail);
    }

    @Override
    public void updateMail(Mail mail) {
	if (mail == null || mail.getId() == null) {
	    return;
	}
	updateNowMail(mail);
	mailDao.updateMail(mail);

    }

    @Override
    public void deleteMail(Long id, Long accountId) {
	if (id == null) {
	    return;
	}
	Mail mail = findMailById(id);
	mailDao.deleteMail(id);
	Page<Mail> page = mailDao.findMailByPage(accountId, 1, 1);
	if (mail.getNowMail() && null != page && CollectionUtils.isNotEmpty(page.getResult())) {
	    mail = page.getResult().get(0);
	    mail.setNowMail(Boolean.TRUE);
	    mailDao.updateMail(mail);
	}

    }

    @Override
    public Mail findMailById(Long id) {
	if (id == null) {
	    return null;
	}
	return mailDao.findMailById(id);
    }

    @Override
    public Mail findMailByNowMail(Long accountId) {
	if (accountId == null) {
	    return null;
	}
	return mailDao.findMailByNowMail(accountId);
    }

    @Override
    public Page<Mail> findMailByPage(Long accountId, int pageNum, int pageSize) {
	return mailDao.findMailByPage(accountId, pageNum, pageSize);
    }

    private void updateNowMail(Mail mail) {
	if (mail == null || mail.getAccountId() == null || !mail.getNowMail()) {
	    return;
	}
	mailDao.updateMailNowMailFalse(mail.getAccountId());
    }

}
