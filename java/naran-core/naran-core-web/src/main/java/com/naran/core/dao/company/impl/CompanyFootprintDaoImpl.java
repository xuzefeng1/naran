package com.naran.core.dao.company.impl;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.naran.core.dao.company.CompanyFootprintDao;
import com.naran.core.entity.company.CompanyFootprint;
import com.naran.foundation.mybatis.dao.MyBatisDAO;
import com.naran.foundation.mybatis.page.Page;
import com.naran.foundation.mybatis.page.PageRequest;

/**
 * 
 * @author zefeng.xu
 */
@Repository
public class CompanyFootprintDaoImpl implements CompanyFootprintDao {

    private static final String ADD_COMPANY_FOOTPRINT = "addCompanyFootprint";
    private static final String UPDATE_COMPANY_FOOTPRINT = "updateCompanyFootprint";
    private static final String FIND_COMPANY_FOOTPRINT_BY_ID = "findCompanyFootprintById";
    private static final String FIND_COMPANY_FOOTPRINT_BY_PAGE = "findCompanyFootprintByPage";

    @Autowired
    private MyBatisDAO myBatisDAO;

    @Override
    public Long addCompanyFootprint(CompanyFootprint companyFootprint) {
	companyFootprint.setCreateTime(new Date());
	companyFootprint.setUpdateTime(new Date());
	companyFootprint.setExpired(Boolean.TRUE);
	myBatisDAO.insert(ADD_COMPANY_FOOTPRINT, companyFootprint);
	return companyFootprint.getId();
    }

    @Override
    public void updateCompanyFootprint(CompanyFootprint companyFootprint) {
	companyFootprint.setUpdateTime(new Date());
	myBatisDAO.update(UPDATE_COMPANY_FOOTPRINT, companyFootprint);
    }

    @Override
    public void deleteCompanyFootprint(Long id) {
	CompanyFootprint companyFootprint = new CompanyFootprint();
	companyFootprint.setId(id);
	companyFootprint.setExpired(Boolean.FALSE);
	myBatisDAO.update(UPDATE_COMPANY_FOOTPRINT, companyFootprint);
    }

    @Override
    public CompanyFootprint findCompanyFootprintById(Long id) {
	if (id == null) {
	    return null;
	}
	return (CompanyFootprint) myBatisDAO.findForObject(FIND_COMPANY_FOOTPRINT_BY_ID, id);
    }

    @SuppressWarnings("unchecked")
    @Override
    public Page<CompanyFootprint> findCompanyFootprintByPage(int pageNum, int pageSize) {
	Map<String, Object> param = new HashMap<String, Object>();
	return myBatisDAO.findForPage(FIND_COMPANY_FOOTPRINT_BY_PAGE, new PageRequest(pageNum, pageSize, param));
    }

}
