package com.naran.core.dao.charitable.impl;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.naran.core.dao.charitable.CharitarianDao;
import com.naran.core.entity.charitable.Charitarian;
import com.naran.foundation.mybatis.dao.MyBatisDAO;
import com.naran.foundation.mybatis.page.Page;
import com.naran.foundation.mybatis.page.PageRequest;

/**
 * 
 * @author zefeng.xu
 */
@Repository
public class CharitarianDaoImpl implements CharitarianDao {

    private static final String ADD_CHARITARIAN = "addCharitarian";
    private static final String UPDATE_CHARITARIAN = "updateCharitarian";
    private static final String FIND_CHARITARIAN_BY_ID = "findCharitarianById";
    private static final String FIND_CHARITARIAN_BY_PAGE = "findCharitarianByPage";

    @Autowired
    private MyBatisDAO myBatisDAO;

    @Override
    public Long addCharitarian(Charitarian charitarian) {
	charitarian.setCreateTime(new Date());
	charitarian.setUpdateTime(new Date());
	charitarian.setExpired(Boolean.TRUE);
	myBatisDAO.insert(ADD_CHARITARIAN, charitarian);

	return charitarian.getId();
    }

    @Override
    public void updateCharitarian(Charitarian charitarian) {
	charitarian.setUpdateTime(new Date());
	myBatisDAO.update(UPDATE_CHARITARIAN, charitarian);
    }

    @Override
    public void deleteCharitarian(Long id) {
	Charitarian charitarian = new Charitarian();
	charitarian.setId(id);
	charitarian.setExpired(Boolean.FALSE);
	myBatisDAO.update(UPDATE_CHARITARIAN, charitarian);
    }

    @Override
    public Charitarian findCharitarianById(Long id) {
	if (id == null) {
	    return null;
	}
	return (Charitarian) myBatisDAO.findForObject(FIND_CHARITARIAN_BY_ID, id);
    }

    @SuppressWarnings("unchecked")
    @Override
    public Page<Charitarian> findCharitarianByPage(int pageNum, int pageSize) {
	Map<String, Object> param = new HashMap<String, Object>();
	return myBatisDAO.findForPage(FIND_CHARITARIAN_BY_PAGE, new PageRequest(pageNum, pageSize, param));
    }

}
