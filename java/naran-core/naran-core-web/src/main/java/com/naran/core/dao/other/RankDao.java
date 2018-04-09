package com.naran.core.dao.other;

import com.naran.core.entity.other.Rank;
import com.naran.foundation.mybatis.page.Page;

/**
 * 排行榜数据访问接口
 * 
 * @author zefeng.xu
 */
public interface RankDao {

    Long addRank(Rank rank);

    void updateRank(Rank rank);

    void deleteRank(Long id);

    Rank findRankById(Long id);

    Page<Rank> findRankByPage(String rankType,int pageNum, int pageSize);

}
