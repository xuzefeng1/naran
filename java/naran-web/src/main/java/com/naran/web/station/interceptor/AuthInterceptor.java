package com.naran.web.station.interceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import com.naran.foundation.util.ResultMessageBuilder;
import com.naran.web.controller.base.AppCode;
import com.naran.web.controller.base.BaseController;

/**
 * 账号统一验证拦截器
 * 
 * @author zefeng.xu
 */
public class AuthInterceptor extends BaseController implements HandlerInterceptor {

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {

	if (!AccountAuthCenter.authLogin(request)) {
	    if (request.getHeader("x-requested-with") != null && request.getHeader("x-requested-with").equalsIgnoreCase("XMLHttpRequest")) // 如果是ajax请求响应头会有，x-requested-with；
	    {
		response.setHeader("sessionstatus", "timeout"); // 在响应头设置session状态
		writeAjaxJSONResponse(ResultMessageBuilder.build(AppCode.GO_TO_LOGIN_CODE, "登录过期 ！"), response);
	    } else {
		writeAjaxJSONResponse(ResultMessageBuilder.build(AppCode.GO_TO_LOGIN_CODE, "请重新登录！"), response);
		//response.sendRedirect(request.getContextPath() + "/login");
	    }
	    return false;
	}
	return true;
    }

    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {
    }

    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {
	// TODO Auto-generated method stub

    }

}
