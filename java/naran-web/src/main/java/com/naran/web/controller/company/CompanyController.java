package com.naran.web.controller.company;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.collections.CollectionUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.naran.core.entity.company.Company;
import com.naran.core.enums.CompanyStatus;
import com.naran.dubbo.service.company.ICompanyService;
import com.naran.foundation.mybatis.page.Page;
import com.naran.foundation.util.ListVO;
import com.naran.foundation.util.ResultMessageBuilder;
import com.naran.web.controller.base.AppCode;
import com.naran.web.controller.base.BaseController;
import com.naran.web.param.BasePageParam;
import com.naran.web.param.company.CompanyParam;
import com.naran.web.vo.company.CompanyPageVO;
import com.naran.web.vo.company.CompanyVO;

/**
 * （爱心机构）模块控制器
 * 
 * @author zefeng.xu
 */
@Controller
@RequestMapping("/api/company")
public class CompanyController extends BaseController {

    @Autowired
    private ICompanyService companyService;

    /**
     * 公益机构列表(分页)
     * 
     * @param request
     * @param response
     */
    @RequestMapping(value = "/page")
    public void companyPage(BasePageParam param, HttpServletRequest request, HttpServletResponse response) {
	CompanyPageVO pageVO = new CompanyPageVO();
	// 加载
	Page<Company> page = companyService.findCompanyByPage(param.getPageNum(), param.getPageSize());
	if (null != page && CollectionUtils.isNotEmpty(page.getResult())) {
	    pageVO.setPageAll(param.getPageNum(), param.getPageSize(), page.getTotalPage(), page.getTotalCount());
	    pageVO.setCompanys(new ListVO<CompanyVO>(page.getResult(), CompanyVO.class).getVoList());
	}
	writeAjaxJSONResponse(ResultMessageBuilder.build(pageVO), response);
    }

    /**
     * 申请成为公益机构
     * 
     * @param request
     * @param response
     */
    @RequestMapping(value = "/change")
    public void changeCompanyPage(CompanyParam param, HttpServletRequest request, HttpServletResponse response) {
	Company company = new Company();
	BeanUtils.copyProperties(param, company);
	if (param.getCompanyId() != null) {
	    company.setId(param.getCompanyId());
	    company.setCompanyStatus(CompanyStatus.INITIAL.name());
	    companyService.updateCompany(company);
	} else {
	    companyService.addCompany(company);
	}
	writeAjaxJSONResponse(ResultMessageBuilder.build(AppCode.SUCCESS_CODE), response);
    }
}
