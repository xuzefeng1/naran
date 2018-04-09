package com.naran.dubbo.service.content;

import com.naran.core.entity.content.Content;
import com.naran.foundation.mybatis.page.Page;

/**
 * 文章服务接口
 * 
 * @author zefeng.xu
 */
public interface IContentService {

    Long addContent(Content content);

    void updateContent(Content content);

    void deleteContent(Long id);

    Content findContentById(Long id);

    /**
     * 获取（分页）
     * 
     * @return
     */
    Page<Content> findContentByPage(String contentType, int pageNum, int pageSize);

}
