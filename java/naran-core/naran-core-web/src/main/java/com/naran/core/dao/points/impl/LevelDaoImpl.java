package com.naran.core.dao.points.impl;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.naran.core.dao.points.LevelDao;
import com.naran.core.entity.points.Level;
import com.naran.foundation.mybatis.dao.MyBatisDAO;
import com.naran.foundation.mybatis.page.Page;
import com.naran.foundation.mybatis.page.PageRequest;

/**
 * 
 * @author zefeng.xu
 */
@Repository
public class LevelDaoImpl implements LevelDao {

    private static final String ADD_LEVEL = "addLevel";
    private static final String UPDATE_LEVEL = "updateLevel";
    private static final String FIND_LEVEL_BY_ID = "findLevelById";
    private static final String FIND_LEVEL_BY_PAGE = "findLevelByPage";

    @Autowired
    private MyBatisDAO myBatisDAO;

    @Override
    public Long addLevel(Level level) {
	level.setCreateTime(new Date());
	level.setUpdateTime(new Date());
	level.setExpired(Boolean.TRUE);
	myBatisDAO.insert(ADD_LEVEL, level);
	return level.getId();
    }

    @Override
    public void updateLevel(Level level) {
	level.setUpdateTime(new Date());
	myBatisDAO.update(UPDATE_LEVEL, level);
    }

    @Override
    public void deleteLevel(Long id) {
	Level level = new Level();
	level.setId(id);
	level.setExpired(Boolean.FALSE);
	myBatisDAO.update(UPDATE_LEVEL, level);
    }

    @Override
    public Level findLevelById(Long id) {
	if (id == null) {
	    return null;
	}
	return (Level) myBatisDAO.findForObject(FIND_LEVEL_BY_ID, id);
    }

    @SuppressWarnings("unchecked")
    @Override
    public Page<Level> findLevelByPage(int pageNum, int pageSize) {
	Map<String, Object> param = new HashMap<String, Object>();
	return myBatisDAO.findForPage(FIND_LEVEL_BY_PAGE, new PageRequest(pageNum, pageSize, param));
    }

}
