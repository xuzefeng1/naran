package com.naran.dubbo.service.record;

import com.naran.core.entity.record.CommentRecord;
import com.naran.dubbo.response.DubboResponse;
import com.naran.foundation.mybatis.page.Page;

/**
 * 评论记录服务接口
 * 
 * @author zefeng.xu
 */
public interface ICommentRecordService {

    DubboResponse<String> addCommentByOrder(Long accountId, Long orderId, Long commentedId, String commentContent);

    DubboResponse<String> addCommentByContent(Long accountId, Long contentId, Long commentedId, String commentContent);
    
    DubboResponse<String> addCommentByActivity(Long accountId, Long activityId, Long commentedId, String commentContent);

    CommentRecord findCommentRecordById(Long id);

    void deleteCommentRecord(Long id);

    Page<CommentRecord> findCommentRecordByPage(Long businessId, String businessType, Long commentedTopId, int pageNum, int pageSize);

    CommentRecord findCommentRecordByAccount(Long businessId, String businessType, Long accountId);

 
}
