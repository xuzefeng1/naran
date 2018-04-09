package com.naran.dubbo.service.record;

import com.naran.core.entity.record.CollectionRecord;
import com.naran.dubbo.response.DubboResponse;
import com.naran.foundation.mybatis.page.Page;

/**
 * 收藏记录服务接口
 * 
 * @author zefeng.xu
 */
public interface ICollectionRecordService {

    DubboResponse<String> addCollectionByOrder(Long accountId, Long orderId);

    DubboResponse<String> addCollectionByContent(Long accountId, Long contentId);

    void deleteCollectionByAccount(Long businessId, String businessType, Long accountId);

    CollectionRecord findCollectionByAccount(Long businessId, String businessType, Long accountId);

    Page<CollectionRecord> findCollectionByPage(String businessType, String secondType, Long accountId, int pageNum, int pageSize);

}
