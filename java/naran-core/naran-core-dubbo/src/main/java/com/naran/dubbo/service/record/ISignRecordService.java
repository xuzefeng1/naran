package com.naran.dubbo.service.record;

import com.naran.core.entity.record.SignRecord;
import com.naran.foundation.mybatis.page.Page;

/**
 * 签到记录服务接口
 * 
 * @author zefeng.xu
 */
public interface ISignRecordService {

    Long addSignRecord(SignRecord signRecord);

    void updateSignRecord(SignRecord signRecord);

    void deleteSignRecord(Long id);

    SignRecord findSignRecordById(Long id);

    /**
     * 获取（分页）
     * 
     * @return
     */
    Page<SignRecord> findSignRecordByPage(Long accountId, String startDate, String endDate, int pageNum, int pageSize);

    SignRecord findSignRecordByLast(Long accountId);

}
