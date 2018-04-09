package com.naran.core.dao.charitable;

import com.naran.core.entity.charitable.CharitableRecord;
import com.naran.foundation.mybatis.page.Page;

/**
 *慈善记录数据访问接口
 * 
 * @author zefeng.xu
 */
public interface CharitableRecordDao {

    Long addCharitableRecord(CharitableRecord charitableRecord);

    void updateCharitableRecord(CharitableRecord charitableRecord);

    void deleteCharitableRecord(Long id);

    CharitableRecord findCharitableRecordById(Long id);

    Page<CharitableRecord> findCharitableRecordByPage(int pageNum, int pageSize);

}
