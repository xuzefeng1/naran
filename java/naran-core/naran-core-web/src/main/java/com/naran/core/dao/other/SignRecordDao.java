package com.naran.core.dao.other;

import com.naran.core.entity.record.SignRecord;
import com.naran.foundation.mybatis.page.Page;

/**
 * 签到记录数据访问接口
 * 
 * @author zefeng.xu
 */
public interface SignRecordDao {

    Long addSignRecord(SignRecord signRecord);

    void updateSignRecord(SignRecord signRecord);

    void deleteSignRecord(Long id);

    SignRecord findSignRecordById(Long id);

    Page<SignRecord> findSignRecordByPage(Long accountId, String startDate, String endDate,int pageNum, int pageSize);

    SignRecord findSignRecordByLast(Long accountId);

}
