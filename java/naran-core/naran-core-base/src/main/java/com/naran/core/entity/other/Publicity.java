package com.naran.core.entity.other;

import com.naran.foundation.entity.BaseEntityPO;

public class Publicity extends BaseEntityPO {

    /**
     * 
     */
    private static final long serialVersionUID = 1L;

    private String publicityType;

    private String publicityName;

    private String publicityImg;

    private String publicityCategory;

    public String getPublicityType() {
	return publicityType;
    }

    public void setPublicityType(String publicityType) {
	this.publicityType = publicityType;
    }

    public String getPublicityName() {
	return publicityName;
    }

    public void setPublicityName(String publicityName) {
	this.publicityName = publicityName;
    }

    public String getPublicityImg() {
	return publicityImg;
    }

    public void setPublicityImg(String publicityImg) {
	this.publicityImg = publicityImg;
    }

    public String getPublicityCategory() {
	return publicityCategory;
    }

    public void setPublicityCategory(String publicityCategory) {
	this.publicityCategory = publicityCategory;
    }

}
