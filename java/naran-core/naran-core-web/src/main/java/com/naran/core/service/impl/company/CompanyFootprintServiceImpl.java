package com.naran.core.service.impl.company;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.naran.core.dao.company.CompanyFootprintDao;
import com.naran.core.entity.company.CompanyFootprint;
import com.naran.dubbo.service.company.ICompanyFootprintService;
import com.naran.foundation.mybatis.page.Page;

/**
 * 
 * @author zefeng.xu
 */
@Transactional
@Service("companyFootprintService")
public class CompanyFootprintServiceImpl implements ICompanyFootprintService {

    @Autowired
    private CompanyFootprintDao companyFootprintDao;

    @Override
    public Long addCompanyFootprint(CompanyFootprint companyFootprint) {
	if (companyFootprint == null) {
	    return null;
	}
	return companyFootprintDao.addCompanyFootprint(companyFootprint);
    }

    @Override
    public void updateCompanyFootprint(CompanyFootprint companyFootprint) {
	if (companyFootprint == null || companyFootprint.getId() == null) {
	    return;
	}
	companyFootprintDao.updateCompanyFootprint(companyFootprint);

    }

    @Override
    public void deleteCompanyFootprint(Long id) {
	if (id == null) {
	    return;
	}
	companyFootprintDao.deleteCompanyFootprint(id);

    }

    @Override
    public CompanyFootprint findCompanyFootprintById(Long id) {
	if (id == null) {
	    return null;
	}
	return companyFootprintDao.findCompanyFootprintById(id);
    }

    @Override
    public Page<CompanyFootprint> findCompanyFootprintByPage(int pageNum, int pageSize) {

	return companyFootprintDao.findCompanyFootprintByPage(pageNum, pageSize);
    }

}
