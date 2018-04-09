package com.naran.core.dao.activity;

import com.naran.core.entity.activity.ActivityApply;
import com.naran.foundation.mybatis.page.Page;

/**
 * 那然活动报名数据访问接口
 * 
 * @author zefeng.xu
 */
public interface ActivityApplyDao {

    Long addActivityApply(ActivityApply activityApply);

    void updateActivityApply(ActivityApply activityApply);

    void deleteActivityApply(Long id);

    ActivityApply findActivityApplyById(Long id);

    ActivityApply findActivityApplyByAccountId(Long activityId, Long accountId);

    Page<ActivityApply> findActivityApplyByPage(Long activityId,int pageNum, int pageSize);

}
