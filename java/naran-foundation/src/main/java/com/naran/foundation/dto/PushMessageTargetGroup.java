package com.naran.foundation.dto;

/**
 * 推送目标群体
 * 
 * @author zefeng.xu
 */
public enum PushMessageTargetGroup {
	
	PUSH_TO_SINGLE, // 推送到指定用户
	
	PUSH_TO_LIST, // 推送到指定用户群
	
	PUSH_TO_APP; // 推送到所有用户
}
