package com.naran.core.service.impl.activity;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.naran.core.dao.activity.ActivityApplyDao;
import com.naran.core.entity.activity.ActivityApply;
import com.naran.dubbo.service.activity.IActivityApplyService;
import com.naran.foundation.mybatis.page.Page;

/**
 * 
 * @author zefeng.xu
 */
@Transactional
@Service("activityApplyService")
public class ActivityApplyServiceImpl implements IActivityApplyService {

    @Autowired
    private ActivityApplyDao activityApplyDao;

    @Override
    public Long addActivityApply(ActivityApply activityApply) {
	if (activityApply == null) {
	    return null;
	}
	return activityApplyDao.addActivityApply(activityApply);
    }

    @Override
    public void updateActivityApply(ActivityApply activityApply) {
	if (activityApply == null || activityApply.getId() == null) {
	    return;
	}
	activityApplyDao.updateActivityApply(activityApply);

    }

    @Override
    public void deleteActivityApply(Long id) {
	if (id == null) {
	    return;
	}
	activityApplyDao.deleteActivityApply(id);

    }

    @Override
    public ActivityApply findActivityApplyById(Long id) {
	if (id == null) {
	}
	return activityApplyDao.findActivityApplyById(id);
    }

    @Override
    public Page<ActivityApply> findActivityApplyByPage(Long activityId, int pageNum, int pageSize) {

	return activityApplyDao.findActivityApplyByPage(activityId,pageNum, pageSize);
    }

    @Override
    public ActivityApply findActivityApplyByAccountId(Long activityId, Long accountId) {
	return activityApplyDao.findActivityApplyByAccountId(activityId, accountId);
    }

}
