package com.naran.core.dao.points;

import com.naran.core.entity.points.Level;
import com.naran.foundation.mybatis.page.Page;

/**
 * 那然等级数据访问接口
 * 
 * @author zefeng.xu
 */
public interface LevelDao {

    Long addLevel(Level level);

    void updateLevel(Level level);

    void deleteLevel(Long id);

    Level findLevelById(Long id);

    Page<Level> findLevelByPage(int pageNum, int pageSize);

}
