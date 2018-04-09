package com.naran.foundation.mybatis.page;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * @author QuCeng
 */
public class PageUtils {

	private PageUtils() {

	}

	public static int getFirstResult(int pageNumber, int pageSize) {

		if (pageSize <= 0)
			throw new IllegalArgumentException(
					"[pageSize] must great than zero");
		return (pageNumber - 1) * pageSize;
	}

	public static List<Integer> generateLinkPageNumbers(int currentPageNumber,
			int lastPageNumber, int count) {
		List<Integer> linkPageNums = new ArrayList<Integer>();

		if (5 >= lastPageNumber) {
			// 总页数<=5
			for (int i = 1; i <= lastPageNumber; i++) {
				linkPageNums.add(i);
			}
		} else {
			// 总页数>=6， 根据当前页码判定
			
			// 加入当前页前两个页码
			int pre = currentPageNumber - 2;
			if (2 < pre) {
				linkPageNums.add(1);
				linkPageNums.add(2);
				
				linkPageNums.add(currentPageNumber - 2);
				linkPageNums.add(currentPageNumber - 1);
			} else {
				for (int j = 1; j < currentPageNumber; j++) {
					linkPageNums.add(j);
				}
			}
			
			// 加入当前页码
			linkPageNums.add(currentPageNumber);
			
			// 加入当前页后两个页码
			int sur = currentPageNumber + 2;
			if (lastPageNumber >= sur) {
				if (5 >= sur) {
					for (int k = currentPageNumber + 1; k <=5; k++) {
						linkPageNums.add(k);
					}
				} else {
					linkPageNums.add(currentPageNumber + 1);
					linkPageNums.add(currentPageNumber + 2);
				}
			} else {
				linkPageNums.add(currentPageNumber + 1);
			}
		}
		
		return linkPageNums;
		
//		int avg = count / 2;
//
//		int startPageNumber = currentPageNumber - avg;
//		if (startPageNumber <= 0) {
//			startPageNumber = 1;
//		}
//
//		int endPageNumber = startPageNumber + count - 1;
//		if (endPageNumber > lastPageNumber) {
//			endPageNumber = lastPageNumber;
//		}
//
//		if (endPageNumber - startPageNumber < count) {
//			startPageNumber = endPageNumber - count;
//			if (startPageNumber <= 0) {
//				startPageNumber = 1;
//			}
//		}
//
//		java.util.List<Integer> result = new java.util.ArrayList();
//		for (int i = startPageNumber; i <= endPageNumber; i++) {
//			result.add(new Integer(i));
//		}
//		return result;
	}

	public static int computeLastPageNumber(int totalElements, int pageSize) {

		int result = totalElements % pageSize == 0 ? totalElements / pageSize
				: totalElements / pageSize + 1;
		if (result <= 1)
			result = 1;
		return result;
	}

	public static int computePageNumber(int pageNumber, int pageSize,
			int totalElements) {

		if (pageNumber <= 1) {
			return 1;
		}
		if (Integer.MAX_VALUE == pageNumber
				|| pageNumber > computeLastPageNumber(totalElements, pageSize)) { // last
																					// page
			return computeLastPageNumber(totalElements, pageSize);
		}
		return pageNumber;
	}
	
	/**
	 * 根据List 返回当前页结果
	 * @param pageNumber
	 * @param pageSize
	 * @param dataList
	 * @return
	 */
	public static List<?> getResult(int pageNumber, int pageSize,List<?> dataList) {
		if(dataList==null || dataList.size()==0){
			return Collections.EMPTY_LIST;
		}
		List<Object> result = new ArrayList<Object>() ; 
		int len = dataList.size();
		int minValue = (pageNumber-1)*pageSize ; 
		int maxValue = (pageNumber-1)*pageSize+pageSize ; 
		if(maxValue>len){
			maxValue = len ; 
		}
		
		for(int i = minValue; i<maxValue ;i++){
			result.add(dataList.get(i)) ; 
		}
		
		return result ; 
	}
}
