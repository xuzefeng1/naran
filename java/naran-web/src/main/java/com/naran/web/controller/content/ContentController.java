package com.naran.web.controller.content;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.collections.CollectionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.naran.core.entity.content.Content;
import com.naran.core.enums.ContentType;
import com.naran.dubbo.service.content.IContentService;
import com.naran.foundation.mybatis.page.Page;
import com.naran.foundation.util.ListVO;
import com.naran.foundation.util.ResultMessageBuilder;
import com.naran.web.controller.base.BaseController;
import com.naran.web.param.content.ContentPageParam;
import com.naran.web.vo.content.ContentPageVO;
import com.naran.web.vo.content.ContentVO;

/**
 * （官方相关推文）模块控制器
 * 
 * @author zefeng.xu
 */
@Controller
@RequestMapping("/api/content")
public class ContentController extends BaseController {

    @Autowired
    private IContentService contentService;

    /**
     * 专题报道列表(分页)
     * 
     * @param request
     * @param response
     */
    @RequestMapping(value = "/special/coverage/page")
    public void specialCoveragePage(ContentPageParam param, HttpServletRequest request, HttpServletResponse response) {
	ContentPageVO pageVO = new ContentPageVO();

	// 加载
	Page<Content> page = contentService.findContentByPage(ContentType.COVERAGE.name(), param.getPageNum(), param.getPageSize());
	if (null != page && CollectionUtils.isNotEmpty(page.getResult())) {
	    pageVO.setPageAll(param.getPageNum(), param.getPageSize(), page.getTotalPage(), page.getTotalCount());
	    pageVO.setContents(new ListVO<ContentVO>(page.getResult(), ContentVO.class).getVoList());
	}
	writeAjaxJSONResponse(ResultMessageBuilder.build(pageVO), response);
    }

    /**
     * 每周推荐列表(分页)
     * 
     * @param request
     * @param response
     */
    @RequestMapping(value = "/weekly/recommendation/page")
    public void weeklyRecommendationPage(ContentPageParam param, HttpServletRequest request, HttpServletResponse response) {
	ContentPageVO pageVO = new ContentPageVO();
	// 加载
	Page<Content> page = contentService.findContentByPage(ContentType.WEEKLY.name(), param.getPageNum(), param.getPageSize());
	if (null != page && CollectionUtils.isNotEmpty(page.getResult())) {
	    pageVO.setPageAll(param.getPageNum(), param.getPageSize(), page.getTotalPage(), page.getTotalCount());
	    pageVO.setContents(new ListVO<ContentVO>(page.getResult(), ContentVO.class).getVoList());
	}
	writeAjaxJSONResponse(ResultMessageBuilder.build(pageVO), response);
    }
}
