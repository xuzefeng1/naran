package com.naran.bd.controller.other;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.collections.CollectionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.naran.bd.controller.base.BaseController;
import com.naran.bd.param.other.RankPageParam;
import com.naran.bd.vo.other.RankPageVO;
import com.naran.bd.vo.other.RankVO;
import com.naran.core.entity.other.Rank;
import com.naran.dubbo.service.other.IRankService;
import com.naran.foundation.mybatis.page.Page;
import com.naran.foundation.util.ListVO;
import com.naran.foundation.util.ResultMessageBuilder;

/**
 * （榜单）模块控制器
 * 
 * @author zefeng.xu
 */
@Controller
@RequestMapping("/bd/rank")
public class RankController extends BaseController {

    @Autowired
    private IRankService rankService;

    /**
     * 榜单列表(分页)
     * 
     * @param request
     * @param response
     */
    @RequestMapping(value = "/welfare/page")
    public void welfarePage(RankPageParam param, HttpServletRequest request, HttpServletResponse response) {
	RankPageVO pageVO = new RankPageVO();
	// 加载
	Page<Rank> page = rankService.findRankByPage(param.getRankType(),param.getPageNum(), param.getPageSize());
	if (null != page && CollectionUtils.isNotEmpty(page.getResult())) {
	    pageVO.setPageAll(param.getPageNum(), param.getPageSize(), page.getTotalPage(), page.getTotalCount());
	    pageVO.setRanks(new ListVO<RankVO>(page.getResult(), RankVO.class).getVoList());
	}
	writeAjaxJSONResponse(ResultMessageBuilder.build(pageVO), response);
    }

}
