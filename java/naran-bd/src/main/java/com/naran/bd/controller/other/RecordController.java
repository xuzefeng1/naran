package com.naran.bd.controller.other;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletResponse;

import org.apache.commons.collections.CollectionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.naran.bd.controller.base.AppCode;
import com.naran.bd.controller.base.BaseController;
import com.naran.bd.param.BasePageParam;
import com.naran.bd.param.BaseParam;
import com.naran.bd.vo.other.CommentRecordPageVO;
import com.naran.bd.vo.other.CommentRecordVO;
import com.naran.bd.vo.other.RecordWithMeVO;
import com.naran.core.entity.account.Account;
import com.naran.core.entity.record.CollectionRecord;
import com.naran.core.entity.record.CommentRecord;
import com.naran.core.enums.BusinessType;
import com.naran.dubbo.response.DubboResponse;
import com.naran.dubbo.response.DubboResponseCode;
import com.naran.dubbo.service.account.IAccountService;
import com.naran.dubbo.service.record.ICollectionRecordService;
import com.naran.dubbo.service.record.ICommentRecordService;
import com.naran.dubbo.service.record.IShareRecordService;
import com.naran.foundation.mybatis.page.Page;
import com.naran.foundation.util.ResultMessageBuilder;
import com.naran.foundation.util.StringUtil;
import com.naran.foundation.util.file.qiniu.QiniuImageUtil;

/**
 * （分享，收藏，评论操作）模块控制器
 * 
 * @author zefeng.xu
 */
@Controller
@RequestMapping("/bd/record")
public class RecordController extends BaseController {

    @Autowired
    private IShareRecordService shareRecordService;
    @Autowired
    private ICollectionRecordService collectionRecordService;
    @Autowired
    private ICommentRecordService commentRecordService;
    @Autowired
    private IAccountService accountService;

    /**
     * 订单分享
     * 
     */
    @RequestMapping(value = "/share/order")
    public void shareOrder(BaseParam param, Long orderId, HttpServletResponse response) {
	Account current = accountService.currentAccount(param.getAccessToken());
	if (null == current) {
	    writeAjaxJSONResponse(ResultMessageBuilder.build(AppCode.GO_TO_LOGIN_CODE), response);
	    return;
	}
	DubboResponse<String> dResponse = shareRecordService.shareOrder(current.getId(), orderId);
	if (!DubboResponseCode.SUCCESS.equals(dResponse.getCode())) {
	    writeAjaxJSONResponse(ResultMessageBuilder.build(AppCode.FAIL_CODE, dResponse.getMsg()), response);
	    return;
	}
	writeAjaxJSONResponse(ResultMessageBuilder.build(AppCode.SUCCESS_CODE), response);
    }

    /**
     * 文章分享
     * 
     */
    @RequestMapping(value = "/share/content")
    public void shareContent(BaseParam param, Long contentId, HttpServletResponse response) {
	Account current = accountService.currentAccount(param.getAccessToken());
	if (null == current) {
	    writeAjaxJSONResponse(ResultMessageBuilder.build(AppCode.GO_TO_LOGIN_CODE), response);
	    return;
	}
	DubboResponse<String> dResponse = shareRecordService.shareContent(current.getId(), contentId);
	if (!DubboResponseCode.SUCCESS.equals(dResponse.getCode())) {
	    writeAjaxJSONResponse(ResultMessageBuilder.build(AppCode.FAIL_CODE, dResponse.getMsg()), response);
	    return;
	}
	writeAjaxJSONResponse(ResultMessageBuilder.build(AppCode.SUCCESS_CODE), response);
    }

    /**
     * 订单收藏
     * 
     */
    @RequestMapping(value = "/collection/order")
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
    @RequestMapping(value = "/collection/order/delete")
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
    @RequestMapping(value = "/collection/order/with/me")
    public void collectionOrderWithMe(BaseParam param, Long orderId, HttpServletResponse response) {
	Account current = accountService.currentAccount(param.getAccessToken());
	if (null == current) {
	    writeAjaxJSONResponse(ResultMessageBuilder.build(AppCode.GO_TO_LOGIN_CODE), response);
	    return;
	}
	CollectionRecord record = collectionRecordService.findCollectionByAccount(orderId, BusinessType.CONTENT.name(), current.getId());
	RecordWithMeVO VO = new RecordWithMeVO();
	VO.setIsMe(record != null ? true : false);
	VO.setAccountId(current.getId());
	writeAjaxJSONResponse(ResultMessageBuilder.build(VO), response);
    }

    /**
     * 文章收藏
     * 
     */
    @RequestMapping(value = "/collection/content")
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
    @RequestMapping(value = "/collection/content/delete")
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
    @RequestMapping(value = "/collection/content/with/me")
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
     * 订单评论列表
     * 
     */
    @RequestMapping(value = "/comment/order/page")
    public void commentOrderPage(BasePageParam param, Long orderId, HttpServletResponse response) {
	CommentRecordPageVO pageVO = new CommentRecordPageVO();
	// 加载
	Page<CommentRecord> page = commentRecordService.findCommentRecordByPage(orderId, BusinessType.ORDER.name(), null, param.getPageNum(), param.getPageSize());
	if (null != page && CollectionUtils.isNotEmpty(page.getResult())) {
	    pageVO.setPageAll(param.getPageNum(), param.getPageSize(), page.getTotalPage(), page.getTotalCount());
	    pageVO.setComments(getCommentRecordVOs(page.getResult()));
	}
	writeAjaxJSONResponse(ResultMessageBuilder.build(pageVO), response);
    }

