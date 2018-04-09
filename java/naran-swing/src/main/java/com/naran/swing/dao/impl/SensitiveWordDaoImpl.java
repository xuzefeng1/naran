package com.naran.swing.dao.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.lechuu.foundation.mybatis.dao.MyBatisDAO;
import com.naran.swing.dao.SensitiveWordDao;
import com.naran.swing.entity.message.SensitiveWordPO;
import com.naran.swing.entity.message.SensitiveWordPO.SensitiveType;

/**
 * 短信敏感词（手机号码）数据访问
 * @author QuCeng
 */
@Repository
public class SensitiveWordDaoImpl implements SensitiveWordDao {

	private static final String FIND_SENSITIVE_WORD_BY_TYPE = "findSensitiveWordByType";
	
	@Autowired
	private MyBatisDAO myBatisDAO;
	
	@Override
	public List<SensitiveWordPO> findSensitiveWordByType(SensitiveType type) {
		if (null == type) {
			return null;
		}
		
		return myBatisDAO.findForList(FIND_SENSITIVE_WORD_BY_TYPE, type.name());
	}

}
