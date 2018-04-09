package com.naran.core.service.impl.other;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.naran.core.dao.other.ToplineDao;
import com.naran.core.entity.other.Topline;
import com.naran.dubbo.service.other.IToplineService;
import com.naran.foundation.mybatis.page.Page;

/**
 * 
 * @author zefeng.xu
 */
@Transactional
@Service("toplineService")
public class ToplineServiceImpl implements IToplineService {

    @Autowired
    private ToplineDao toplineDao;

    @Override
    public Long addTopline(Topline topline) {
	if (topline == null) {
	    return null;
	}
	return toplineDao.addTopline(topline);
    }

    @Override
    public void updateTopline(Topline topline) {
	if (topline == null || topline.getId() == null) {
	    return;
	}
	toplineDao.updateTopline(topline);

    }

    @Override
    public void deleteTopline(Long id) {
	if (id == null) {
	    return;
	}
	toplineDao.deleteTopline(id);

    }

    @Override
    public Topline findToplineById(Long id) {
	if (id == null) {
	    return null;
	}
	return toplineDao.findToplineById(id);
    }

    @Override
    public Page<Topline> findToplineByPage(int pageNum, int pageSize) {

	return toplineDao.findToplineByPage(pageNum, pageSize);
    }

}
