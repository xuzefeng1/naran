package com.naran.dubbo.service.other;

import com.naran.core.entity.other.Rank;
import com.naran.foundation.mybatis.page.Page;

/**
 * 邮寄地址服务接口
 * 
 * @author zefeng.xu
 */
public interface IRankService {

    Long addRank(Rank rank);

    void updateRank(Rank rank);

    void deleteRank(Long id);

    Rank findRankById(Long id);

    /**
     * 获取（分页）
     * 
     * @return
     */
    Page<Rank> findRankByPage(String rankType, int pageNum, int pageSize);

}