    /**
     * 订单评论
     * 
     */
    @RequestMapping(value = "/comment/order")
    public void commentOrder(BaseParam param, Long orderId, Long commentedId, String commentContent, HttpServletResponse response) {
	Account current = accountService.currentAccount(param.getAccessToken());
	if (null == current) {
	    writeAjaxJSONResponse(ResultMessageBuilder.build(AppCode.GO_TO_LOGIN_CODE), response);
	    return;
	}
	if (null == commentContent || StringUtil.isBlank(commentContent)) {
	    writeAjaxJSONResponse(ResultMessageBuilder.build(AppCode.FAIL_CODE, "评论内容不能为空！"), response);
	    return;
	}
	DubboResponse<String> dResponse = commentRecordService.addCommentByOrder(current.getId(), orderId, commentedId, commentContent);
	if (!DubboResponseCode.SUCCESS.equals(dResponse.getCode())) {
	    writeAjaxJSONResponse(ResultMessageBuilder.build(AppCode.FAIL_CODE, dResponse.getMsg()), response);
	    return;
	}
	writeAjaxJSONResponse(ResultMessageBuilder.build(AppCode.SUCCESS_CODE), response);
    }

    /**
     * 订单是否评论
     * 
     */
    @RequestMapping(value = "/comment/order/with/me")
    public void commentOrderWithMe(BaseParam param, Long orderId, HttpServletResponse response) {
	Account current = accountService.currentAccount(param.getAccessToken());
	if (null == current) {
	    writeAjaxJSONResponse(ResultMessageBuilder.build(AppCode.GO_TO_LOGIN_CODE), response);
	    return;
	}
	CommentRecord record = commentRecordService.findCommentRecordByAccount(orderId, BusinessType.ORDER.name(), current.getId());
	RecordWithMeVO VO = new RecordWithMeVO();
	VO.setIsMe(record != null ? true : false);
	VO.setAccountId(current.getId());
	writeAjaxJSONResponse(ResultMessageBuilder.build(VO), response);
    }

    /**
     * 文章评论列表
     * 
     */
    @RequestMapping(value = "/comment/content/page")
    public void commentContentPage(BasePageParam param, Long contentId, HttpServletResponse response) {
	CommentRecordPageVO pageVO = new CommentRecordPageVO();
	// 加载
	Page<CommentRecord> page = commentRecordService.findCommentRecordByPage(contentId, BusinessType.CONTENT.name(), null, param.getPageNum(), param.getPageSize());
	if (null != page && CollectionUtils.isNotEmpty(page.getResult())) {
	    pageVO.setPageAll(param.getPageNum(), param.getPageSize(), page.getTotalPage(), page.getTotalCount());
	    pageVO.setComments(getCommentRecordVOs(page.getResult()));
	}
	writeAjaxJSONResponse(ResultMessageBuilder.build(pageVO), response);
    }

    /**
     * 文章评论
     * 
     */
    @RequestMapping(value = "/comment/content")
    public void commentContent(BaseParam param, Long contentId, Long commentedId, String commentContent, HttpServletResponse response) {
	Account current = accountService.currentAccount(param.getAccessToken());
	if (null == current) {
	    writeAjaxJSONResponse(ResultMessageBuilder.build(AppCode.GO_TO_LOGIN_CODE), response);
	    return;
	}
	if (null == commentContent || StringUtil.isBlank(commentContent)) {
	    writeAjaxJSONResponse(ResultMessageBuilder.build(AppCode.FAIL_CODE, "评论内容不能为空！"), response);
	    return;
	}
	DubboResponse<String> dResponse = commentRecordService.addCommentByContent(current.getId(), contentId, commentedId, commentContent);
	if (!DubboResponseCode.SUCCESS.equals(dResponse.getCode())) {
	    writeAjaxJSONResponse(ResultMessageBuilder.build(AppCode.FAIL_CODE, dResponse.getMsg()), response);
	    return;
	}
	writeAjaxJSONResponse(ResultMessageBuilder.build(AppCode.SUCCESS_CODE), response);
    }

    /**
     * 文章是否评论
     * 
     */
    @RequestMapping(value = "/comment/content/with/me")
    public void commentContentWithMe(BaseParam param, Long contentId, HttpServletResponse response) {
	Account current = accountService.currentAccount(param.getAccessToken());
	if (null == current) {
	    writeAjaxJSONResponse(ResultMessageBuilder.build(AppCode.GO_TO_LOGIN_CODE), response);
	    return;
	}
	CommentRecord record = commentRecordService.findCommentRecordByAccount(contentId, BusinessType.CONTENT.name(), current.getId());
	RecordWithMeVO VO = new RecordWithMeVO();
	VO.setIsMe(record != null ? true : false);
	VO.setAccountId(current.getId());
	writeAjaxJSONResponse(ResultMessageBuilder.build(VO), response);
    }

