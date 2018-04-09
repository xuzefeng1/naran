package com.naran.dubbo.service.company;

import com.naran.core.entity.company.CompanyFootprint;
import com.naran.foundation.mybatis.page.Page;

/**
 * 那然足迹服务接口
 * 
 * @author zefeng.xu
 */
public interface ICompanyFootprintService {

    Long addCompanyFootprint(CompanyFootprint companyFootprint);

    void updateCompanyFootprint(CompanyFootprint companyFootprint);

    void deleteCompanyFootprint(Long id);

    CompanyFootprint findCompanyFootprintById(Long id);

    /**
     * 获取（分页）
     * 
     * @return
     */
    Page<CompanyFootprint> findCompanyFootprintByPage(int pageNum, int pageSize);

}
