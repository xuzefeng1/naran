package com.naran.core.dao.other.impl;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.naran.core.dao.other.MailDao;
import com.naran.core.entity.other.Mail;
import com.naran.foundation.mybatis.dao.MyBatisDAO;
import com.naran.foundation.mybatis.page.Page;
import com.naran.foundation.mybatis.page.PageRequest;

/**
 * 
 * @author zefeng.xu
 */
@Repository
public class MailDaoImpl implements MailDao {

    private static final String ADD_MAIL = "addMail";
    private static final String UPDATE_MAIL = "updateMail";
    private static final String UPDATE_MAIL_NOW_MAIL_FALSE = "updateMailNowMailFalse";
    private static final String FIND_MAIL_BY_ID = "findMailById";
    private static final String FIND_MAIL_BY_NOW_MAIL="findMailByNowMail";
    private static final String FIND_MAIL_BY_PAGE = "findMailByPage";

    @Autowired
    private MyBatisDAO myBatisDAO;

    @Override
    public Long addMail(Mail mail) {
	mail.setCreateTime(new Date());
	mail.setUpdateTime(new Date());
	mail.setExpired(Boolean.TRUE);
	if (mail.getNowMail() == null) {
	    mail.setNowMail(Boolean.FALSE);
	}
	myBatisDAO.insert(ADD_MAIL, mail);

	return mail.getId();
    }

    @Override
    public void updateMail(Mail mail) {
	mail.setUpdateTime(new Date());
	myBatisDAO.update(UPDATE_MAIL, mail);
    }

    @Override
    public void updateMailNowMailFalse(Long accountId) {
	myBatisDAO.update(UPDATE_MAIL_NOW_MAIL_FALSE, accountId);
    }

    @Override
    public void deleteMail(Long id) {
	Mail mail = new Mail();
	mail.setId(id);
	mail.setExpired(Boolean.FALSE);
	myBatisDAO.update(UPDATE_MAIL, mail);
    }

    @Override
    public Mail findMailById(Long id) {
	if (id == null) {
	    return null;
	}
	return (Mail) myBatisDAO.findForObject(FIND_MAIL_BY_ID, id);
    }

    @Override
    public Mail findMailByNowMail(Long accountId) {
	if (accountId == null) {
	    return null;
	}
	return (Mail) myBatisDAO.findForObject(FIND_MAIL_BY_NOW_MAIL, accountId);
    }

    @SuppressWarnings("unchecked")
    @Override
    public Page<Mail> findMailByPage(Long accountId, int pageNum, int pageSize) {
	Map<String, Object> param = new HashMap<String, Object>();
	param.put("accountId", accountId);
	return myBatisDAO.findForPage(FIND_MAIL_BY_PAGE, new PageRequest(pageNum, pageSize, param));
    }

}
