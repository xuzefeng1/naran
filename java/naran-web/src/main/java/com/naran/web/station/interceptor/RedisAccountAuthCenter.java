package com.naran.web.station.interceptor;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Component;

/**
 * REDIS缓存账号中心
 * @author zefeng.xu
 */
@Component
public class RedisAccountAuthCenter {

	private static final String REDIS_ACCOUNT_AUTH_CENTER_KEY = "redis_account_auth_center_key";
	
	@Autowired
	private RedisTemplate<String, Object> redisTemplate;
	
	/**
	 * 登陆成功，SESSION当前员工信息
	 * @param employee
	
	public void sessionEmployee(Employee employee) {
		if (null == employee || null == employee.getId()) {
			return;
		}
		
		redisTemplate.opsForHash().put(REDIS_ACCOUNT_AUTH_CENTER_KEY, employee.getId(), employee);
	} */
	
	/**
	 * 从SESSION拿出当前员工
	 * @param employeeId
	 * @return
	
	public Employee getSessionEmployee(Long employeeId) {
		if (null == employeeId) {
			return null;
		}
		
		return (Employee) redisTemplate.opsForHash().get(REDIS_ACCOUNT_AUTH_CENTER_KEY, employeeId);
	} */
	
	/**
	 * 登出成功，销毁SESSION
	 * @param employeeId
	 */
	public void sessionOut(Long employeeId) {
		if (null == employeeId) {
			return;
		}
		
		redisTemplate.opsForHash().delete(REDIS_ACCOUNT_AUTH_CENTER_KEY, employeeId);
	}
	
	/**
	 * 验证是否登陆
	 * @param employeeId
	 * @return
	
	public boolean authLogin(Long employeeId) {
		Employee employee = getSessionEmployee(employeeId);
		if (null == employee) {
			return false;
		}
		
		return true;
	} */
	
}
