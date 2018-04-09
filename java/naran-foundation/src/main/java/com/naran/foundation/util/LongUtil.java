package com.naran.foundation.util;

/**
 * Long处理工具类
 * @author QuCeng
 */
public class LongUtil {

	/**
	 * 判断Long是否相等
	 * @param first
	 * @param second
	 * @return
	 */
	public static boolean isEquals(Long first, Long second) {
		if (null == first && null == second) {
			return true;
		} else if (null == first || null == second) {
			return false;
		} else {
			return first.longValue() == second.longValue();
		}
	}
	
	/**
	 * 判断Long是否不相等
	 * @param first
	 * @param second
	 * @return
	 */
	public static boolean isNotEquals(Long first, Long second) {
		return !isEquals(first, second);
	}
	
}
