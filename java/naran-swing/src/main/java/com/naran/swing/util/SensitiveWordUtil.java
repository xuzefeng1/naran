package com.naran.swing.util;

import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

import org.apache.commons.collections.CollectionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.lechuu.foundation.util.StringUtil;
import com.naran.swing.dao.SensitiveWordDao;
import com.naran.swing.entity.message.SensitiveWordPO;
import com.naran.swing.entity.message.SensitiveWordPO.SensitiveType;

/**
 * 敏感词处理工具类
 * @author QuCeng
 */
@Component
public class SensitiveWordUtil {

	private static Set<SensitiveWordPO> sensitiveWordSet;

	private static Set<SensitiveWordPO> sensitiveMobileSet;
	
	@Autowired
	private SensitiveWordDao sensitiveWordDao;
	
	/**
	 * 敏感词过滤
	 * @param content 被校验内容
	 * @return 非敏感词
	 */
	public String wordFilter(String content) {
		// 如果原句为空，直接返回
		if (StringUtil.isBlank(content)) {
			return content;
		}

		// 如果不包含敏感词，直接返回
		if (!containsSensitiveWord(content)) {
			return content;
		}

		// 含有敏感词，需要递归过滤
		Iterator<SensitiveWordPO> iter = sensitiveWordSet.iterator();
		while (iter.hasNext()) {
			String sensitiveWord = iter.next().getWord();
			if (sensitiveWord.trim().equals("")) {
				continue;
			}

			if (content.contains(sensitiveWord)) {
				String tempContent = "";
				if (sensitiveWord.length() == 1) {
					// 只有单个字为敏感词
					tempContent = content.replaceAll(sensitiveWord, " ");
				} else {
					char[] ch = sensitiveWord.toCharArray();
					String s = "";
					for (char c : ch) {
						s += c + " ";
					}

					tempContent = content.replaceAll(sensitiveWord, s.trim());
				}

				// 重复判断
				return wordFilter(tempContent);
			}
		}

		return content;
	}

	/**
	 * 内容中是否包含了敏感词
	 * @param content
	 * @return
	 */
	public boolean containsSensitiveWord(String content) {
		// 如果全局变量为空，则从数据库同步
		if (CollectionUtils.isEmpty(sensitiveWordSet)) {
			List<SensitiveWordPO> sensitiveWords = sensitiveWordDao.findSensitiveWordByType(SensitiveType.WORD);
			if (!CollectionUtils.isEmpty(sensitiveWords)) {
				sensitiveWordSet = new HashSet<SensitiveWordPO>();
				for (SensitiveWordPO po : sensitiveWords) {
					sensitiveWordSet.add(po);
				}
			} else {
				return false;
			}
		}
		
		// 同步以后词库仍然为空，则返回false
		if (CollectionUtils.isEmpty(sensitiveWordSet)) {
			return false;
		}
		
		// 词库有内容，则对原句进行包含验证
		for (SensitiveWordPO po : sensitiveWordSet) {
			if (content.contains(po.getWord())) {
				return true;
			}
		}
		
		return false;
	}

	/**
	 * 手机号码是否包含了敏感词（手机号码）
	 * @param mobile
	 * @return
	 */
	public boolean containsSensitiveMobile(String mobile) {
		if (StringUtil.isBlank(mobile)) {
			return true;
		}
		
		// 如果全局变量为空，则从数据库同步
		if (CollectionUtils.isEmpty(sensitiveMobileSet)) {
			List<SensitiveWordPO> sensitiveMobiles = sensitiveWordDao.findSensitiveWordByType(SensitiveType.MOBILE);
			if (CollectionUtils.isNotEmpty(sensitiveMobiles)) {
				sensitiveMobileSet = new HashSet<SensitiveWordPO>();
				for (SensitiveWordPO po : sensitiveMobiles) {
					sensitiveMobileSet.add(po);
				}
			} else {
				return false;
			}
		}
		
		// 同步以后词库仍然为空，则返回false
		if (CollectionUtils.isEmpty(sensitiveMobileSet)) {
			return false;
		}
		
		// 词库有内容，则对原句进行匹配验证
		for (SensitiveWordPO po : sensitiveMobileSet) {
			if (mobile.equalsIgnoreCase(po.getWord())) {
				return true;
			}
		}
		
		return false;
	}
	
	public void clearSensitiveWordSet() {
		if (null != sensitiveWordSet) {
			sensitiveWordSet.clear();
		}
		
		if (null != sensitiveMobileSet) {
			sensitiveMobileSet.clear();
		}
	}
	
}
