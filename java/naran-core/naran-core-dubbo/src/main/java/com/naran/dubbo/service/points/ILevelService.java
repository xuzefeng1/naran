package com.naran.dubbo.service.points;

import com.naran.core.entity.points.Level;
import com.naran.foundation.mybatis.page.Page;

/**
 * 那然等级服务接口
 * 
 * @author zefeng.xu
 */
public interface ILevelService {

    Long addLevel(Level level);

    void updateLevel(Level level);

    void deleteLevel(Long id);

    Level findLevelById(Long id);

    /**
     * 获取（分页）
     * 
     * @return
     */
    Page<Level> findLevelByPage(int pageNum, int pageSize);

}
