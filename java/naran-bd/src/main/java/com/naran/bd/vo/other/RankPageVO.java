package com.naran.bd.vo.other;

import java.util.List;

import com.naran.foundation.util.BasePageVO;

public class RankPageVO extends BasePageVO {

    private List<RankVO> ranks;

    public List<RankVO> getRanks() {
	return ranks;
    }

    public void setRanks(List<RankVO> ranks) {
	this.ranks = ranks;
    }

}
