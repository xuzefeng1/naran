package com.naran.bd.controller.other;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.collections.CollectionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.naran.bd.controller.base.BaseController;
import com.naran.bd.param.other.PublicityPageParam;
import com.naran.bd.vo.other.PublicityPageVO;
import com.naran.bd.vo.other.PublicityVO;
import com.naran.core.entity.other.Publicity;
import com.naran.dubbo.service.other.IPublicityService;
import com.naran.foundation.mybatis.page.Page;
import com.naran.foundation.util.ListVO;
import com.naran.foundation.util.ResultMessageBuilder;

/**
 * （轮播图）模块控制器
 * 
 * @author zefeng.xu
 */
@Controller
@RequestMapping("/bd/publicity")
public class PublicityController extends BaseController {

    @Autowired
    private IPublicityService publicityService;

    /**
     * 轮播图列表(分页)
     * 
     * @param request
     * @param response
     */
    @RequestMapping(value = "/page")
    public void publicityPage(PublicityPageParam param, HttpServletRequest request, HttpServletResponse response) {
	PublicityPageVO pageVO = new PublicityPageVO();
	// 加载
	Page<Publicity> page = publicityService.findPublicityByPage(param.getPublicityType(), param.getPageNum(), param.getPageSize());
	if (null != page && CollectionUtils.isNotEmpty(page.getResult())) {
	    pageVO.setPageAll(param.getPageNum(), param.getPageSize(), page.getTotalPage(), page.getTotalCount());
	    pageVO.setPublicitys(new ListVO<PublicityVO>(page.getResult(), PublicityVO.class).getVoList());
	}
	writeAjaxJSONResponse(ResultMessageBuilder.build(pageVO), response);
    }

}
