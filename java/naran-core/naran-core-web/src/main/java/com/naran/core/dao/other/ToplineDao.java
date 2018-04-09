package com.naran.core.dao.other;

import com.naran.core.entity.other.Topline;
import com.naran.foundation.mybatis.page.Page;

/**
 * 那然快报数据访问接口
 * 
 * @author zefeng.xu
 */
public interface ToplineDao {

    Long addTopline(Topline topline);

    void updateTopline(Topline topline);

    void deleteTopline(Long id);

    Topline findToplineById(Long id);

    Page<Topline> findToplineByPage(int pageNum, int pageSize);

}
