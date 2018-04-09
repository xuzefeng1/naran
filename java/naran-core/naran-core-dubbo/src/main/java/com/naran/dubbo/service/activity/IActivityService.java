package com.naran.dubbo.service.activity;

import com.naran.core.entity.activity.Activity;
import com.naran.foundation.mybatis.page.Page;

/**
 * 那然活动服务接口
 * 
 * @author zefeng.xu
 */
public interface IActivityService {

    Long addActivity(Activity activity);

    void updateActivity(Activity activity);

    void deleteActivity(Long id);

    Activity findActivityById(Long id);

    /**
     * 获取（分页）
     * 
     * @return
     */
    Page<Activity> findActivityByPage(String activityType,int pageNum, int pageSize);

}
