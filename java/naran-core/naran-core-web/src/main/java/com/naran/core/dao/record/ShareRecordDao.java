package com.naran.core.dao.record;

import com.naran.core.entity.record.ShareRecord;
import com.naran.foundation.mybatis.page.Page;

/**
 * 分享记录数据访问接口
 * 
 * @author zefeng.xu
 */
public interface ShareRecordDao {

    Long addShareRecord(ShareRecord shareRecord);

    void updateShareRecord(ShareRecord shareRecord);

    void deleteShareRecord(Long id);

    ShareRecord findShareRecordById(Long id);

    ShareRecord findShareRecordByRecord(Long accountId, Long businessId, String businessType);

    Page<ShareRecord> findShareRecordByPage(int pageNum, int pageSize);

}
