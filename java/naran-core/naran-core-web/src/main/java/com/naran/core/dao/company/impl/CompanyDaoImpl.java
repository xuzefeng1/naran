package com.naran.core.dao.company.impl;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.naran.core.dao.company.CompanyDao;
import com.naran.core.entity.company.Company;
import com.naran.foundation.mybatis.dao.MyBatisDAO;
import com.naran.foundation.mybatis.page.Page;
import com.naran.foundation.mybatis.page.PageRequest;

/**
 * 
 * @author zefeng.xu
 */
@Repository
public class CompanyDaoImpl implements CompanyDao {

    private static final String ADD_COMPANY = "addCompany";
    private static final String UPDATE_COMPANY = "updateCompany";
    private static final String FIND_COMPANY_BY_ID = "findCompanyById";
    private static final String FIND_COMPANY_BY_PAGE = "findCompanyByPage";

    @Autowired
    private MyBatisDAO myBatisDAO;

    @Override
    public Long addCompany(Company company) {
	company.setCreateTime(new Date());
	company.setUpdateTime(new Date());
	company.setExpired(Boolean.TRUE);
	myBatisDAO.insert(ADD_COMPANY, company);
	return company.getId();
    }

    @Override
    public void updateCompany(Company company) {
	company.setUpdateTime(new Date());
	myBatisDAO.update(UPDATE_COMPANY, company);
    }

    @Override
    public void deleteCompany(Long id) {
	Company company = new Company();
	company.setId(id);
	company.setExpired(Boolean.FALSE);
	myBatisDAO.update(UPDATE_COMPANY, company);
    }

    @Override
    public Company findCompanyById(Long id) {
	if (id == null) {
	    return null;
	}
	return (Company) myBatisDAO.findForObject(FIND_COMPANY_BY_ID, id);
    }

    @SuppressWarnings("unchecked")
    @Override
    public Page<Company> findCompanyByPage(int pageNum, int pageSize) {
	Map<String, Object> param = new HashMap<String, Object>();
	return myBatisDAO.findForPage(FIND_COMPANY_BY_PAGE, new PageRequest(pageNum, pageSize, param));
    }

}
