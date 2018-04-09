package com.naran.dubbo.service.other;

import com.naran.core.entity.other.Stationery;
import com.naran.foundation.mybatis.page.Page;

/**
 * 那然快报服务接口
 * 
 * @author zefeng.xu
 */
public interface IStationeryService {

    Long addStationery(Stationery stationery);

    void updateStationery(Stationery stationery);

    void deleteStationery(Long id);

    Stationery findStationeryById(Long id);

    /**
     * 获取（分页）
     * 
     * @return
     */
    Page<Stationery> findStationeryByPage(int pageNum, int pageSize);

}
