package com.naran.foundation.util;

import java.io.IOException;
import java.io.StringReader;
import java.io.StringWriter;
import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Date;
import java.util.Map;
import java.util.Random;
import java.util.UUID;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import net.sourceforge.pinyin4j.PinyinHelper;
import net.sourceforge.pinyin4j.format.HanyuPinyinCaseType;
import net.sourceforge.pinyin4j.format.HanyuPinyinOutputFormat;
import net.sourceforge.pinyin4j.format.HanyuPinyinToneType;
import net.sourceforge.pinyin4j.format.exception.BadHanyuPinyinOutputFormatCombination;

import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import freemarker.template.Configuration;
import freemarker.template.Template;
import freemarker.template.TemplateException;

/**
 * 字符操作
 * 
 * @author zefeng.xu
 */
public class StringUtil extends StringUtils {

    private static final Logger logger = LoggerFactory.getLogger(StringUtil.class);

    private static char[] hexDigits = { '0', '1', '2', '3', '4', '5', '6', '7', '8', '9', 'a', 'b', 'c', 'd', 'e', 'f' };

    public static final String DEFAULT_BOUND_SYMBOL = ","; // 平台默认连接符（1,2,3）

    public static final String DEFAULT_COMBINER = "&&&"; // 平台默认连接符（1&&2&&3）

    /**
     * 字符串转换成double 数
     * 
     * @param strDouble
     * @return
     */
    public static double toDouble(String strDouble) {
	if (strDouble == null || strDouble.trim().length() == 0) {
	    return 0d;
	}

	try {
	    return Double.parseDouble(strDouble);
	} catch (Exception e) {
	    return 0d;
	}
    }

    /**
     * 字符串转换成int 数
     * 
     * @param strInt
     * @return
     */
    public static int toInt(String strInt) {
	if (strInt == null || strInt.trim().length() == 0) {
	    return 0;
	}

	try {
	    return Integer.parseInt(strInt);
	} catch (Exception e) {
	    return 0;
	}
    }

    /**
     * 把指定的数据转化为16进制格式的字符串
     * 
     * @param data
     *            待转化的数据
     * @return 16进制表示的数据
     */
    public static String toHexString(byte[] data) {

	return toHexString(data, 0, data.length);
    }

    /**
     * 把指定的数据转化为16进制格式的字符串， 如toHexString({8,9,12,4},1,3) = "090C"
     * 
     * @param data
     *            待转化的数据
     * @param beginIndex
     *            需转化的数据的起始索引
     * @param endIndex
     *            需转化的数据的结束索引
     * @return 16进制表示的数据
     */
    public static String toHexString(byte[] data, int beginIndex, int endIndex) {

	if (data == null || beginIndex < 0)
	    return null;
	StringBuilder strBuilder = new StringBuilder();
	for (int i = beginIndex; i < endIndex; i++) {
	    strBuilder.append(hexDigits[data[i] >>> 4 & 0xf]);
	    strBuilder.append(hexDigits[data[i] & 0xf]);
	}
	return strBuilder.toString();
    }

    /**
     * 返回16进制的MDS加密串
     * 
     * @param str
     *            需要加密的字符串
     * @return
     */
    private static String md5Encrypt(String str) {

	if (Validators.isEmpty(str)) {
	    return null;
	}

	try {
	    MessageDigest messageDigest = MessageDigest.getInstance("MD5");
	    messageDigest.reset();

	    return toHexString(messageDigest.digest(str.getBytes("UTF-8")));

	} catch (NoSuchAlgorithmException e) {
	    logger.error("NoSuchAlgorithmException", e);
	    throw new RuntimeException(e);
	} catch (UnsupportedEncodingException e) {
	    // e.printStackTrace();
	    logger.error("UnsupportedEncodingException", e);
	    throw new RuntimeException(e);
	}
    }

    /**
     * 返回16进制的MDS加密串(加密两次)
     * 
     * @param str
     *            需要加密的字符串
     * @return
     */
    public static String md5EncryptDouble(String str) {
	return md5Encrypt(md5Encrypt(str));
    }

