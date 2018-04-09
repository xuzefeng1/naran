package com.naran.core.service.impl.other;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.naran.core.dao.other.RankDao;
import com.naran.core.entity.other.Rank;
import com.naran.dubbo.service.other.IRankService;
import com.naran.foundation.mybatis.page.Page;

/**
 * 
 * @author zefeng.xu
 */
@Transactional
@Service("rankService")
public class RankServiceImpl implements IRankService {

    @Autowired
    private RankDao rankDao;

    @Override
    public Long addRank(Rank rank) {
	if (rank == null) {
	    return null;
	}
	return rankDao.addRank(rank);
    }

    @Override
    public void updateRank(Rank rank) {
	if (rank == null || rank.getId() == null) {
	    return;
	}
	rankDao.updateRank(rank);

    }

    @Override
    public void deleteRank(Long id) {
	if (id == null) {
	    return;
	}
	rankDao.deleteRank(id);

    }

    @Override
    public Rank findRankById(Long id) {
	if (id == null) {
	    return null;
	}
	return rankDao.findRankById(id);
    }

    @Override
    public Page<Rank> findRankByPage(String rankType,int pageNum, int pageSize) {
	return rankDao.findRankByPage(rankType,pageNum, pageSize);
    }

}
