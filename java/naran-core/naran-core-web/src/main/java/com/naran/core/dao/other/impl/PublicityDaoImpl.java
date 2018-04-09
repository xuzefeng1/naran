package com.naran.core.dao.other.impl;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.naran.core.dao.other.PublicityDao;
import com.naran.core.entity.other.Publicity;
import com.naran.foundation.mybatis.dao.MyBatisDAO;
import com.naran.foundation.mybatis.page.Page;
import com.naran.foundation.mybatis.page.PageRequest;

/**
 * 
 * @author zefeng.xu
 */
@Repository
public class PublicityDaoImpl implements PublicityDao {

    private static final String ADD_PUBLICITY = "addPublicity";
    private static final String UPDATE_PUBLICITY = "updatePublicity";
    private static final String FIND_PUBLICITY_BY_ID = "findPublicityById";
    private static final String FIND_PUBLICITY_BY_PAGE = "findPublicityByPage";

    @Autowired
    private MyBatisDAO myBatisDAO;

    @Override
    public Long addPublicity(Publicity publicity) {
	publicity.setCreateTime(new Date());
	publicity.setUpdateTime(new Date());
	publicity.setExpired(Boolean.TRUE);
	myBatisDAO.insert(ADD_PUBLICITY, publicity);
	return publicity.getId();
    }

    @Override
    public void updatePublicity(Publicity publicity) {
	publicity.setUpdateTime(new Date());
	myBatisDAO.update(UPDATE_PUBLICITY, publicity);
    }

    @Override
    public void deletePublicity(Long id) {
	Publicity publicity = new Publicity();
	publicity.setId(id);
	publicity.setExpired(Boolean.FALSE);
	myBatisDAO.update(UPDATE_PUBLICITY, publicity);
    }

    @Override
    public Publicity findPublicityById(Long id) {
	if (id == null) {
	    return null;
	}
	return (Publicity) myBatisDAO.findForObject(FIND_PUBLICITY_BY_ID, id);
    }

    @SuppressWarnings("unchecked")
    @Override
    public Page<Publicity> findPublicityByPage(String publicityType, int pageNum, int pageSize) {
	Map<String, Object> param = new HashMap<String, Object>();
	param.put("publicityType", publicityType);
	return myBatisDAO.findForPage(FIND_PUBLICITY_BY_PAGE, new PageRequest(pageNum, pageSize, param));
    }

}