    /**
     * 删除评论
     * 
     */
    @RequestMapping(value = "/comment/delete")
    public void deleteComment(BaseParam param, Long commentId, HttpServletResponse response) {
	Account current = accountService.currentAccount(param.getAccessToken());
	if (null == current) {
	    writeAjaxJSONResponse(ResultMessageBuilder.build(AppCode.GO_TO_LOGIN_CODE), response);
	    return;
	}

	CommentRecord comment = commentRecordService.findCommentRecordById(commentId);
	if (comment != null && !current.getId().equals(comment.getInitiatorId())) {
	    writeAjaxJSONResponse(ResultMessageBuilder.build(AppCode.FAIL_CODE, "不能删除非本人的评论！"), response);
	    return;
	}
	if (comment != null) {
	    commentRecordService.deleteCommentRecord(commentId);
	}
	writeAjaxJSONResponse(ResultMessageBuilder.build(AppCode.SUCCESS_CODE), response);
    }

    /**
     * 评论详情列表(通用)
     * 
     */
    @RequestMapping(value = "/comment/detail/page")
    public void commentDetailPage(BasePageParam param, Long commentedId, HttpServletResponse response) {
	CommentRecordVO commentedVO = new CommentRecordVO();
	Account account = null;
	CommentRecord commented = commentRecordService.findCommentRecordById(commentedId);
	if (commented == null) {
	    writeAjaxJSONResponse(ResultMessageBuilder.build(AppCode.FAIL_CODE, "被评论订单不存在！"), response);
	    return;
	}
	commentedVO.convertPOToVO(commented);
	account = accountService.getAccountById(commented.getInitiatorId());
	if (account != null) {
	    commentedVO.setInitiatorNike(account.getNickName());
	    commentedVO.setInitiatorImg(QiniuImageUtil.newImageUrl60(account.getPhoto()));
	}

	// 获取二级数据
	CommentRecordPageVO pageVO = new CommentRecordPageVO();
	Page<CommentRecord> page = commentRecordService.findCommentRecordByPage(null, null, commentedId, param.getPageNum(), param.getPageSize());
	if (null != page && CollectionUtils.isNotEmpty(page.getResult())) {
	    pageVO.setPageAll(param.getPageNum(), param.getPageSize(), page.getTotalPage(), page.getTotalCount());
	    CommentRecordVO VO = null;
	    List<CommentRecordVO> VOs = new ArrayList<CommentRecordVO>();
	    for (CommentRecord comment : page.getResult()) {
		VO = new CommentRecordVO();
		VO.convertPOToVO(comment);
		account = accountService.getAccountById(comment.getInitiatorId());
		if (account != null) {
		    VO.setInitiatorNike(account.getNickName());
		    VO.setInitiatorImg(QiniuImageUtil.newImageUrl60(account.getPhoto()));
		}
	    }
	    pageVO.setComments(VOs);
	    commentedVO.setRecords(pageVO);
	}
	writeAjaxJSONResponse(ResultMessageBuilder.build(commentedVO), response);
    }

    private List<CommentRecordVO> getCommentRecordVOs(List<CommentRecord> comments) {
	List<CommentRecordVO> VOs = new ArrayList<CommentRecordVO>();
	CommentRecordVO VO = null;
	Account account = null;
	CommentRecordPageVO pageVO = null;
	Page<CommentRecord> page = null;
	for (CommentRecord comment : comments) {
	    VO = new CommentRecordVO();
	    VO.convertPOToVO(comment);
	    account = accountService.getAccountById(comment.getInitiatorId());
	    if (account != null) {
		VO.setInitiatorNike(account.getNickName());
		VO.setInitiatorImg(QiniuImageUtil.newImageUrl60(account.getPhoto()));
	    }
	    // 获取二级数据
	    pageVO = new CommentRecordPageVO();
	    page = commentRecordService.findCommentRecordByPage(null, null, comment.getId(), 1, 3);
	    if (null != page && CollectionUtils.isNotEmpty(page.getResult())) {
		pageVO.setPageAll(1, 3, page.getTotalPage(), page.getTotalCount());
		List<CommentRecordVO> VO1s = new ArrayList<CommentRecordVO>();
		CommentRecordVO VO1 = null;
		Account account1 = null;
		for (CommentRecord commentRecord : page.getResult()) {
		    VO1 = new CommentRecordVO();
		    VO1.convertPOToVO(commentRecord);
		    account1 = accountService.getAccountById(commentRecord.getInitiatorId());
		    if (account1 != null) {
			VO1.setInitiatorNike(account1.getNickName());
			VO1.setInitiatorImg(QiniuImageUtil.newImageUrl60(account1.getPhoto()));
		    }
		    VO1s.add(VO1);
		}
		pageVO.setComments(VO1s);
		VO.setRecords(pageVO);
	    }
	    VOs.add(VO);
	}
	return VOs;
    }

}
