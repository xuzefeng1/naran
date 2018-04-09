package com.naran.foundation.util.caculation;

import java.math.BigDecimal;

/**
 * 大数计算器
 * @author quceng
 */
public class BigDecimalCalculationUtil {

	private static final int DEFAULT_LEN = 10;
	
	// 进行加法运算
	public static BigDecimal add(BigDecimal b1, BigDecimal b2) {
		return b1.add(b2);
	}

	// 进行减法运算
	public static BigDecimal sub(BigDecimal b1, BigDecimal b2) {
		return b1.subtract(b2);
	}

	// 进行乘法运算
	public static BigDecimal mul(BigDecimal b1, BigDecimal b2) {
		return b1.multiply(b2);
	}

	// 进行除法运算
	public static BigDecimal div(BigDecimal b1, BigDecimal b2) {
		return b1.divide(b2, DEFAULT_LEN, BigDecimal.ROUND_HALF_UP);
	}

	// 进行四舍五入操作
	public static BigDecimal round(BigDecimal b) {
		// 任何一个数字除以1都是原数字
		// ROUND_HALF_UP是BigDecimal的一个常量，表示进行四舍五入的操作
		return b.divide(new BigDecimal(1), DEFAULT_LEN, BigDecimal.ROUND_HALF_UP);
	}

	// 进行负数操作
	public static BigDecimal opposite(BigDecimal b1) {
		return b1.multiply(new BigDecimal("-1"));
	}

}
