package com.naran.core.dao.activity;

import com.naran.core.entity.activity.Activity;
import com.naran.foundation.mybatis.page.Page;

/**
 * 那然活动数据访问接口
 * 
 * @author zefeng.xu
 */
public interface ActivityDao {

    Long addActivity(Activity activity);

    void updateActivity(Activity activity);

    void deleteActivity(Long id);

    Activity findActivityById(Long id);

    Page<Activity> findActivityByPage(String activityType,int pageNum, int pageSize);

}
