package com.naran.core.entity.charitable;

import java.math.BigDecimal;
import java.util.Date;

import com.naran.foundation.entity.BaseEntityPO;

public class CharitableRecord extends BaseEntityPO {

    /**
     * 
     */
    private static final long serialVersionUID = 1L;

    private Long charitarianId;

    private BigDecimal charitableMoney;

    private String charitarianName;

    private Date charitableDay;

    public Long getCharitarianId() {
	return charitarianId;
    }

    public void setCharitarianId(Long charitarianId) {
	this.charitarianId = charitarianId;
    }

    public BigDecimal getCharitableMoney() {
	return charitableMoney;
    }

    public void setCharitableMoney(BigDecimal charitableMoney) {
	this.charitableMoney = charitableMoney;
    }

    public String getCharitarianName() {
	return charitarianName;
    }

    public void setCharitarianName(String charitarianName) {
	this.charitarianName = charitarianName;
    }

    public Date getCharitableDay() {
	return charitableDay;
    }

    public void setCharitableDay(Date charitableDay) {
	this.charitableDay = charitableDay;
    }

}