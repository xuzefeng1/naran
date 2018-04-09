package com.naran.core.dao.other.impl;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.naran.core.dao.other.ToplineDao;
import com.naran.core.entity.other.Topline;
import com.naran.foundation.mybatis.dao.MyBatisDAO;
import com.naran.foundation.mybatis.page.Page;
import com.naran.foundation.mybatis.page.PageRequest;

/**
 * 
 * @author zefeng.xu
 */
@Repository
public class ToplineDaoImpl implements ToplineDao {

    private static final String ADD_TOPLINE = "addTopline";
    private static final String UPDATE_TOPLINE = "updateTopline";
    private static final String FIND_TOPLINE_BY_ID = "findToplineById";
    private static final String FIND_TOPLINE_BY_PAGE = "findToplineByPage";

    @Autowired
    private MyBatisDAO myBatisDAO;

    @Override
    public Long addTopline(Topline topline) {
	topline.setCreateTime(new Date());
	topline.setUpdateTime(new Date());
	topline.setExpired(Boolean.TRUE);
	myBatisDAO.insert(ADD_TOPLINE, topline);
	return topline.getId();
    }

    @Override
    public void updateTopline(Topline topline) {
	topline.setUpdateTime(new Date());
	myBatisDAO.update(UPDATE_TOPLINE, topline);
    }

    @Override
    public void deleteTopline(Long id) {
	Topline topline = new Topline();
	topline.setId(id);
	topline.setExpired(Boolean.FALSE);
	myBatisDAO.update(UPDATE_TOPLINE, topline);
    }

    @Override
    public Topline findToplineById(Long id) {
	if (id == null) {
	    return null;
	}
	return (Topline) myBatisDAO.findForObject(FIND_TOPLINE_BY_ID, id);
    }

    @SuppressWarnings("unchecked")
    @Override
    public Page<Topline> findToplineByPage(int pageNum, int pageSize) {
	Map<String, Object> param = new HashMap<String, Object>();
	return myBatisDAO.findForPage(FIND_TOPLINE_BY_PAGE, new PageRequest(pageNum, pageSize, param));
    }

}