    /**
     * 获取一个字符串的拼音码
     * 
     * @param oriStr
     *            字符串
     * @return
     */
    public static String getFirstLetter(String oriStr) {
	if (oriStr == null) {
	    return null;
	}
	StringBuilder builder = new StringBuilder();
	char[] arr = oriStr.toCharArray();
	HanyuPinyinOutputFormat format = new HanyuPinyinOutputFormat();
	format.setCaseType(HanyuPinyinCaseType.UPPERCASE);
	format.setToneType(HanyuPinyinToneType.WITHOUT_TONE);
	for (int i = 0; i < arr.length; i++) {
	    if (arr[i] > 128) {
		try {
		    String[] pinyin = PinyinHelper.toHanyuPinyinStringArray(arr[i], format);
		    if (pinyin != null) {
			builder.append(pinyin[0].charAt(0));
		    }
		} catch (BadHanyuPinyinOutputFormatCombination e) {
		    e.printStackTrace();
		}
	    } else {
		builder.append(arr[i]);
	    }
	}
	return builder.toString().trim();
    }

    /**
     * 字符串处理，如果字符串为空，则返加null,否则返回 str.trim()
     * 
     * @param str
     *            字符串
     * @return
     */
    public static String trimNull(String str) {

	if (Validators.isEmpty(str)) {
	    return null;
	}
	return str.trim();
    }

    public static String trim(String str) {

	return str == null ? "" : str.trim();
    }

    public static String generateLoginToken() {
	String s = UUID.randomUUID().toString();

	return s.replaceAll("-", "");
    }

    /**
     * 规范化金额格式 2个字符串（其实是数值），的和；如addAsDouble("8.00","3.00") = "11.00";
     * 
     * @param firstStr
     * @param secStr
     * @return
     */
    public static String addAsDouble(String firstStr, String secStr) {
	double resultDoubleVal = 0.0;
	double firstDoubleVal = 0.0;
	double secDoubleVal = 0.0;
	String result = null;
	if (firstStr != null && !"".equals(firstStr)) {
	    firstDoubleVal = Double.parseDouble(firstStr);
	}

	if (secStr != null && !"".equals(secStr)) {
	    secDoubleVal = Double.parseDouble(secStr);
	}
	resultDoubleVal = firstDoubleVal + secDoubleVal;
	result = String.valueOf(resultDoubleVal);
	result = getUniversalFeeFormat(result);
	return result;
    }

    /**
     * 将字符串形式的金额统一起来，目前都显示2位小数点
     * 
     * @param feeStr
     * @return
     */
    public static String getUniversalFeeFormat(String feeStr) {
	String resultStr = "";
	if (StringUtils.isBlank(feeStr))
	    return "";

	int dotLoc = feeStr.indexOf(".");
	int length = feeStr.length();

	if (dotLoc == -1) {
	    // 没有小数点，则补充2位
	    resultStr = feeStr + ".00";
	} else if (dotLoc == 0) {
	    // 没有小数点，如.2756或.1
	    resultStr = "0" + feeStr;
	    resultStr = getUniversalFeeFormat(resultStr);
	} else if (length - dotLoc == 2) {
	    // 如3.4，将它转化为3.40
	    resultStr = feeStr + "0";
	} else if (length - dotLoc == 3) {
	    // 如3.40，不需要改变
	    resultStr = feeStr;
	} else if (length - dotLoc > 3) {
	    // 如3.14159265
	    resultStr = feeStr.substring(0, dotLoc + 3);
	} else if (length - dotLoc == 1) {
	    // 如312. 较少见，补充00
	    resultStr = feeStr + "00";
	}

	return resultStr;
    }

    /**
     * 根据分隔符来分割数字串 example:"1-9",解析后结构就是[1,2,3,4,5,6,7,8,9], "1;2;4;5"
     * 分割后结果就是[1,2,4,5], 暂时只支持这两种格式的解析
     * 
     * @param numArea
     * @param delimiter
     * @return
     */
    public static String[] parseNumArea(String numArea) {
	String[] noArray;

	if (numArea.indexOf("-") > 0) {
	    String[] nums = numArea.split("-");

	    int first = Integer.parseInt(nums[0]);
	    int last = Integer.parseInt(nums[1]);

	    noArray = new String[last - first + 1];

	    for (int i = 0, j = first; j <= last; i++, j++) {
		noArray[i] = j + "";
	    }
	} else {
	    noArray = StringUtils.split(numArea, ";");
	}

	return noArray;
    }

