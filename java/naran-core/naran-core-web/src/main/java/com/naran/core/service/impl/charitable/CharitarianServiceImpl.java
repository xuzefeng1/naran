package com.naran.core.service.impl.charitable;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.naran.core.dao.charitable.CharitarianDao;
import com.naran.core.entity.charitable.Charitarian;
import com.naran.dubbo.service.charitable.ICharitarianService;
import com.naran.foundation.mybatis.page.Page;

/**
 * 
 * @author zefeng.xu
 */
@Transactional
@Service("charitarianService")
public class CharitarianServiceImpl implements ICharitarianService {

    @Autowired
    private CharitarianDao charitarianDao;

    @Override
    public Long addCharitarian(Charitarian charitarian) {
	if (charitarian == null) {
	    return null;
	}
	return charitarianDao.addCharitarian(charitarian);
    }

    @Override
    public void updateCharitarian(Charitarian charitarian) {
	if (charitarian == null || charitarian.getId() == null) {
	    return;
	}
	charitarianDao.updateCharitarian(charitarian);

    }

    @Override
    public void deleteCharitarian(Long id) {
	if (id == null) {
	    return;
	}
	charitarianDao.deleteCharitarian(id);

    }

    @Override
    public Charitarian findCharitarianById(Long id) {
	if (id == null) {
	    return null;
	}
	return charitarianDao.findCharitarianById(id);
    }

    @Override
    public Page<Charitarian> findCharitarianByPage(int pageNum, int pageSize) {

	return charitarianDao.findCharitarianByPage(pageNum, pageSize);
    }

}
