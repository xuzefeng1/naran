package com.naran.core.entity.other;

import com.naran.foundation.entity.BaseEntityPO;

public class Topline extends BaseEntityPO {

    /**
     * 
     */
    private static final long serialVersionUID = 1L;

    private String toplineTitle;

    private String toplineContent;

    private String toplineType;

    public String getToplineTitle() {
	return toplineTitle;
    }

    public void setToplineTitle(String toplineTitle) {
	this.toplineTitle = toplineTitle;
    }

    public String getToplineContent() {
	return toplineContent;
    }

    public void setToplineContent(String toplineContent) {
	this.toplineContent = toplineContent;
    }

    public String getToplineType() {
	return toplineType;
    }

    public void setToplineType(String toplineType) {
	this.toplineType = toplineType;
    }

}