    /**
     * 根据输入的字符串，生成指定长度的随机字符串
     * 
     * @param strPool
     * @param length
     * @return
     */
    public static String randomString(String strPool, int length) {
	if (strPool == null || length < 1) {
	    return null;
	}

	Random randGen = new Random();
	char[] numbersAndLetters = (strPool).toCharArray();

	char[] randBuffer = new char[length];
	for (int i = 0; i < randBuffer.length; i++) {
	    randBuffer[i] = numbersAndLetters[randGen.nextInt(strPool.length())];
	}
	return new String(randBuffer);
    }

    public static boolean isDoubleString(String inputStr) {
	try {
	    Double.valueOf(inputStr);
	} catch (Exception e) {
	    return false;
	}

	return true;
    }

    public static boolean isChinese(char c) {
	Character.UnicodeBlock ub = Character.UnicodeBlock.of(c);
	if (ub == Character.UnicodeBlock.CJK_UNIFIED_IDEOGRAPHS || ub == Character.UnicodeBlock.CJK_COMPATIBILITY_IDEOGRAPHS || ub == Character.UnicodeBlock.CJK_UNIFIED_IDEOGRAPHS_EXTENSION_A || ub == Character.UnicodeBlock.CJK_UNIFIED_IDEOGRAPHS_EXTENSION_B || ub == Character.UnicodeBlock.CJK_SYMBOLS_AND_PUNCTUATION || ub == Character.UnicodeBlock.HALFWIDTH_AND_FULLWIDTH_FORMS || ub == Character.UnicodeBlock.GENERAL_PUNCTUATION) {
	    return true;
	}

	return false;
    }

    /**
     * @param prefix
     * @return
     */
    public static String generateUniqueSequence(String prefix) {
	String unique = DateUtil.formatDate(DateUtil.DATE_FORMATS[7], new Date()) + System.nanoTime();

	if (StringUtil.isNotBlank(prefix)) {
	    unique = prefix + unique;
	}

	return unique;
    }

    /**
     * 替换字符串模板中的占位属性
     * 
     * @param values
     * @param strTemplate
     * @return
     */
    public static String generateMessageWithTemplate(Map<String, Object> values, String strTemplate) {
	String resultMessage = null;

	try {
	    Template template = new Template("name", new StringReader(strTemplate), new Configuration());
	    StringWriter writer = new StringWriter();
	    template.process(values, writer);
	    resultMessage = writer.toString();
	} catch (IOException e) {
	    e.printStackTrace();
	} catch (TemplateException e) {
	    e.printStackTrace();
	}

	return resultMessage;
    }

    /**
     * 模糊查询关键字转化加%
     * 
     * @param keyWord
     * @return
     */
    public static String getFuzzyQueryKeyWord(String keyWord) {
	if (StringUtils.isBlank(keyWord)) {
	    return null;
	}
	StringBuffer sb = new StringBuffer();
	sb.append("%");
	for (char c : keyWord.toCharArray()) {
	    sb.append(c + "%");
	}
	return sb.toString();
    }

    /**
     * 是否正确手机号码 要更加准确的匹配手机号码只匹配11位数字是不够的，比如说就没有以144开始的号码段，
     * 故先要整清楚现在已经开放了多少个号码段，国家号码段分配如下：
     * 移动：134、135、136、137、138、139、150、151、157(TD)、158、159、187、188
     * 联通：130、131、132、152、155、156、185、186 电信：133、153、180、189、（1349卫通）
     * 
     * @param mobile
     * @return
     */
    public static boolean isMobile(String mobile) {
	if (StringUtil.isBlank(mobile)) {
	    return false;
	}

	Pattern p = null;
	Matcher m = null;

	boolean b = false;

	p = Pattern.compile("^[1][3,4,5,6,7,8,9][0-9]{9}$"); // 验证手机号
	m = p.matcher(mobile);
	b = m.matches();

	return b;
    }

    public static void main(String[] args) {
	// System.out.println(StringUtil.md5Encrypt("606411&3552482"));
	// System.out.println(StringUtil.md5EncryptDouble("123456"));
	
	// System.out.println(randomString("CSKH1", 6));
	System.out.println(String.format("%010d", 11));
	// System.out.println(isMobile(null));
    }
}
