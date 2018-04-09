package com.naran.dubbo.service.activity;

import com.naran.core.entity.activity.ActivityApply;
import com.naran.foundation.mybatis.page.Page;

/**
 * 那然活动报名服务接口
 * 
 * @author zefeng.xu
 */
public interface IActivityApplyService {

    Long addActivityApply(ActivityApply activityApply);

    void updateActivityApply(ActivityApply activityApply);

    void deleteActivityApply(Long id);

    ActivityApply findActivityApplyById(Long id);

    /**
     * 获取（分页）
     * 
     * @return
     */
    Page<ActivityApply> findActivityApplyByPage(Long activityId,int pageNum, int pageSize);

    ActivityApply findActivityApplyByAccountId(Long activityId, Long accountId);

}
