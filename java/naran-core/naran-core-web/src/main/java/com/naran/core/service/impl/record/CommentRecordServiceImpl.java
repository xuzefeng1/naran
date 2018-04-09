package com.naran.core.service.impl.record;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.naran.core.dao.record.CommentRecordDao;
import com.naran.core.entity.account.Account;
import com.naran.core.entity.activity.Activity;
import com.naran.core.entity.content.Content;
import com.naran.core.entity.order.Order;
import com.naran.core.entity.record.CommentRecord;
import com.naran.core.enums.BusinessType;
import com.naran.dubbo.response.DubboResponse;
import com.naran.dubbo.response.DubboResponseCode;
import com.naran.dubbo.service.account.IAccountService;
import com.naran.dubbo.service.activity.IActivityService;
import com.naran.dubbo.service.content.IContentService;
import com.naran.dubbo.service.order.IOrderService;
import com.naran.dubbo.service.record.ICommentRecordService;
import com.naran.foundation.mybatis.page.Page;

/**
 * 
 * @author zefeng.xu
 */
@Transactional
@Service("commentRecordService")
public class CommentRecordServiceImpl implements ICommentRecordService {

    @Autowired
    private CommentRecordDao commentRecordDao;
    @Autowired
    private IOrderService orderService;
    @Autowired
    private IContentService contentService;
    @Autowired
    private IActivityService activityService;
    @Autowired
    private IAccountService accountService;

    @Override
    public DubboResponse<String> addCommentByOrder(Long accountId, Long orderId, Long commentedId, String commentContent) {
	DubboResponse<String> dResponse = new DubboResponse<String>();
	Order order = orderService.findOrderById(orderId);
	if (order == null) {
	    dResponse.setCode(DubboResponseCode.FAIL);
	    dResponse.setMsg("评论对象不存在！");
	}
	order.setCommentTimes(addTimes(order.getCommentTimes()));
	orderService.updateOrder(order);
	addCommentRecord(accountId, orderId, BusinessType.ORDER.name(), commentedId, commentContent);
	return dResponse;
    }

    @Override
    public DubboResponse<String> addCommentByContent(Long accountId, Long contentId, Long commentedId, String commentContent) {
	DubboResponse<String> dResponse = new DubboResponse<String>();
	Content content = contentService.findContentById(contentId);
	if (content == null) {
	    dResponse.setCode(DubboResponseCode.FAIL);
	    dResponse.setMsg("评论对象不存在！");
	}
	content.setCommentTimes(addTimes(content.getCommentTimes()));
	contentService.updateContent(content);
	addCommentRecord(accountId, contentId, BusinessType.CONTENT.name(), commentedId, commentContent);
	return dResponse;
    }

    @Override
    public DubboResponse<String> addCommentByActivity(Long accountId, Long activityId, Long commentedId, String commentContent) {
	DubboResponse<String> dResponse = new DubboResponse<String>();
	Activity activity = activityService.findActivityById(activityId);
	if (activity == null) {
	    dResponse.setCode(DubboResponseCode.FAIL);
	    dResponse.setMsg("分享对象不存在！");
	}
	activity.setCommentTimes(addTimes(activity.getCommentTimes()));
	activityService.updateActivity(activity);
	addCommentRecord(accountId, activityId, BusinessType.ACTIVITY.name(), commentedId, commentContent);
	return dResponse;
    }

    private Integer addTimes(Integer times) {
	if (times == null) {
	    times = 0;
	}
	return times + 1;
    }

    private void addCommentRecord(Long accountId, Long businessId, String businessType, Long commentedId, String commentContent) {
	if (accountId == null || businessId == null) {
	    return;
	}
	CommentRecord record = new CommentRecord();
	record.setInitiatorId(accountId);
	Account initiator = accountService.getAccountById(accountId);
	if (initiator != null) {
	    record.setInitiatorNike(initiator.getNickName());
	}
	record.setBusinessId(businessId);
	record.setBusinessType(businessType);
	record.setCommentContent(commentContent);

	CommentRecord commented = commentRecordDao.findCommentRecordById(commentedId);
	if (commented != null) {
	    if (commented.getCommentedTopId() != null) {
		record.setCommentedTopId(commented.getCommentedTopId());
	    } else {
		record.setCommentedTopId(commentedId);
	    }
	    record.setCommentedId(commentedId);
	    if (!accountId.equals(commented.getInitiatorId())) {
		record.setRecipientId(commented.getInitiatorId());
		Account recipient = accountService.getAccountById(commented.getInitiatorId());
		if (recipient != null) {
		    record.setRecipientNike(recipient.getNickName());
		}
	    }
	}

	commentRecordDao.addCommentRecord(record);
    }

    @Override
    public Page<CommentRecord> findCommentRecordByPage(Long businessId, String businessType, Long commentedTopId, int pageNum, int pageSize) {
	return commentRecordDao.findCommentRecordByPage(businessId, businessType, commentedTopId, pageNum, pageSize);
    }

    @Override
    public CommentRecord findCommentRecordById(Long id) {
	return commentRecordDao.findCommentRecordById(id);
    }

    @Override
    public void deleteCommentRecord(Long id) {
	commentRecordDao.deleteCommentRecord(id);
    }

    @Override
    public CommentRecord findCommentRecordByAccount(Long businessId, String businessType, Long accountId) {
	return commentRecordDao.findCommentRecordByAccount(businessId, businessType, accountId);
    }

}
