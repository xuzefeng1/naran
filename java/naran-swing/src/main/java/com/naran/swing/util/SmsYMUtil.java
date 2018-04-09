package com.naran.swing.util;

import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.naran.swing.entity.message.SmsMessagePO;
import com.naran.swing.entity.message.SmsMessagePO.SendState;
import com.naran.swing.util.sdk.Client;
import com.naran.swing.util.sdk.SingletonClient;

/**
 * 亿美短信发送工具
 * @author QuCeng
 */
public class SmsYMUtil {

    protected static final Logger logger = LoggerFactory.getLogger("SMS_LOGGER");

    public static SmsMessagePO send(SmsMessagePO smsMessage) {
        try {
            // 号码、内容、签名校验
            if (StringUtils.isBlank(smsMessage.getMobile()) 
            		|| StringUtils.isBlank(smsMessage.getContent())) {
                logger.error("缺少短信发送必要的参数，号码：" + smsMessage.getMobile()
                        + "，内容：" + smsMessage.getContent());
                smsMessage.setSendState(SendState.COMMIT_ERROR);
                smsMessage.setSendStateRemark("缺少短信发送必要的参数！");
                return smsMessage;
            }

            // 手机号、短信内容、附加码、优先级（1-5）
            Client client = SingletonClient.getClient();
            int i = client.sendSMS(new String[]{smsMessage.getMobile()}, smsMessage.getContent(), "", 5);
            if (i == 0) {
                smsMessage.setSendState(SendState.COMMIT_SUCCESS);
                smsMessage.setSendStateRemark(getSmsStateRemarkForSend(i));
            } else {
                smsMessage.setSendState(SendState.COMMIT_ERROR);
                smsMessage.setSendStateRemark(getSmsStateRemarkForSend(i));
            }
        } catch (Exception e) {
        	logger.error("提交亿美通道失败！", e);
            smsMessage.setSendState(SendState.COMMIT_ERROR);
            smsMessage.setSendStateRemark("提交亿美通道失败！");
        }
        
        return smsMessage;
    }

    public static String getSmsStateRemarkForSend(int code) {
        switch (code) {
            case 0:
                return "发送成功";
            case -1:
                return "系统异常";
            case -2:
                return "客户端异常";
            case -101:
                return "命令不被支持";
            case -102:
                return "RegistryTransInfo删除信息失败";
            case -103:
                return "RegistryInfo更新信息失败";
            case -104:
                return "请求超过限制";
            case -110:
                return "号码注册激活失败";
            case -111:
                return "企业注册失败";
            case -113:
                return "充值失败";
            case -117:
                return "发送短信失败";
            case -118:
                return "接收MO失败";
            case -119:
                return "接收Report失败";
            case -120:
                return "修改密码失败";
            case -122:
                return "号码注销激活失败";
            case -123:
                return "查询单价失败";
            case -124:
                return "查询余额失败";
            case -125:
                return "设置MO转发失败";
            case -126:
                return "路由信息失败";
            case -127:
                return "计费失败0余额";
            case -128:
                return "计费失败余额不足";
            case -190:
                return "数据操作失败";
            case -1100:
                return "序列号错误，序列号不存在内存中，或尝试攻击的用户";
            case -1102:
                return "序列号密码错误";
            case -1103:
                return "序列号Key错误";
            case -1104:
                return "路由失败，请联系系统管理员";
            case -1105:
                return "注册号状态异常, 未用 1";
            case -1107:
                return "注册号状态异常, 停用 3";
            case -1108:
                return "注册号状态异常, 停止 5";
            case -1131:
                return "充值卡无效";
            case -1132:
                return "充值密码无效";
            case -1133:
                return "充值卡绑定异常";
            case -1134:
                return "充值状态无效";
            case -1135:
                return "充值金额无效";
            case -1901:
                return "数据库插入操作失败";
            case -1902:
                return "数据库更新操作失败";
            case -1903:
                return "数据库删除操作失败";
            case -9000:
                return "数据格式错误,数据超出数据库允许范围";
            case -9001:
                return "序列号格式错误";
            case -9002:
                return "密码格式错误";
            case -9003:
                return "客户端Key格式错误";
            case -9004:
                return "设置转发格式错误";
            case -9005:
                return "公司地址格式错误";
            case -9006:
                return "企业中文名格式错误";
            case -9007:
                return "企业中文名简称格式错误";
            case -9008:
                return "邮件地址格式错误";
            case -9009:
                return "企业英文名格式错误";
            case -9010:
                return "企业英文名简称格式错误";
            case -9011:
                return "传真格式错误";
            case -9012:
                return "联系人格式错误";
            case -9013:
                return "联系电话格式错误";
            case -9014:
                return "邮编格式错误";
            case -9015:
                return "新密码格式错误";
            case -9016:
                return "发送短信包大小超出范围";
            case -9017:
                return "发送短信内容格式错误";
            case -9018:
                return "发送短信扩展号格式错误";
            case -9019:
                return "发送短信优先级格式错误";
            case -9020:
                return "发送短信手机号格式错误";
            case -9021:
                return "发送短信定时时间格式错误";
            case -9022:
                return "发送短信唯一序列值错误";
            case -9023:
                return "充值卡号格式错误";
            case -9024:
                return "充值密码格式错误";
            case -9025:
                return "客户端请求sdk5超时";
            default:
                return "未知";
        }
    }
    
}
