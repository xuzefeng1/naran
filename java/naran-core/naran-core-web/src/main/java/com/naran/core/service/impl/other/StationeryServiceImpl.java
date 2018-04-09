package com.naran.core.service.impl.other;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.naran.core.dao.other.StationeryDao;
import com.naran.core.entity.other.Stationery;
import com.naran.dubbo.service.other.IStationeryService;
import com.naran.foundation.mybatis.page.Page;

/**
 * 
 * @author zefeng.xu
 */
@Transactional
@Service("stationeryService")
public class StationeryServiceImpl implements IStationeryService {

    @Autowired
    private StationeryDao stationeryDao;

    @Override
    public Long addStationery(Stationery stationery) {
	if (stationery == null) {
	    return null;
	}
	return stationeryDao.addStationery(stationery);
    }

    @Override
    public void updateStationery(Stationery stationery) {
	if (stationery == null || stationery.getId() == null) {
	    return;
	}
	stationeryDao.updateStationery(stationery);

    }

    @Override
    public void deleteStationery(Long id) {
	if (id == null) {
	    return;
	}
	stationeryDao.deleteStationery(id);

    }

    @Override
    public Stationery findStationeryById(Long id) {
	if (id == null) {
	    return null;
	}
	return stationeryDao.findStationeryById(id);
    }

    @Override
    public Page<Stationery> findStationeryByPage(int pageNum, int pageSize) {

	return stationeryDao.findStationeryByPage(pageNum, pageSize);
    }

}
