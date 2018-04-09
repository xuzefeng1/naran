package com.naran.core.service.impl.content;

import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.naran.core.dao.content.ContentDao;
import com.naran.core.entity.content.Content;
import com.naran.dubbo.service.content.IContentService;
import com.naran.foundation.mybatis.page.Page;

/**
 * 
 * @author zefeng.xu
 */
@Transactional
@Service("contentService")
public class ContentServiceImpl implements IContentService {

    @Autowired
    private ContentDao contentDao;

    @Override
    public Long addContent(Content content) {
	if (content == null) {
	    return null;
	}
	content.setCreateTime(new Date());
	content.setUpdateTime(new Date());
	return contentDao.addContent(content);
    }

    @Override
    public void updateContent(Content content) {
	if (content == null || content.getId() == null) {
	    return;
	}
	content.setUpdateTime(new Date());
	contentDao.updateContent(content);

    }

    @Override
    public void deleteContent(Long id) {
	if (id == null) {
	    return;
	}
	contentDao.deleteContent(id);

    }

    @Override
    public Content findContentById(Long id) {
	if (id == null) {
	    return null;
	}
	return contentDao.findContentById(id);
    }

    @Override
    public Page<Content> findContentByPage(String contentType,int pageNum, int pageSize) {

	return contentDao.findContentByPage(contentType,pageNum, pageSize);
    }

}
