package com.naran.dubbo.service.other;

import com.naran.foundation.dto.SmsMessageCategory;

/**
 * 信息服务接口
 * 
 * @author zefeng.xu
 */
public interface IMessageService {

    String getVerifyCode(String phone, SmsMessageCategory forgetPasswordVerifyCode);

    String generateVerifyCode(String phone, SmsMessageCategory forgetPasswordVerifyCode, int i);

}
