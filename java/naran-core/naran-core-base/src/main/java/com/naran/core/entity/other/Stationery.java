package com.naran.core.entity.other;

import com.naran.foundation.entity.BaseEntityPO;

public class Stationery extends BaseEntityPO {

    /**
     * 
     */
    private static final long serialVersionUID = 1L;

    private String stationeryTitle;

    private String stationeryImg;

    private String stationeryTopImg;

    private String stationeryBottomImg;

    public String getStationeryTitle() {
	return stationeryTitle;
    }

    public void setStationeryTitle(String stationeryTitle) {
	this.stationeryTitle = stationeryTitle;
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

}