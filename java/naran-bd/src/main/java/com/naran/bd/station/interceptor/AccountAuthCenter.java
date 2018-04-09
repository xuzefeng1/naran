package com.naran.bd.station.interceptor;

import javax.servlet.http.HttpServletRequest;

import com.naran.core.entity.account.Account;
import com.naran.foundation.exception.NoLoginException;

/**
 * 平台账号统一验证中心
 * @author zefeng.xu
 */
public class AccountAuthCenter {

	private static final String SESSION_ACCOUNT = "session_account"; // 当前用户
	
	/**
	 * 登陆成功，SESSION当前员工信息
	 * @param request
	 * @param 
	 */
	public static void sessionAccount(HttpServletRequest request, Account account) {
		if (null == account) {
			return;
		}
		
		request.getSession().setAttribute(SESSION_ACCOUNT, account);
	}
	
	/**
	 * 从SESSION拿出当前用户
	 * @param request
	 * @return
	 */
	public static Account getSessionAccount(HttpServletRequest request) {
		return (Account) request.getSession().getAttribute(SESSION_ACCOUNT);
	}
	
	/**
	 * 登出成功，销毁SESSION
	 * @param request
	 */
	public static void sessionOut(HttpServletRequest request) {
		request.getSession().invalidate();
	}
	
	/**
	 * 验证是否登陆
	 * @param request
	 * @return
	 */
	public static boolean authLogin(HttpServletRequest request) throws NoLoginException {
		Account account = (Account) request.getSession().getAttribute(SESSION_ACCOUNT);
		if (null == account) {
			return false;
		}
		
		return true;
	}
	
}
