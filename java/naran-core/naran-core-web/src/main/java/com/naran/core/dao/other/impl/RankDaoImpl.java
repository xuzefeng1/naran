package com.naran.core.dao.other.impl;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.naran.core.dao.other.RankDao;
import com.naran.core.entity.other.Rank;
import com.naran.foundation.mybatis.dao.MyBatisDAO;
import com.naran.foundation.mybatis.page.Page;
import com.naran.foundation.mybatis.page.PageRequest;

/**
 * 
 * @author zefeng.xu
 */
@Repository
public class RankDaoImpl implements RankDao {

    private static final String ADD_RANK = "addRank";
    private static final String UPDATE_RANK = "updateRank";
    private static final String FIND_RANK_BY_ID = "findRankById";
    private static final String FIND_RANK_BY_PAGE = "findRankByPage";

    @Autowired
    private MyBatisDAO myBatisDAO;

    @Override
    public Long addRank(Rank rank) {
	rank.setCreateTime(new Date());
	rank.setUpdateTime(new Date());
	rank.setExpired(Boolean.TRUE);
	myBatisDAO.insert(ADD_RANK, rank);
	return rank.getId();
    }

    @Override
    public void updateRank(Rank rank) {
	rank.setUpdateTime(new Date());
	myBatisDAO.update(UPDATE_RANK, rank);
    }

    @Override
    public void deleteRank(Long id) {
	Rank rank = new Rank();
	rank.setId(id);
	rank.setExpired(Boolean.FALSE);
	myBatisDAO.update(UPDATE_RANK, rank);
    }

    @Override
    public Rank findRankById(Long id) {
	if (id == null) {
	    return null;
	}
	return (Rank) myBatisDAO.findForObject(FIND_RANK_BY_ID, id);
    }

    @SuppressWarnings("unchecked")
    @Override
    public Page<Rank> findRankByPage(String rankType, int pageNum, int pageSize) {
	Map<String, Object> param = new HashMap<String, Object>();
	param.put("rankType", rankType);
	return myBatisDAO.findForPage(FIND_RANK_BY_PAGE, new PageRequest(pageNum, pageSize, param));
    }

}
