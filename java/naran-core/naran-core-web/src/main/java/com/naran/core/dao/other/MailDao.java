package com.naran.core.dao.other;

import com.naran.core.entity.other.Mail;
import com.naran.foundation.mybatis.page.Page;

/**
 * 邮寄地址数据访问接口
 * 
 * @author zefeng.xu
 */
public interface MailDao {

    Long addMail(Mail mail);

    void updateMail(Mail mail);
    
    void updateMailNowMailFalse(Long accountId);

    void deleteMail(Long id);

    Mail findMailById(Long id);
    
    Mail findMailByNowMail(Long accountId);

    Page<Mail> findMailByPage(Long accountId,int pageNum, int pageSize);

    



}
