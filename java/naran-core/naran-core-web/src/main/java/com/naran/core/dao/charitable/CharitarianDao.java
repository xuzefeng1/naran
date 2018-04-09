package com.naran.core.dao.charitable;

import com.naran.core.entity.charitable.Charitarian;
import com.naran.foundation.mybatis.page.Page;

/**
 * 慈善家数据访问接口
 * 
 * @author zefeng.xu
 */
public interface CharitarianDao {

    Long addCharitarian(Charitarian charitarian);

    void updateCharitarian(Charitarian charitarian);

    void deleteCharitarian(Long id);

    Charitarian findCharitarianById(Long id);

    Page<Charitarian> findCharitarianByPage(int pageNum, int pageSize);

}
