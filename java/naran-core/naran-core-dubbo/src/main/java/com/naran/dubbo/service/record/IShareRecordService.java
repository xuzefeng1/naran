package com.naran.dubbo.service.record;

import com.naran.dubbo.response.DubboResponse;

/**
 * 分享记录服务接口
 * 
 * @author zefeng.xu
 */
public interface IShareRecordService {

    DubboResponse<String> shareOrder(Long accountId, Long orderId);

    DubboResponse<String> shareContent(Long accountId, Long contentId);

    DubboResponse<String> shareActivity(Long accountId, Long activityId);

}
