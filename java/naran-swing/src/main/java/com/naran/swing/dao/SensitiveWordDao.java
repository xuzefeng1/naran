package com.naran.swing.dao;

import java.util.List;

import com.naran.swing.entity.message.SensitiveWordPO;
import com.naran.swing.entity.message.SensitiveWordPO.SensitiveType;

/**
 * 短信敏感词（手机号码）数据访问接口
 * @author QuCeng
 */
public interface SensitiveWordDao {

	List<SensitiveWordPO> findSensitiveWordByType(SensitiveType type);
	
}
