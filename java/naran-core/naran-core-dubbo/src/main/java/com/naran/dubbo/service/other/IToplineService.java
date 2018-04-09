package com.naran.dubbo.service.other;

import com.naran.core.entity.other.Topline;
import com.naran.foundation.mybatis.page.Page;

/**
 * 那然快报服务接口
 * 
 * @author zefeng.xu
 */
public interface IToplineService {

    Long addTopline(Topline topline);

    void updateTopline(Topline topline);

    void deleteTopline(Long id);

    Topline findToplineById(Long id);

    /**
     * 获取（分页）
     * 
     * @return
     */
    Page<Topline> findToplineByPage(int pageNum, int pageSize);

}
