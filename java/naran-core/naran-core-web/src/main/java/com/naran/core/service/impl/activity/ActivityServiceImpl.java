package com.naran.core.service.impl.activity;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.naran.core.dao.activity.ActivityDao;
import com.naran.core.entity.activity.Activity;
import com.naran.dubbo.service.activity.IActivityService;
import com.naran.foundation.mybatis.page.Page;

/**
 * 
 * @author zefeng.xu
 */
@Transactional
@Service("activityService")
public class ActivityServiceImpl implements IActivityService {

    @Autowired
    private ActivityDao activityDao;

    @Override
    public Long addActivity(Activity activity) {
	if (activity == null) {
	    return null;
	}
	return activityDao.addActivity(activity);
    }

    @Override
    public void updateActivity(Activity activity) {
	if (activity == null || activity.getId() == null) {
	    return;
	}
	activityDao.updateActivity(activity);

    }

    @Override
    public void deleteActivity(Long id) {
	if (id == null) {
	    return;
	}
	activityDao.deleteActivity(id);

    }

    @Override
    public Activity findActivityById(Long id) {
	if (id == null) {
	    return null;
	}
	return activityDao.findActivityById(id);
    }

    @Override
    public Page<Activity> findActivityByPage(String activityType, int pageNum, int pageSize) {

	return activityDao.findActivityByPage(activityType, pageNum, pageSize);
    }

}
