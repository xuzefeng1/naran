package com.naran.web.param;

/**
 * 平台接口基本入参封装（分页）
 * @author zefeng.xu
 */
public class BasePageParam extends BaseParam {

	private static final int DEFAULT_PAGE_NUM = 1;
	
	private static final int DEFAULT_PAGE_SIZE = 10;
	
	private int pageNum; // 页码
	
	private int pageSize; // 每页大小

	public int getPageNum() {
		return 0 == pageNum ? DEFAULT_PAGE_NUM : pageNum;
	}

	public void setPageNum(int pageNum) {
		this.pageNum = pageNum;
	}

	public int getPageSize() {
		return 0 == pageSize ? DEFAULT_PAGE_SIZE : pageSize;
	}

	public void setPageSize(int pageSize) {
		this.pageSize = pageSize;
	}
	
}
