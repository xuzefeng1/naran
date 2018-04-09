package com.naran.core.dao.content.impl;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.naran.core.dao.content.ContentDao;
import com.naran.core.entity.content.Content;
import com.naran.foundation.mybatis.dao.MyBatisDAO;
import com.naran.foundation.mybatis.page.Page;
import com.naran.foundation.mybatis.page.PageRequest;

/**
 * 
 * @author zefeng.xu
 */
@Repository
public class ContentDaoImpl implements ContentDao {

    private static final String ADD_CONTENT = "addContent";
    private static final String UPDATE_CONTENT = "updateContent";
    private static final String FIND_CONTENT_BY_ID = "findContentById";
    private static final String FIND_CONTENT_BY_PAGE = "findContentByPage";

    @Autowired
    private MyBatisDAO myBatisDAO;

    @Override
    public Long addContent(Content content) {
	content.setCreateTime(new Date());
	content.setUpdateTime(new Date());
	content.setExpired(Boolean.TRUE);
	myBatisDAO.insert(ADD_CONTENT, content);

	return content.getId();
    }

    @Override
    public void updateContent(Content content) {
	content.setUpdateTime(new Date());
	myBatisDAO.update(UPDATE_CONTENT, content);
    }

    @Override
    public void deleteContent(Long id) {
	Content content = new Content();
	content.setId(id);
	content.setExpired(Boolean.FALSE);
	myBatisDAO.update(UPDATE_CONTENT, content);
    }

    @Override
    public Content findContentById(Long id) {
	if (id == null) {
	    return null;
	}
	return (Content) myBatisDAO.findForObject(FIND_CONTENT_BY_ID, id);
    }

    @SuppressWarnings("unchecked")
    @Override
    public Page<Content> findContentByPage(String contentType,int pageNum, int pageSize) {
	Map<String, Object> param = new HashMap<String, Object>();
	param.put("contentType", contentType);
	return myBatisDAO.findForPage(FIND_CONTENT_BY_PAGE, new PageRequest(pageNum, pageSize, param));
    }

}
