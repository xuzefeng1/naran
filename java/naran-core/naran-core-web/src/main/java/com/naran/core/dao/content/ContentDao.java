package com.naran.core.dao.content;

import com.naran.core.entity.content.Content;
import com.naran.foundation.mybatis.page.Page;

/**
 * 文章数据访问接口
 * 
 * @author zefeng.xu
 */
public interface ContentDao {

    Long addContent(Content content);

    void updateContent(Content content);

    void deleteContent(Long id);

    Content findContentById(Long id);

    Page<Content> findContentByPage(String contentType,int pageNum, int pageSize);

}
