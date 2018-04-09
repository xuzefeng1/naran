package com.naran.swing.util;

import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.lechuu.foundation.spring.adapter.PropertyHolder;
import com.lechuu.foundation.util.StringUtil;
import com.naran.swing.dao.SmsMessageDao;
import com.naran.swing.entity.message.SmsMessagePO;
import com.naran.swing.entity.message.SmsMessagePO.SendState;
import com.taobao.api.DefaultTaobaoClient;
import com.taobao.api.TaobaoClient;
import com.taobao.api.internal.tmc.Message;
import com.taobao.api.internal.tmc.MessageHandler;
import com.taobao.api.internal.tmc.MessageStatus;
import com.taobao.api.internal.tmc.TmcClient;
import com.taobao.api.request.AlibabaAliqinFcSmsNumSendRequest;
import com.taobao.api.response.AlibabaAliqinFcSmsNumSendResponse;
import com.taobao.top.link.LinkException;

/**
 * 阿里大鱼短信发送工具
 * @author QuCeng
 */
@Component
public class SmsALDYUtil {

    protected static final Logger logger = LoggerFactory.getLogger("SMS_LOGGER");

	private static final String ALDY_COMMIT_URL = "http://gw.api.taobao.com/router/rest";
	private static final String STATUS_LISTENER_URL = "ws://mc.api.taobao.com/";
	private static final String APP_KEY = "23265815";
	private static final String SECRET = "e55cd03772ab2926373fce781272d3b5";
	
	private static final String EXTEND = "123456";
	private static final String SMS_TYPE = "normal";
	private static final String SMS_SIGN = "智蔬采配";
	
	@Autowired
	private SmsMessageDao smsMessageDao;
	
	public static SmsMessagePO send(SmsMessagePO smsMessage) {
        try {
            // 号码、内容
            if (StringUtils.isBlank(smsMessage.getMobile()) 
            		|| StringUtils.isBlank(smsMessage.getAlidayuTemplateId())) {
                logger.error("缺少短信发送必要的参数！");
                smsMessage.setSendState(SendState.COMMIT_ERROR);
                smsMessage.setSendStateRemark("缺少短信发送必要的参数！");
                return smsMessage;
            }

            TaobaoClient client = new DefaultTaobaoClient(ALDY_COMMIT_URL, APP_KEY, SECRET);
    		
    		AlibabaAliqinFcSmsNumSendRequest req = new AlibabaAliqinFcSmsNumSendRequest();
    		req.setExtend(EXTEND);
    		req.setSmsType(SMS_TYPE);
    		req.setSmsFreeSignName(SMS_SIGN);
    		req.setRecNum(smsMessage.getMobile());
    		req.setSmsTemplateCode(smsMessage.getAlidayuTemplateId());
    		
    		if (StringUtil.isNotBlank(smsMessage.getValues())) {
    			req.setSmsParam(smsMessage.getValues());
    		}
    		
    		AlibabaAliqinFcSmsNumSendResponse rsp = client.execute(req);
            if (null != rsp && null != rsp.getResult() && rsp.getResult().getSuccess()) {
            	smsMessage.setAlidayuSmsId(rsp.getResult().getModel());
            	smsMessage.setSendState(SendState.COMMIT_SUCCESS);
                smsMessage.setSendStateRemark(rsp.getBody());
            } else {
                smsMessage.setSendState(SendState.COMMIT_ERROR);
                
                String remark = null;
                if (null != rsp) {
                	remark = rsp.getBody();
                } else {
                	remark = "阿里大鱼响应失败！";
                }
                smsMessage.setSendStateRemark(remark);
            }
        } catch (Exception e) {
        	logger.error("提交阿里大鱼通道失败！", e);
        	
            smsMessage.setSendState(SendState.COMMIT_ERROR);
            smsMessage.setSendStateRemark("提交阿里大鱼通道失败！");
        }
        
        return smsMessage;
    }
    
    public void syncStatus() {
    	TmcClient client = new TmcClient(STATUS_LISTENER_URL, APP_KEY, SECRET, "default"); // 关于default消息分组参见：消息分组介绍 
        client.setMessageHandler(new MessageHandler() {
        	@Override
            public void onMessage(Message message, MessageStatus status) {  
                try {  
                	String content = message.getContent();
                	if (StringUtil.isBlank(content)) {
                		return;
                	}
                	
                	JSONObject obj = JSON.parseObject(content);
                	if (null == obj) {
                		return;
                	}
                	
                	String bizId = obj.getString("biz_id");
                	String state = obj.getString("state");
                	String errCode = obj.getString("err_code");
                	
                	updateSmsStatus(bizId, state, errCode);
                } catch (Exception e) {  
                    e.printStackTrace();  
                    status.fail(); // 消息处理失败回滚，服务端需要重发  
                }  
            }
        }); 
        
        try {
			client.connect();
		} catch (LinkException e) {
			e.printStackTrace();
		}
    }
    
