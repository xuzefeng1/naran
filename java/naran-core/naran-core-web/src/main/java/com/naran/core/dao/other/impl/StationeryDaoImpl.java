package com.naran.core.dao.other.impl;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.naran.core.dao.other.StationeryDao;
import com.naran.core.entity.other.Stationery;
import com.naran.foundation.mybatis.dao.MyBatisDAO;
import com.naran.foundation.mybatis.page.Page;
import com.naran.foundation.mybatis.page.PageRequest;

/**
 * 
 * @author zefeng.xu
 */
@Repository
public class StationeryDaoImpl implements StationeryDao {

    private static final String ADD_STATIONERY = "addStationery";
    private static final String UPDATE_STATIONERY = "updateStationery";
    private static final String FIND_STATIONERY_BY_ID = "findStationeryById";
    private static final String FIND_STATIONERY_BY_PAGE = "findStationeryByPage";

    @Autowired
    private MyBatisDAO myBatisDAO;

    @Override
    public Long addStationery(Stationery stationery) {
	stationery.setCreateTime(new Date());
	stationery.setUpdateTime(new Date());
	stationery.setExpired(Boolean.TRUE);
	myBatisDAO.insert(ADD_STATIONERY, stationery);
	return stationery.getId();
    }

    @Override
    public void updateStationery(Stationery stationery) {
	stationery.setUpdateTime(new Date());
	myBatisDAO.update(UPDATE_STATIONERY, stationery);
    }

    @Override
    public void deleteStationery(Long id) {
	Stationery stationery = new Stationery();
	stationery.setId(id);
	stationery.setExpired(Boolean.FALSE);
	myBatisDAO.update(UPDATE_STATIONERY, stationery);
    }

    @Override
    public Stationery findStationeryById(Long id) {
	if (id == null) {
	    return null;
	}
	return (Stationery) myBatisDAO.findForObject(FIND_STATIONERY_BY_ID, id);
    }

    @SuppressWarnings("unchecked")
    @Override
    public Page<Stationery> findStationeryByPage(int pageNum, int pageSize) {
	Map<String, Object> param = new HashMap<String, Object>();
	return myBatisDAO.findForPage(FIND_STATIONERY_BY_PAGE, new PageRequest(pageNum, pageSize, param));
    }

}
