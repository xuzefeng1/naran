package com.naran.bd.controller.other;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.collections.CollectionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.naran.bd.controller.base.BaseController;
import com.naran.bd.param.other.ToplinePageParam;
import com.naran.bd.vo.other.ToplinePageVO;
import com.naran.bd.vo.other.ToplineVO;
import com.naran.core.entity.other.Topline;
import com.naran.dubbo.service.other.IToplineService;
import com.naran.foundation.mybatis.page.Page;
import com.naran.foundation.util.ListVO;
import com.naran.foundation.util.ResultMessageBuilder;

/**
 * （那然快报）模块控制器
 * 
 * @author zefeng.xu
 */
@Controller
@RequestMapping("/bd/topline")
public class ToplineController extends BaseController {

    @Autowired
    private IToplineService toplineService;

    /**
     * 那然快报列表(分页)
     * 
     * @param request
     * @param response
     */
    @RequestMapping(value = "/page")
    public void toplinePage(ToplinePageParam param, HttpServletRequest request, HttpServletResponse response) {
	ToplinePageVO pageVO = new ToplinePageVO();
	// 加载
	Page<Topline> page = toplineService.findToplineByPage(param.getPageNum(), param.getPageSize());
	if (null != page && CollectionUtils.isNotEmpty(page.getResult())) {
	    pageVO.setPageAll(param.getPageNum(), param.getPageSize(), page.getTotalPage(), page.getTotalCount());
	    pageVO.setToplines(new ListVO<ToplineVO>(page.getResult(), ToplineVO.class).getVoList());
	}
	writeAjaxJSONResponse(ResultMessageBuilder.build(pageVO), response);
    }

}
