package com.naran.dubbo.service.other;

import com.naran.core.entity.other.Mail;
import com.naran.foundation.mybatis.page.Page;

/**
 * 邮寄地址服务接口
 * 
 * @author zefeng.xu
 */
public interface IMailService {

    Long addMail(Mail mail);

    void updateMail(Mail mail);

    void deleteMail(Long id,Long accountId);

    Mail findMailById(Long id);

    Mail findMailByNowMail(Long accountId);

    /**
     * 获取（分页）
     * 
     * @return
     */
    Page<Mail> findMailByPage(Long accountId, int pageNum, int pageSize);

}
