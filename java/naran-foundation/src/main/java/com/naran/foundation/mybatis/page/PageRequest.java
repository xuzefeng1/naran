package com.naran.foundation.mybatis.page;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;

/**
 * 分页请求信息
 * @author QuCeng
 */
public class PageRequest implements Serializable {

	private static final long serialVersionUID = 9092186838918641382L;

	/**
	 * 过滤参数
	 */
	@SuppressWarnings("rawtypes")
	private Map filters;

	/**
	 * 页号码
	 */
	private int pageNumber;

	/**
	 * 分页大小
	 */
	private int pageSize;

	public PageRequest() {

		this(0, 0, null);
	}

	@SuppressWarnings("rawtypes")
	public PageRequest(int pageNumber, int pageSize) {

		this(pageNumber, pageSize, new HashMap());
	}

	@SuppressWarnings("rawtypes")
	public PageRequest(int pageNumber, int pageSize, Map filters) {

		this.pageNumber = pageNumber;
		this.pageSize = pageSize;
		this.filters = filters;
	}

	@SuppressWarnings("rawtypes")
	public Map getFilters() {

		return filters;
	}

	@SuppressWarnings("rawtypes")
	public void setFilters(Map filters) {

		this.filters = filters;
	}

	public int getPageNumber() {

		return pageNumber;
	}

	public void setPageNumber(int pageNumber) {

		this.pageNumber = pageNumber;
	}

	public int getPageSize() {

		return pageSize;
	}

	public void setPageSize(int pageSize) {

		this.pageSize = pageSize;
	}

	public void setPageNumberAndSize(int start, int limit) {

		this.pageSize = limit;
		this.pageNumber = start / limit;
	}
}
