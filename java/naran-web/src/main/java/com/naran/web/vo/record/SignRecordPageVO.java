package com.naran.web.vo.record;

import java.util.List;

import com.naran.foundation.util.BasePageVO;

/**
 * 展示封装（分页）
 * 
 * @author zefeng.xu
 */
public class SignRecordPageVO extends BasePageVO {

    private List<SignRecordVO> signs;

    public List<SignRecordVO> getSigns() {
	return signs;
    }

    public void setSigns(List<SignRecordVO> signs) {
	this.signs = signs;
    }

}
