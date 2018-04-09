package com.naran.dubbo.service.charitable;

import com.naran.core.entity.charitable.CharitableRecord;
import com.naran.foundation.mybatis.page.Page;

/**
 * 慈善记录服务接口
 * 
 * @author zefeng.xu
 */
public interface ICharitableRecordService {

    Long addCharitableRecord(CharitableRecord charitableRecord);

    void updateCharitableRecord(CharitableRecord charitableRecord);

    void deleteCharitableRecord(Long id);

    CharitableRecord findCharitableRecordById(Long id);

    /**
     * 获取（分页）
     * 
     * @return
     */
    Page<CharitableRecord> findCharitableRecordByPage(int pageNum, int pageSize);

}
