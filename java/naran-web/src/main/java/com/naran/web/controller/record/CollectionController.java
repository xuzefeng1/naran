package com.naran.web.controller.record;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletResponse;

import org.apache.commons.collections.CollectionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.naran.core.entity.account.Account;
import com.naran.core.entity.content.Content;
import com.naran.core.entity.order.Order;
import com.naran.core.entity.record.CollectionRecord;
import com.naran.core.enums.BusinessType;
import com.naran.core.enums.ContentType;
import com.naran.core.enums.OrderType;
import com.naran.dubbo.response.DubboResponse;
import com.naran.dubbo.response.DubboResponseCode;
import com.naran.dubbo.service.account.IAccountService;
import com.naran.dubbo.service.content.IContentService;
import com.naran.dubbo.service.order.IOrderService;
import com.naran.dubbo.service.record.ICollectionRecordService;
import com.naran.foundation.mybatis.page.Page;
import com.naran.foundation.util.ResultMessageBuilder;
import com.naran.web.controller.base.AppCode;
import com.naran.web.controller.base.BaseController;
import com.naran.web.param.BasePageParam;
import com.naran.web.param.BaseParam;
import com.naran.web.vo.content.ContentPageVO;
import com.naran.web.vo.content.ContentVO;
import com.naran.web.vo.order.OrderPageVO;
import com.naran.web.vo.order.OrderSimpleVO;
import com.naran.web.vo.record.RecordWithMeVO;

/**
 * （收藏操作）模块控制器
 * 
 * @author zefeng.xu
 */
@Controller
@RequestMapping("/api/record/collection")
public class CollectionController extends BaseController {

    @Autowired
    private ICollectionRecordService collectionRecordService;
    @Autowired
    private IAccountService accountService;
    @Autowired
    private IContentService contentService;
    @Autowired
    private IOrderService orderService;

    /**
     * 订单收藏
     * 
     */
    @RequestMapping(value = "/order")
    public void collectionOrder(BaseParam param, Long orderId, HttpServletResponse response) {
	Account current = accountService.currentAccount(param.getAccessToken());
	if (null == current) {
	    writeAjaxJSONResponse(ResultMessageBuilder.build(AppCode.GO_TO_LOGIN_CODE), response);
	    return;
	}
	DubboResponse<String> dResponse = collectionRecordService.addCollectionByOrder(current.getId(), orderId);
	if (!DubboResponseCode.SUCCESS.equals(dResponse.getCode())) {
	    writeAjaxJSONResponse(ResultMessageBuilder.build(AppCode.FAIL_CODE, dResponse.getMsg()), response);
	    return;
	}
	writeAjaxJSONResponse(ResultMessageBuilder.build(AppCode.SUCCESS_CODE), response);
    }

    /**
     * 取消订单收藏
     * 
     */
    @RequestMapping(value = "/order/delete")
    public void deleteCollectionByOrder(BaseParam param, Long orderId, HttpServletResponse response) {
	Account current = accountService.currentAccount(param.getAccessToken());
	if (null == current) {
	    writeAjaxJSONResponse(ResultMessageBuilder.build(AppCode.GO_TO_LOGIN_CODE), response);
	    return;
	}
	collectionRecordService.deleteCollectionByAccount(orderId, BusinessType.ORDER.name(), current.getId());
	writeAjaxJSONResponse(ResultMessageBuilder.build(AppCode.SUCCESS_CODE), response);
    }

    /**
     * 订单是否收藏
     * 
     */
    @RequestMapping(value = "/order/with/me")
    public void collectionOrderWithMe(BaseParam param, Long orderId, HttpServletResponse response) {
	Account current = accountService.currentAccount(param.getAccessToken());
	if (null == current) {
	    writeAjaxJSONResponse(ResultMessageBuilder.build(AppCode.GO_TO_LOGIN_CODE), response);
	    return;
	}
	CollectionRecord record = collectionRecordService.findCollectionByAccount(orderId, BusinessType.ORDER.name(), current.getId());
	RecordWithMeVO VO = new RecordWithMeVO();
	VO.setIsMe(record != null ? true : false);
	VO.setAccountId(current.getId());
	writeAjaxJSONResponse(ResultMessageBuilder.build(VO), response);
    }

