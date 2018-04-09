package com.naran.dubbo.service.points;

import com.naran.core.entity.points.LevelRecord;
import com.naran.foundation.mybatis.page.Page;

/**
 * 等级记录服务接口
 * 
 * @author zefeng.xu
 */
public interface ILevelRecordService {

    Long addLevelRecord(LevelRecord levelRecord);

    void updateLevelRecord(LevelRecord levelRecord);

    void deleteLevelRecord(Long id);

    LevelRecord findLevelRecordById(Long id);

    /**
     * 获取（分页）
     * 
     * @return
     */
    Page<LevelRecord> findLevelRecordByPage(int pageNum, int pageSize);

}
