package com.naran.core.service.impl.record;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.naran.core.dao.record.CollectionRecordDao;
import com.naran.core.entity.content.Content;
import com.naran.core.entity.order.Order;
import com.naran.core.entity.record.CollectionRecord;
import com.naran.core.enums.BusinessType;
import com.naran.dubbo.response.DubboResponse;
import com.naran.dubbo.response.DubboResponseCode;
import com.naran.dubbo.service.content.IContentService;
import com.naran.dubbo.service.order.IOrderService;
import com.naran.dubbo.service.record.ICollectionRecordService;
import com.naran.foundation.mybatis.page.Page;

/**
 * 
 * @author zefeng.xu
 */
@Transactional
@Service("collectionRecordService")
public class CollectionRecordServiceImpl implements ICollectionRecordService {

    @Autowired
    private CollectionRecordDao collectionRecordDao;
    @Autowired
    private IOrderService orderService;
    @Autowired
    private IContentService contentService;

    @Override
    public DubboResponse<String> addCollectionByOrder(Long accountId, Long orderId) {
	DubboResponse<String> dResponse = new DubboResponse<String>();
	Order order = orderService.findOrderById(orderId);
	if (order == null) {
	    dResponse.setCode(DubboResponseCode.FAIL);
	    dResponse.setMsg("收藏对象不存在！");
	}
	order.setCollectionTimes(addTimes(order.getCollectionTimes()));
	orderService.updateOrder(order);
	addCollectionRecord(accountId, orderId, BusinessType.ORDER.name(), order.getOrderType());
	return dResponse;
    }

    @Override
    public DubboResponse<String> addCollectionByContent(Long accountId, Long contentId) {
	DubboResponse<String> dResponse = new DubboResponse<String>();
	Content content = contentService.findContentById(contentId);
	if (content == null) {
	    dResponse.setCode(DubboResponseCode.FAIL);
	    dResponse.setMsg("收藏对象不存在！");
	}
	content.setCollectionTimes(addTimes(content.getCollectionTimes()));
	contentService.updateContent(content);
	addCollectionRecord(accountId, contentId, BusinessType.CONTENT.name(), content.getContentType());
	return dResponse;
    }

    private Integer addTimes(Integer times) {
	if (times == null) {
	    times = 0;
	}
	return times + 1;
    }

    private void addCollectionRecord(Long accountId, Long businessId, String businessType, String secondType) {
	if (accountId == null || businessId == null) {
	    return;
	}
	CollectionRecord record = new CollectionRecord();
	record.setAccountId(accountId);
	record.setBusinessId(businessId);
	record.setBusinessType(businessType);
	record.setSecondType(secondType);
	collectionRecordDao.addCollectionRecord(record);
    }

    @Override
    public void deleteCollectionByAccount(Long businessId, String businessType, Long accountId) {
	collectionRecordDao.deleteCollectionRecordByAccount(businessId, businessType, accountId);

    }

    @Override
    public CollectionRecord findCollectionByAccount(Long businessId, String businessType, Long accountId) {
	return collectionRecordDao.findCollectionRecordByAccount(businessId, businessType, accountId);
    }

    @Override
    public Page<CollectionRecord> findCollectionByPage(String businessType, String secondType, Long accountId, int pageNum, int pageSize) {

	return collectionRecordDao.findCollectionRecordByPage(businessType, secondType, accountId, pageNum, pageSize);
    }
}
