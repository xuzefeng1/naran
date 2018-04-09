package com.naran.web.vo.content;

import java.util.List;

import com.naran.foundation.util.BasePageVO;

/**
 * 展示封装（分页）
 * 
 * @author zefeng.xu
 */
public class ContentPageVO extends BasePageVO {

    private List<ContentVO> contents;

    public List<ContentVO> getContents() {
	return contents;
    }

    public void setContents(List<ContentVO> contents) {
	this.contents = contents;
    }

}
