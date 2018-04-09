package com.naran.core.dao.other;

import com.naran.core.entity.other.Stationery;
import com.naran.foundation.mybatis.page.Page;

/**
 * 数据访问接口
 * 
 * @author zefeng.xu
 */
public interface StationeryDao {

    Long addStationery(Stationery stationery);

    void updateStationery(Stationery stationery);

    void deleteStationery(Long id);

    Stationery findStationeryById(Long id);

    Page<Stationery> findStationeryByPage(int pageNum, int pageSize);

}