    // 更新短信发送状态
    private void updateSmsStatus(String bizId, String state, String errCode) {
    	if (StringUtil.isBlank(bizId) || StringUtil.isBlank(state)) {
    		return;
    	}
    	
    	SmsMessagePO message = smsMessageDao.findSmsMessageByAlidayuSmsId(bizId);
    	if (null == message) {
    		return;
    	}
    	
    	if ("1".equalsIgnoreCase(state)) {
    		// 短信发送成功
    		message.setSendState(SendState.SEND_SUCCESS);
    		message.setSendStateRemark(message.getSendStateRemark() + SendState.SEND_SUCCESS.getChinese());
    	} else {
    		// 短信发送失败
    		message.setSendState(SendState.SEND_ERROR);
    		message.setSendStateRemark(message.getSendStateRemark() + "," + SendState.SEND_ERROR.getChinese() + ",原因：" + errCode);
    	}
    	
    	smsMessageDao.updateSmsMessage(message);
    }
    
    /*public static SmsMessagePO sendCall(SmsMessagePO smsMessage) {
        try {
            // 号码、内容
            if (StringUtils.isBlank(smsMessage.getMobile()) 
            		|| StringUtils.isBlank(smsMessage.getAlidayuTemplateId())) {
                logger.error("缺少短信发送必要的参数！");
                smsMessage.setSendState(SendState.SEND_ERROR);
                smsMessage.setSendStateRemark("缺少短信发送必要的参数！");
                return smsMessage;
            }

            TaobaoClient client = new DefaultTaobaoClient(URL, APP_KEY, SECRET);
    		AlibabaAliqinFcTtsNumSinglecallRequest req = new AlibabaAliqinFcTtsNumSinglecallRequest();
    		req.setExtend(EXTEND);
    		req.setTtsParam("{\"product\":\"123456\",\"code\":\"123456\"}");
    		req.setCalledNum(smsMessage.getMobile());
    		req.setCalledShowNum("4008065035");
    		req.setTtsCode("TTS_950138");
    		
    		if (StringUtil.isNotBlank(smsMessage.getValues())) {
    			req.setSmsParam(smsMessage.getValues());
    		}
    		
    		AlibabaAliqinFcTtsNumSinglecallResponse rsp = client.execute(req);
            if (null != rsp && null != rsp.getResult() && rsp.getResult().getSuccess()) {
                smsMessage.setSendState(SendState.SEND_SUCCESS);
                smsMessage.setSendStateRemark(rsp.getBody());
            } else {
                smsMessage.setSendState(SendState.SEND_ERROR);
                
                String remark = null;
                if (null != rsp) {
                	remark = rsp.getBody();
                } else {
                	remark = "阿里大鱼响应失败！";
                }
                smsMessage.setSendStateRemark(remark);
            }
        } catch (Exception e) {
        	logger.error("阿里大鱼通道发送失败！", e);
        	
            smsMessage.setSendState(SendState.SEND_ERROR);
            smsMessage.setSendStateRemark("阿里大鱼通道发送失败！");
        }
        
        return smsMessage;
    }*/
    
	public static void main(String[] args) throws LinkException {
		// 短信
		/*TaobaoClient client = new DefaultTaobaoClient(URL, APP_KEY, SECRET);
		
		AlibabaAliqinFcSmsNumSendRequest req = new AlibabaAliqinFcSmsNumSendRequest();
		req.setExtend(EXTEND);
		req.setSmsType(SMS_TYPE);
		req.setSmsFreeSignName(SMS_SIGN);
//		req.setSmsParam("{\"verifyCode\":\"123456\"}");
		req.setRecNum("15990143902");
		req.setSmsTemplateCode("SMS_1075058");
		
		AlibabaAliqinFcSmsNumSendResponse rsp;
		try {
			rsp = client.execute(req);
			System.out.println(rsp.getBody());
		} catch (ApiException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}*/
		
		// 文字转语音
		/*TaobaoClient client = new DefaultTaobaoClient(URL, APP_KEY, SECRET);
		AlibabaAliqinFcTtsNumSinglecallRequest req = new AlibabaAliqinFcTtsNumSinglecallRequest();
		req.setExtend(EXTEND);
		req.setTtsParam("{\"product\":\"123456\",\"code\":\"123456\"}");
		req.setCalledNum("15990143902");
		req.setCalledShowNum("4008065035");
		req.setTtsCode("TTS_950138");
		AlibabaAliqinFcTtsNumSinglecallResponse rsp;
		try {
			rsp = client.execute(req);
			System.out.println(rsp.getBody());
		} catch (ApiException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}*/
		
//		Map<String, Object> values = new HashMap<String, Object>();
//		
//		values.put("verifyCode", "123456");
//		System.out.println(JSON.toJSONString(values, SerializerFeature.UseSingleQuotes));
	}
	
}
