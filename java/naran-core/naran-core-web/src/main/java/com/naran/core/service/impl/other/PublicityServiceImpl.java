package com.naran.core.service.impl.other;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.naran.core.dao.other.PublicityDao;
import com.naran.core.entity.other.Publicity;
import com.naran.dubbo.service.other.IPublicityService;
import com.naran.foundation.mybatis.page.Page;

/**
 * 轮播图服务
 * 
 * @author zefeng.xu
 */
@Transactional
@Service("publicityService")
public class PublicityServiceImpl implements IPublicityService {

    @Autowired
    private PublicityDao publicityDao;

    @Override
    public Long addPublicity(Publicity publicity) {
	if (publicity == null) {
	    return null;
	}
	return publicityDao.addPublicity(publicity);
    }

    @Override
    public void updatePublicity(Publicity publicity) {
	if (publicity == null || publicity.getId() == null) {
	    return;
	}
	publicityDao.updatePublicity(publicity);

    }

    @Override
    public void deletePublicity(Long id) {
	if (id == null) {
	    return;
	}
	publicityDao.deletePublicity(id);

    }

    @Override
    public Publicity findPublicityById(Long id) {
	if (id == null) {
	    return null;
	}
	return publicityDao.findPublicityById(id);
    }

    @Override
    public Page<Publicity> findPublicityByPage(String publicityType, int pageNum, int pageSize) {

	return publicityDao.findPublicityByPage(publicityType, pageNum, pageSize);
    }

}
