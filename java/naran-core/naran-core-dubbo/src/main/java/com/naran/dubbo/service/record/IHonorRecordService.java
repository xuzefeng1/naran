package com.naran.dubbo.service.record;

import com.naran.core.entity.record.HonorRecord;
import com.naran.foundation.mybatis.page.Page;

/**
 * 那然荣誉服务接口
 * 
 * @author zefeng.xu
 */
public interface IHonorRecordService {

    Long addHonorRecord(HonorRecord honorRecord);

    void updateHonorRecord(HonorRecord honorRecord);

    void deleteHonorRecord(Long id);

    HonorRecord findHonorRecordById(Long id);

    /**
     * 获取（分页）
     * 
     * @return
     */
    Page<HonorRecord> findHonorRecordByPage(int pageNum, int pageSize);

}