    /**
     * 收藏捐赠列表
     * 
     */
    @RequestMapping(value = "/order/donation/account/page")
    public void orderDonationAccountPage(BasePageParam param, Long accountId, HttpServletResponse response) {
	Account current = accountService.currentAccount(param.getAccessToken());
	if (null == current) {
	    writeAjaxJSONResponse(ResultMessageBuilder.build(AppCode.GO_TO_LOGIN_CODE), response);
	    return;
	}
	Page<CollectionRecord> page = collectionRecordService.findCollectionByPage(BusinessType.ORDER.name(), OrderType.DONATION.name(), accountId, param.getPageNum(), param.getPageSize());
	OrderPageVO pageVO = new OrderPageVO();
	if (null != page && CollectionUtils.isNotEmpty(page.getResult())) {
	    pageVO.setPageAll(param.getPageNum(), param.getPageSize(), page.getTotalPage(), page.getTotalCount());
	    pageVO.setOrders(getOrders(page.getResult()));
	}
	writeAjaxJSONResponse(ResultMessageBuilder.build(pageVO), response);
    }

    /**
     * 收藏愿望列表
     * 
     */
    @RequestMapping(value = "/order/wish/account/page")
    public void orderWishAccountPage(BasePageParam param, Long accountId, HttpServletResponse response) {
	Account current = accountService.currentAccount(param.getAccessToken());
	if (null == current) {
	    writeAjaxJSONResponse(ResultMessageBuilder.build(AppCode.GO_TO_LOGIN_CODE), response);
	    return;
	}
	Page<CollectionRecord> page = collectionRecordService.findCollectionByPage(BusinessType.ORDER.name(), OrderType.WISH.name(), accountId, param.getPageNum(), param.getPageSize());

	OrderPageVO pageVO = new OrderPageVO();
	if (null != page && CollectionUtils.isNotEmpty(page.getResult())) {
	    pageVO.setPageAll(param.getPageNum(), param.getPageSize(), page.getTotalPage(), page.getTotalCount());
	    pageVO.setOrders(getOrders(page.getResult()));
	}
	writeAjaxJSONResponse(ResultMessageBuilder.build(pageVO), response);
    }

    private List<OrderSimpleVO> getOrders(List<CollectionRecord> records) {
	List<OrderSimpleVO> VOs = new ArrayList<OrderSimpleVO>();
	Order order = null;
	OrderSimpleVO VO = null;
	for (CollectionRecord record : records) {
	    order = orderService.findOrderById(record.getBusinessId());
	    if (order != null) {
		VO = new OrderSimpleVO();
		VO.convertPOToVO(order);
	    }
	    VOs.add(VO);
	}
	return VOs;

    }

    /**
     * 文章收藏
     * 
     */
    @RequestMapping(value = "/content")
    public void collectionContent(BaseParam param, Long contentId, HttpServletResponse response) {
	Account current = accountService.currentAccount(param.getAccessToken());
	if (null == current) {
	    writeAjaxJSONResponse(ResultMessageBuilder.build(AppCode.GO_TO_LOGIN_CODE), response);
	    return;
	}
	DubboResponse<String> dResponse = collectionRecordService.addCollectionByContent(current.getId(), contentId);
	if (!DubboResponseCode.SUCCESS.equals(dResponse.getCode())) {
	    writeAjaxJSONResponse(ResultMessageBuilder.build(AppCode.FAIL_CODE, dResponse.getMsg()), response);
	    return;
	}
	writeAjaxJSONResponse(ResultMessageBuilder.build(AppCode.SUCCESS_CODE), response);
    }

