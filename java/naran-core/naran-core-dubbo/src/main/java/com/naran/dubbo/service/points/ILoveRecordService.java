package com.naran.dubbo.service.points;

import com.naran.core.entity.points.LoveRecord;
import com.naran.foundation.mybatis.page.Page;

/**
 * 爱心值记录服务接口
 * 
 * @author zefeng.xu
 */
public interface ILoveRecordService {

    Long addLoveRecord(LoveRecord loveRecord);

    void updateLoveRecord(LoveRecord loveRecord);

    void deleteLoveRecord(Long id);

    LoveRecord findLoveRecordById(Long id);

    /**
     * 获取（分页）
     * 
     * @return
     */
    Page<LoveRecord> findLoveRecordByPage(int pageNum, int pageSize);

}
