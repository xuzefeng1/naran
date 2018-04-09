package com.naran.core.dao.record;

import com.naran.core.entity.record.CommentRecord;
import com.naran.foundation.mybatis.page.Page;

/**
 * 评论记录数据访问接口
 * 
 * @author zefeng.xu
 */
public interface CommentRecordDao {

    Long addCommentRecord(CommentRecord commentRecord);

    void updateCommentRecord(CommentRecord commentRecord);

    void deleteCommentRecord(Long id);

    CommentRecord findCommentRecordById(Long id);

    Page<CommentRecord> findCommentRecordByPage(Long businessId, String businessType,Long commentedTopId,int pageNum, int pageSize);

    CommentRecord findCommentRecordByAccount(Long businessId, String businessType, Long accountId);

}
