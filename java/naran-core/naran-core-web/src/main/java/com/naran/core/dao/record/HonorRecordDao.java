package com.naran.core.dao.record;

import com.naran.core.entity.record.HonorRecord;
import com.naran.foundation.mybatis.page.Page;

/**
 * 那然荣誉数据访问接口
 * 
 * @author zefeng.xu
 */
public interface HonorRecordDao {

    Long addHonorRecord(HonorRecord honorRecord);

    void updateHonorRecord(HonorRecord honorRecord);

    void deleteHonorRecord(Long id);

    HonorRecord findHonorRecordById(Long id);

    Page<HonorRecord> findHonorRecordByPage(int pageNum, int pageSize);

}
