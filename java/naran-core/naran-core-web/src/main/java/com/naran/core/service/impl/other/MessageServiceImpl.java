package com.naran.core.service.impl.other;

import java.text.DecimalFormat;
import java.util.Random;
import java.util.concurrent.TimeUnit;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.naran.dubbo.service.other.IMessageService;
import com.naran.foundation.constants.NaranConstant;
import com.naran.foundation.dto.SmsMessageCategory;
import com.naran.foundation.util.CloopenUtil;
import com.naran.foundation.util.StringUtil;

/**
 * 
 * @author zefeng.xu
 */
@Transactional
@Service("messageService")
public class MessageServiceImpl implements IMessageService {

    @Autowired
    private RedisTemplate<Object, Object> redisTemplate;

    @Override
    public String getVerifyCode(String phone, SmsMessageCategory category) {
	return (String) redisTemplate.opsForValue().get(category + phone);
    }

    @Override
    public String generateVerifyCode(String phone, SmsMessageCategory category, int i) {
	String code = (String) redisTemplate.opsForValue().get(category + phone);
	if (StringUtil.isBlank(code)) {
	    code = new DecimalFormat("0000").format(new Random(System.currentTimeMillis()).nextInt(10000));
	}
	redisTemplate.opsForValue().set(category + phone, code, NaranConstant.DEFAULT_CODE_VALID_SECOND, TimeUnit.SECONDS);
	CloopenUtil cloopen = new CloopenUtil();
	cloopen.sendVerification(phone, code, Integer.toString(i));
	return code;
    }

}
