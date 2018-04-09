package com.naran.core.dao.activity.impl;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.naran.core.dao.activity.ActivityDao;
import com.naran.core.entity.activity.Activity;
import com.naran.core.enums.ActivityStatus;
import com.naran.foundation.mybatis.dao.MyBatisDAO;
import com.naran.foundation.mybatis.page.Page;
import com.naran.foundation.mybatis.page.PageRequest;

/**
 * 
 * @author zefeng.xu
 */
@Repository
public class ActivityDaoImpl implements ActivityDao {

    private static final String ADD_ACTIVITY = "addActivity";
    private static final String UPDATE_ACTIVITY = "updateActivity";
    private static final String FIND_ACTIVITY_BY_ID = "findActivityById";
    private static final String FIND_ACTIVITY_BY_PAGE = "findActivityByPage";

    @Autowired
    private MyBatisDAO myBatisDAO;

    @Override
    public Long addActivity(Activity activity) {
	activity.setCreateTime(new Date());
	activity.setUpdateTime(new Date());
	activity.setExpired(Boolean.TRUE);
	activity.setActivityStatus(ActivityStatus.INITIAL.name());
	myBatisDAO.insert(ADD_ACTIVITY, activity);
	return activity.getId();
    }

    @Override
    public void updateActivity(Activity activity) {
	activity.setUpdateTime(new Date());
	myBatisDAO.update(UPDATE_ACTIVITY, activity);
    }

    @Override
    public void deleteActivity(Long id) {
	Activity activity = new Activity();
	activity.setId(id);
	activity.setExpired(Boolean.FALSE);
	myBatisDAO.update(UPDATE_ACTIVITY, activity);
    }

    @Override
    public Activity findActivityById(Long id) {
	if (id == null) {
	    return null;
	}
	return (Activity) myBatisDAO.findForObject(FIND_ACTIVITY_BY_ID, id);
    }

    @SuppressWarnings("unchecked")
    @Override
    public Page<Activity> findActivityByPage(String activityType, int pageNum, int pageSize) {
	Map<String, Object> param = new HashMap<String, Object>();
	param.put("activityType", activityType);
	return myBatisDAO.findForPage(FIND_ACTIVITY_BY_PAGE, new PageRequest(pageNum, pageSize, param));
    }

}
