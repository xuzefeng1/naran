package com.naran.core.dao.company;

import com.naran.core.entity.company.Company;
import com.naran.foundation.mybatis.page.Page;

/**
 * 那然慈善机构数据访问接口
 * 
 * @author zefeng.xu
 */
public interface CompanyDao {

    Long addCompany(Company company);

    void updateCompany(Company company);

    void deleteCompany(Long id);

    Company findCompanyById(Long id);

    Page<Company> findCompanyByPage(int pageNum, int pageSize);

}
