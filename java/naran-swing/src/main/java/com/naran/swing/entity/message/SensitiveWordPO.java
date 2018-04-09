package com.naran.swing.entity.message;

import com.lechuu.foundation.entity.BaseEntityPO;

/**
 * 短信敏感词（手机号码）
 * @author QuCeng
 */
public class SensitiveWordPO extends BaseEntityPO {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private SensitiveType type; // 敏感词类型 
	
	private String word; // 敏感词（手机号码）

	public SensitiveType getType() {
		return type;
	}

	public void setType(SensitiveType type) {
		this.type = type;
	}

	public String getWord() {
		return word;
	}

	public void setWord(String word) {
		this.word = word;
	}
	
	/**
	 * 敏感词类型（内容敏感OR手机号码敏感）
	 * @author QuCeng
	 */
	public enum SensitiveType {
		WORD, // 内容
		
		MOBILE; // 手机号码
	}
	
}
