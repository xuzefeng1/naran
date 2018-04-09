package com.naran.core.service.impl.record;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.naran.core.dao.record.ShareRecordDao;
import com.naran.core.entity.activity.Activity;
import com.naran.core.entity.content.Content;
import com.naran.core.entity.order.Order;
import com.naran.core.entity.record.ShareRecord;
import com.naran.core.enums.BusinessType;
import com.naran.dubbo.response.DubboResponse;
import com.naran.dubbo.response.DubboResponseCode;
import com.naran.dubbo.service.activity.IActivityService;
import com.naran.dubbo.service.content.IContentService;
import com.naran.dubbo.service.order.IOrderService;
import com.naran.dubbo.service.record.IShareRecordService;

/**
 * 
 * @author zefeng.xu
 */
@Transactional
@Service("shareRecordService")
public class ShareRecordServiceImpl implements IShareRecordService {

    @Autowired
    private ShareRecordDao shareRecordDao;
    @Autowired
    private IOrderService orderService;
    @Autowired
    private IContentService contentService;
    @Autowired
    private IActivityService activityService;

    @Override
    public DubboResponse<String> shareOrder(Long accountId, Long orderId) {
	DubboResponse<String> dResponse = new DubboResponse<String>();
	Order order = orderService.findOrderById(orderId);
	if (order == null) {
	    dResponse.setCode(DubboResponseCode.FAIL);
	    dResponse.setMsg("分享对象不存在！");
	}
	order.setShareTimes(addTimes(order.getShareTimes()));
	orderService.updateOrder(order);
	addShareRecord(accountId, orderId, BusinessType.ORDER.name());
	return dResponse;
    }

    @Override
    public DubboResponse<String> shareContent(Long accountId, Long contentId) {
	DubboResponse<String> dResponse = new DubboResponse<String>();
	Content content = contentService.findContentById(contentId);
	if (content == null) {
	    dResponse.setCode(DubboResponseCode.FAIL);
	    dResponse.setMsg("分享对象不存在！");
	}
	content.setShareTimes(addTimes(content.getShareTimes()));
	contentService.updateContent(content);
	addShareRecord(accountId, contentId, BusinessType.CONTENT.name());
	return dResponse;
    }

    @Override
    public DubboResponse<String> shareActivity(Long accountId, Long activityId) {
	DubboResponse<String> dResponse = new DubboResponse<String>();
	Activity activity = activityService.findActivityById(activityId);
	if (activity == null) {
	    dResponse.setCode(DubboResponseCode.FAIL);
	    dResponse.setMsg("分享对象不存在！");
	}
	activity.setShareTimes(addTimes(activity.getShareTimes()));
	activityService.updateActivity(activity);
	addShareRecord(accountId, activityId, BusinessType.ACTIVITY.name());
	return dResponse;
    }

    private Integer addTimes(Integer times) {
	if (times == null) {
	    times = 0;
	}
	return times + 1;
    }

    private void addShareRecord(Long accountId, Long businessId, String businessType) {
	if (accountId == null || businessId == null) {
	    return;
	}
	ShareRecord record = new ShareRecord();
	record.setAccountId(accountId);
	record.setBusinessId(businessId);
	record.setBusinessType(businessType);
	shareRecordDao.addShareRecord(record);
    }

}
