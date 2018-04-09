package com.naran.swing.quartz;

import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.springframework.scheduling.quartz.QuartzJobBean;

import com.lechuu.foundation.spring.adapter.BeanFactory;
import com.naran.swing.util.SensitiveWordUtil;

/**
 * 敏感词定时器
 * @author QuCeng
 */
public class SensitiveWordQuartz extends QuartzJobBean {
	
	private SensitiveWordUtil sensitiveWordUtil = (SensitiveWordUtil)BeanFactory.getInstance()
			.getBean("sensitiveWordUtil");
	
	@Override
	protected void executeInternal(JobExecutionContext context) throws JobExecutionException {
		sensitiveWordUtil.clearSensitiveWordSet();
	}

}
