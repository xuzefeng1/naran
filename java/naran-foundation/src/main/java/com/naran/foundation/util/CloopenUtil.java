package com.naran.foundation.util;

import java.util.HashMap;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.cloopen.rest.sdk.CCPRestSDK;
import com.naran.foundation.constants.NaranConstant;

/**
 * http://www.yuntongxun.com/member/main
 * 
 * @author zefeng.xu
 */
public class CloopenUtil {

    private static Log logger = LogFactory.getLog(CloopenUtil.class);

    private static String host = "app.cloopen.com";
    private static String port = "8883";
    private static String accountSid = "8a48b551505b4af001505b4bdee90001";
    private static String authToken = "3125f64afe24472abccf56d7e5ca6a31";
    private static String appId = "aaf98f8950189e9b01505b509c652bfd";
    private static CCPRestSDK restAPI;

    static {
	restAPI = new CCPRestSDK();
	restAPI.init(host, port);
	restAPI.setAccount(accountSid, authToken);
	restAPI.setAppId(appId);
    }

    /**
     * 发送短信验证码
     * 
     */
    public String sendVerification(String tel, String code, String serial) {

	HashMap<String, Object> result = restAPI.sendTemplateSMS(tel, NaranConstant.CODE_NUM, new String[] { code, serial, "3" });
	if (logger.isDebugEnabled()) {
	    logger.debug("短信返回信息：=" + result);
	}
	if (!"000000".equals(result.get("statusCode"))) {
	    return "错误码=" + result.get("statusCode") + " 错误信息= " + result.get("statusMsg");
	}
	return null;
    }

    public static void setHost(String host) {
	CloopenUtil.host = host;
    }

    public static void setPort(String port) {
	CloopenUtil.port = port;
    }

    public static void setAccountSid(String accountSid) {
	CloopenUtil.accountSid = accountSid;
    }

    public static void setAuthToken(String authToken) {
	CloopenUtil.authToken = authToken;
    }

    public static void setAppId(String appId) {
	CloopenUtil.appId = appId;
    }

}