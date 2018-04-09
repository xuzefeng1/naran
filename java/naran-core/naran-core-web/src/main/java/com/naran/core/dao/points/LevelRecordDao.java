package com.naran.core.dao.points;

import com.naran.core.entity.points.LevelRecord;
import com.naran.foundation.mybatis.page.Page;

/**
 * 等级记录数据访问接口
 * 
 * @author zefeng.xu
 */
public interface LevelRecordDao {

    Long addLevelRecord(LevelRecord levelRecord);

    void updateLevelRecord(LevelRecord levelRecord);

    void deleteLevelRecord(Long id);

    LevelRecord findLevelRecordById(Long id);

    Page<LevelRecord> findLevelRecordByPage(int pageNum, int pageSize);

}
