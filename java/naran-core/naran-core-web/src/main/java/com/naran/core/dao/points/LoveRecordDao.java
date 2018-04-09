package com.naran.core.dao.points;

import com.naran.core.entity.points.LoveRecord;
import com.naran.foundation.mybatis.page.Page;

/**
 * 爱心值记录数据访问接口
 * 
 * @author zefeng.xu
 */
public interface LoveRecordDao {

    Long addLoveRecord(LoveRecord loveRecord);

    void updateLoveRecord(LoveRecord loveRecord);

    void deleteLoveRecord(Long id);

    LoveRecord findLoveRecordById(Long id);

    Page<LoveRecord> findLoveRecordByPage(int pageNum, int pageSize);

}
