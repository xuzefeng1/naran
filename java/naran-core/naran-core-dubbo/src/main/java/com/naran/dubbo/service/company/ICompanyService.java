package com.naran.dubbo.service.company;

import com.naran.core.entity.company.Company;
import com.naran.foundation.mybatis.page.Page;

/**
 * 那然慈善机构服务接口
 * 
 * @author zefeng.xu
 */
public interface ICompanyService {

    Long addCompany(Company company);

    void updateCompany(Company company);

    void deleteCompany(Long id);

    Company findCompanyById(Long id);

    /**
     * 获取（分页）
     * 
     * @return
     */
    Page<Company> findCompanyByPage(int pageNum, int pageSize);

}
