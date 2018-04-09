package com.naran.core.dao.record;

import com.naran.core.entity.record.CollectionRecord;
import com.naran.foundation.mybatis.page.Page;

/**
 * 收藏记录数据访问接口
 * 
 * @author zefeng.xu
 */
public interface CollectionRecordDao {

    Long addCollectionRecord(CollectionRecord collectionRecord);

    void updateCollectionRecord(CollectionRecord collectionRecord);

    void deleteCollectionRecord(Long id);

    void deleteCollectionRecordByAccount(Long businessId, String businessType, Long accountId);

    CollectionRecord findCollectionRecordById(Long id);

    CollectionRecord findCollectionRecordByAccount(Long businessId, String businessType, Long accountId);

    Page<CollectionRecord> findCollectionRecordByPage(String businessType, String secondType, Long accountId, int pageNum, int pageSize);

}