    /**
     * 取消文章收藏
     * 
     */
    @RequestMapping(value = "/content/delete")
    public void deleteCollectionByContent(BaseParam param, Long contentId, HttpServletResponse response) {
	Account current = accountService.currentAccount(param.getAccessToken());
	if (null == current) {
	    writeAjaxJSONResponse(ResultMessageBuilder.build(AppCode.GO_TO_LOGIN_CODE), response);
	    return;
	}
	collectionRecordService.deleteCollectionByAccount(contentId, BusinessType.CONTENT.name(), current.getId());
	writeAjaxJSONResponse(ResultMessageBuilder.build(AppCode.SUCCESS_CODE), response);
    }

    /**
     * 文章是否收藏
     * 
     */
    @RequestMapping(value = "/content/with/me")
    public void collectionContentWithMe(BaseParam param, Long contentId, HttpServletResponse response) {
	Account current = accountService.currentAccount(param.getAccessToken());
	if (null == current) {
	    writeAjaxJSONResponse(ResultMessageBuilder.build(AppCode.GO_TO_LOGIN_CODE), response);
	    return;
	}
	CollectionRecord record = collectionRecordService.findCollectionByAccount(contentId, BusinessType.CONTENT.name(), current.getId());

	RecordWithMeVO VO = new RecordWithMeVO();
	VO.setIsMe(record != null ? true : false);
	VO.setAccountId(current.getId());
	writeAjaxJSONResponse(ResultMessageBuilder.build(VO), response);
    }

    /**
     * 收藏文章列表
     * 
     */
    @RequestMapping(value = "/content/account/page")
    public void contentAccountPage(BasePageParam param, Long accountId, HttpServletResponse response) {
	Account current = accountService.currentAccount(param.getAccessToken());
	if (null == current) {
	    writeAjaxJSONResponse(ResultMessageBuilder.build(AppCode.GO_TO_LOGIN_CODE), response);
	    return;
	}
	Page<CollectionRecord> page = collectionRecordService.findCollectionByPage(BusinessType.CONTENT.name(), ContentType.WEEKLY.name(), accountId, param.getPageNum(), param.getPageSize());
	ContentPageVO pageVO = new ContentPageVO();
	if (null != page && CollectionUtils.isNotEmpty(page.getResult())) {
	    pageVO.setPageAll(param.getPageNum(), param.getPageSize(), page.getTotalPage(), page.getTotalCount());
	    pageVO.setContents(getContents(page.getResult()));
	}
	writeAjaxJSONResponse(ResultMessageBuilder.build(pageVO), response);
    }

    /**
     * 收藏专题报道列表
     * 
     */
    @RequestMapping(value = "/content/coverage/account/page")
    public void contentCoverageAccountPage(BasePageParam param, Long accountId, HttpServletResponse response) {
	Account current = accountService.currentAccount(param.getAccessToken());
	if (null == current) {
	    writeAjaxJSONResponse(ResultMessageBuilder.build(AppCode.GO_TO_LOGIN_CODE), response);
	    return;
	}
	Page<CollectionRecord> page = collectionRecordService.findCollectionByPage(BusinessType.CONTENT.name(), ContentType.COVERAGE.name(), accountId, param.getPageNum(), param.getPageSize());
	ContentPageVO pageVO = new ContentPageVO();
	if (null != page && CollectionUtils.isNotEmpty(page.getResult())) {
	    pageVO.setPageAll(param.getPageNum(), param.getPageSize(), page.getTotalPage(), page.getTotalCount());
	    pageVO.setContents(getContents(page.getResult()));
	}
	writeAjaxJSONResponse(ResultMessageBuilder.build(pageVO), response);
    }

    private List<ContentVO> getContents(List<CollectionRecord> records) {
	List<ContentVO> VOs = new ArrayList<ContentVO>();
	Content content = null;
	ContentVO VO = null;
	for (CollectionRecord record : records) {
	    content = contentService.findContentById(record.getBusinessId());
	    if (content != null) {
		VO = new ContentVO();
		VO.convertPOToVO(content);
	    }
	    VOs.add(VO);
	}
	return VOs;
    }
}
