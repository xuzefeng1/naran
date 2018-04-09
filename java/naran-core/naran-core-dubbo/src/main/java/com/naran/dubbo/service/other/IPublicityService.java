package com.naran.dubbo.service.other;

import com.naran.core.entity.other.Publicity;
import com.naran.foundation.mybatis.page.Page;

/**
 * 轮播图服务接口
 * 
 * @author zefeng.xu
 */
public interface IPublicityService {

    Long addPublicity(Publicity publicity);

    void updatePublicity(Publicity publicity);

    void deletePublicity(Long id);

    Publicity findPublicityById(Long id);

    /**
     * 获取（分页）
     * 
     * @param publicityType
     * @return
     */
    Page<Publicity> findPublicityByPage(String publicityType, int pageNum, int pageSize);

}
