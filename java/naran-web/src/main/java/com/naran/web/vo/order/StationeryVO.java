package com.naran.web.vo.order;

import org.springframework.beans.BeanUtils;

import com.naran.core.entity.other.Stationery;
import com.naran.foundation.util.BaseVO;
import com.naran.foundation.util.file.qiniu.QiniuImageUtil;

public class StationeryVO implements BaseVO {

    private Long stationeryId;

    private String stationeryImg;

    private String stationeryTopImg;

    private String stationeryBottomImg;

    private String stationeryTitle;

    public Long getStationeryId() {
	return stationeryId;
    }

    public void setStationeryId(Long stationeryId) {
	this.stationeryId = stationeryId;
    }

    public String getStationeryImg() {
	return stationeryImg;
    }

    public void setStationeryImg(String stationeryImg) {
	this.stationeryImg = stationeryImg;
    }

    public String getStationeryTopImg() {
	return stationeryTopImg;
    }

    public void setStationeryTopImg(String stationeryTopImg) {
	this.stationeryTopImg = stationeryTopImg;
    }

    public String getStationeryBottomImg() {
	return stationeryBottomImg;
    }

    public void setStationeryBottomImg(String stationeryBottomImg) {
	this.stationeryBottomImg = stationeryBottomImg;
    }

    public String getStationeryTitle() {
	return stationeryTitle;
    }

    public void setStationeryTitle(String stationeryTitle) {
	this.stationeryTitle = stationeryTitle;
    }

    @Override
    public void convertPOToVO(Object poObj) {
	if (null == poObj) {
	    return;
	}
	Stationery po = (Stationery) poObj;
	BeanUtils.copyProperties(po, this);
	this.stationeryId = po.getId();
	this.stationeryImg = QiniuImageUtil.newImageUrl(po.getStationeryImg());
	this.stationeryTopImg = QiniuImageUtil.newImageUrl(po.getStationeryTopImg());
	this.stationeryBottomImg = QiniuImageUtil.newImageUrl(po.getStationeryTopImg());
    }

}
