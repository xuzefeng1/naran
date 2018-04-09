package com.naran.web.filter;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.perf4j.StopWatch;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.naran.foundation.util.ResultMessageBuilder;
import com.naran.web.controller.base.AppCode;
import com.naran.web.controller.base.BaseController;

/**
 * 过滤器
 * 
 * @author zefeng.xu
 */
public class NaranFilter extends BaseController implements Filter {

    /**
     * 系统错误日志
     */
    protected final Logger error_logger = LoggerFactory.getLogger("ERROR_LOGGER");

    /**
     * 性能日志
     */
    protected final Logger performance_logger = LoggerFactory.getLogger("PERFORMANCE_LOGGER");

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
	// TODO Auto-generated method stub
    }

    @Override
    public void doFilter(ServletRequest sReq, ServletResponse sRes, FilterChain chain) throws IOException, ServletException {
	StopWatch stopWatch = new StopWatch();

	HttpServletRequest request = (HttpServletRequest) sReq;
	HttpServletResponse response = (HttpServletResponse) sRes;
	HttpSession session = request.getSession();

	// 如果session非空
	if (null != session) {
	    try {
		chain.doFilter(sReq, sRes);
	    } catch (Exception e) {
		error_logger.error("发生系统错误:", e);
		writeAjaxJSONResponse(ResultMessageBuilder.build(AppCode.FAIL_CODE, "系统错误！"), response);
	    }
	}

	performance_logger.info(stopWatch.stop(request.getRequestURL().toString()));
    }

    @Override
    public void destroy() {
	// TODO Auto-generated method stub
    }

}
