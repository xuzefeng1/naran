package com.naran.bd.vo.order;

import java.util.List;

import com.naran.foundation.util.BasePageVO;

/**
 * 展示封装（分页）
 * 
 * @author zefeng.xu
 */
public class StationeryPageVO extends BasePageVO {

    private List<StationeryVO> stationerys;

    public List<StationeryVO> getStationerys() {
	return stationerys;
    }

    public void setStationerys(List<StationeryVO> stationerys) {
	this.stationerys = stationerys;
    }

}
