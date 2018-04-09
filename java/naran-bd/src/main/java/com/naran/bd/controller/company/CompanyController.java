package com.naran.bd.controller.company;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.collections.CollectionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.naran.bd.controller.base.BaseController;
import com.naran.bd.param.BasePageParam;
import com.naran.bd.vo.company.CompanyPageVO;
import com.naran.bd.vo.company.CompanyVO;
import com.naran.core.entity.company.Company;
import com.naran.dubbo.service.company.ICompanyService;
import com.naran.foundation.mybatis.page.Page;
import com.naran.foundation.util.ListVO;
import com.naran.foundation.util.ResultMessageBuilder;

/**
 * （那然快报）模块控制器
 * 
 * @author zefeng.xu
 */
@Controller
@RequestMapping("/bd/company")
public class CompanyController extends BaseController {

    @Autowired
    private ICompanyService companyService;

    /**
     * 那然快报列表(分页)
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

}
