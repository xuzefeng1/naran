package com.naran.web.vo.activity;

import java.util.List;

import com.naran.foundation.util.BasePageVO;

/**
 * 分页展示封装
 * 
 * @author zefeng.xu
 */
public class ActivityPageVO extends BasePageVO {

    private List<ActivitySimpleVO> activitys;

    public List<ActivitySimpleVO> getActivitys() {
	return activitys;
    }

    public void setActivitys(List<ActivitySimpleVO> activitys) {
	this.activitys = activitys;
    }

}
