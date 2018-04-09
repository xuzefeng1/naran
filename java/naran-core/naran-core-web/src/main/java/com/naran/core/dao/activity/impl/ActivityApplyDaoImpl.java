package com.naran.core.dao.activity.impl;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.naran.core.dao.activity.ActivityApplyDao;
import com.naran.core.entity.activity.ActivityApply;
import com.naran.core.enums.ActivityApplyStatus;
import com.naran.foundation.mybatis.dao.MyBatisDAO;
import com.naran.foundation.mybatis.page.Page;
import com.naran.foundation.mybatis.page.PageRequest;

/**
 * 
 * @author zefeng.xu
 */
@Repository
public class ActivityApplyDaoImpl implements ActivityApplyDao {

    private static final String ADD_ACTIVITY_APPLY = "addActivityApply";
    private static final String UPDATE_ACTIVITY_APPLY = "updateActivityApply";
    private static final String FIND_ACTIVITY_APPLY_BY_ID = "findActivityApplyById";
    private static final String FIND_ACTIVITY_APPLY_BY_ACCOUNT_ID="findActivityApplyByAccountId";
    private static final String FIND_ACTIVITY_APPLY_BY_PAGE = "findActivityApplyByPage";

    @Autowired
    private MyBatisDAO myBatisDAO;

    @Override
    public Long addActivityApply(ActivityApply activityApply) {
	activityApply.setCreateTime(new Date());
	activityApply.setUpdateTime(new Date());
	activityApply.setExpired(Boolean.TRUE);
	activityApply.setApplyStatus(ActivityApplyStatus.INITIAL.name());
	myBatisDAO.insert(ADD_ACTIVITY_APPLY, activityApply);
	return activityApply.getId();
    }

    @Override
    public void updateActivityApply(ActivityApply activityApply) {
	activityApply.setUpdateTime(new Date());
	myBatisDAO.update(UPDATE_ACTIVITY_APPLY, activityApply);
    }

    @Override
    public void deleteActivityApply(Long id) {
	ActivityApply activityApply = new ActivityApply();
	activityApply.setId(id);
	activityApply.setExpired(Boolean.FALSE);
	myBatisDAO.update(UPDATE_ACTIVITY_APPLY, activityApply);
    }

    @Override
    public ActivityApply findActivityApplyById(Long id) {
	if (id == null) {
	    return null;
	}
	return (ActivityApply) myBatisDAO.findForObject(FIND_ACTIVITY_APPLY_BY_ID, id);
    }

    @Override
    public ActivityApply findActivityApplyByAccountId(Long activityId, Long accountId) {
	if (activityId == null || accountId == null) {
	    return null;
	}
	Map<String, Object> param = new HashMap<String, Object>();
	param.put("activityId", activityId);
	param.put("accountId", accountId);
	return (ActivityApply) myBatisDAO.findForObject(FIND_ACTIVITY_APPLY_BY_ACCOUNT_ID, param);
    }

    @SuppressWarnings("unchecked")
    @Override
    public Page<ActivityApply> findActivityApplyByPage(Long activityId,int pageNum, int pageSize) {
	Map<String, Object> param = new HashMap<String, Object>();
	param.put("activityId", activityId);
	return myBatisDAO.findForPage(FIND_ACTIVITY_APPLY_BY_PAGE, new PageRequest(pageNum, pageSize, param));
    }

}
