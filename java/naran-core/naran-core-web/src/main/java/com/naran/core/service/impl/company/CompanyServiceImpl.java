package com.naran.core.service.impl.company;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.naran.core.dao.company.CompanyDao;
import com.naran.core.entity.company.Company;
import com.naran.core.enums.CompanyStatus;
import com.naran.dubbo.service.company.ICompanyService;
import com.naran.foundation.mybatis.page.Page;

/**
 * 
 * @author zefeng.xu
 */
@Transactional
@Service("companyService")
public class CompanyServiceImpl implements ICompanyService {

    @Autowired
    private CompanyDao companyDao;

    @Override
    public Long addCompany(Company company) {
	if (company == null) {
	    return null;
	}
	company.setCompanyStatus(CompanyStatus.INITIAL.name());
	return companyDao.addCompany(company);
    }

    @Override
    public void updateCompany(Company company) {
	if (company == null || company.getId() == null) {
	    return;
	}
	companyDao.updateCompany(company);

    }

    @Override
    public void deleteCompany(Long id) {
	if (id == null) {
	    return;
	}
	companyDao.deleteCompany(id);

    }

    @Override
    public Company findCompanyById(Long id) {
	if (id == null) {
	    return null;
	}
	return companyDao.findCompanyById(id);
    }

    @Override
    public Page<Company> findCompanyByPage(int pageNum, int pageSize) {

	return companyDao.findCompanyByPage(pageNum, pageSize);
    }

}
