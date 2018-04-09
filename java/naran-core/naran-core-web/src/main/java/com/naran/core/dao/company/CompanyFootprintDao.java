package com.naran.core.dao.company;

import com.naran.core.entity.company.CompanyFootprint;
import com.naran.foundation.mybatis.page.Page;

/**
 * 那然足迹数据访问接口
 * 
 * @author zefeng.xu
 */
public interface CompanyFootprintDao {

    Long addCompanyFootprint(CompanyFootprint companyFootprint);

    void updateCompanyFootprint(CompanyFootprint companyFootprint);

    void deleteCompanyFootprint(Long id);

    CompanyFootprint findCompanyFootprintById(Long id);

    Page<CompanyFootprint> findCompanyFootprintByPage(int pageNum, int pageSize);

}
