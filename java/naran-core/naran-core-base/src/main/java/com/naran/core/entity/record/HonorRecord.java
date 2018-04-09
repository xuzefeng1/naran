package com.naran.core.entity.record;

import com.naran.foundation.entity.BaseEntityPO;

public class HonorRecord extends BaseEntityPO {

    /**
     * 
     */
    private static final long serialVersionUID = 1L;

    private Long businessId;

    private String businessType;

    private String honorName;

    private String honorCode;

    public Long getBusinessId() {
	return businessId;
    }

    public void setBusinessId(Long businessId) {
	this.businessId = businessId;
    }

    public String getBusinessType() {
	return businessType;
    }

    public void setBusinessType(String businessType) {
	this.businessType = businessType;
    }

    public String getHonorName() {
	return honorName;
    }

    public void setHonorName(String honorName) {
	this.honorName = honorName;
    }

    public String getHonorCode() {
	return honorCode;
    }

    public void setHonorCode(String honorCode) {
	this.honorCode = honorCode;
    }

}