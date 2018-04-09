package com.naran.dubbo.service.charitable;

import com.naran.core.entity.charitable.Charitarian;
import com.naran.foundation.mybatis.page.Page;

/**
 * 慈善家服务接口
 * 
 * @author zefeng.xu
 */
public interface ICharitarianService {

    Long addCharitarian(Charitarian charitarian);

    void updateCharitarian(Charitarian charitarian);

    void deleteCharitarian(Long id);

    Charitarian findCharitarianById(Long id);

    /**
     * 获取（分页）
     * 
     * @return
     */
    Page<Charitarian> findCharitarianByPage(int pageNum, int pageSize);

}
