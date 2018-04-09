package com.naran.core.dao.other;

import com.naran.core.entity.other.Publicity;
import com.naran.foundation.mybatis.page.Page;

/**
 * 轮播图数据访问接口
 * 
 * @author zefeng.xu
 */
public interface PublicityDao {

    Long addPublicity(Publicity publicity);

    void updatePublicity(Publicity publicity);
    
    void deletePublicity(Long id);

    Publicity findPublicityById(Long id);

    Page<Publicity> findPublicityByPage(String publicityType, int pageNum, int pageSize);

}
