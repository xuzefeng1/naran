package com.naran.foundation.util;

import com.naran.foundation.mybatis.page.PageUtils;

/**
 * 分页接口展示统一返回项
 * @author QuCeng
 */
public class BasePageVO {

	private Integer pageNum; // 当前页
	
	private Integer pageSize = 10; // 每页个数
	
	private Integer totalPageNum; // 最大页
	
	private Integer totalCount; // 总数
	
	public Integer getPageNum() {
		return pageNum;
	}

	public void setPageNum(Integer pageNum) {
		this.pageNum = pageNum;
	}

	public Integer getTotalPageNum() {
		if (null == totalCount) {
			totalCount = 0;
		}
		
		this.totalPageNum = PageUtils.computeLastPageNumber(totalCount, pageSize);
		
		return totalPageNum;
	}

	public Integer getPageSize() {
		return pageSize;
	}

	public void setPageSize(Integer pageSize) {
		this.pageSize = pageSize;
	}

	public Integer getTotalCount() {
		return totalCount;
	}

	public void setTotalCount(Integer totalCount) {
		this.totalCount = totalCount;
	}
	
	public void setPageAll(Integer pageNum,Integer pageSize,Integer totalPageNum,Integer totalCount) {
	    this.pageNum = pageNum;
	    this.totalPageNum =totalPageNum;
	    this.pageSize = pageSize;
	    this.totalCount = totalCount;
	}

}
