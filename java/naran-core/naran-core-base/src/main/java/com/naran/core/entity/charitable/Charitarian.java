package com.naran.core.entity.charitable;

import java.math.BigDecimal;

import com.naran.foundation.entity.BaseEntityPO;

public class Charitarian extends BaseEntityPO {

    /**
     * 
     */
    private static final long serialVersionUID = 1L;

    private String charitarianName;

    private String charitarianContent;

    private BigDecimal charitableMoney;

    private String charitarianImg;

    private String charitableDesc;

    public String getCharitarianName() {
	return charitarianName;
    }

    public void setCharitarianName(String charitarianName) {
	this.charitarianName = charitarianName;
    }

    public String getCharitarianContent() {
	return charitarianContent;
    }

    public void setCharitarianContent(String charitarianContent) {
	this.charitarianContent = charitarianContent;
    }

    public BigDecimal getCharitableMoney() {
	return charitableMoney;
    }

    public void setCharitableMoney(BigDecimal charitableMoney) {
	this.charitableMoney = charitableMoney;
    }

    public String getCharitarianImg() {
	return charitarianImg;
    }

    public void setCharitarianImg(String charitarianImg) {
	this.charitarianImg = charitarianImg;
    }

    public String getCharitableDesc() {
	return charitableDesc;
    }

    public void setCharitableDesc(String charitableDesc) {
	this.charitableDesc = charitableDesc;
    }

}