package com.naran.core.entity.company;

import com.naran.foundation.entity.BaseEntityPO;

public class CompanyFootprint extends BaseEntityPO {

    /**
     * 
     */
    private static final long serialVersionUID = 1L;

    private String footprintTitle;

    private String footprintContent;

    private String footprintImgs;

    public String getFootprintTitle() {
	return footprintTitle;
    }

    public void setFootprintTitle(String footprintTitle) {
	this.footprintTitle = footprintTitle;
    }

    public String getFootprintContent() {
	return footprintContent;
    }

    public void setFootprintContent(String footprintContent) {
	this.footprintContent = footprintContent;
    }

    public String getFootprintImgs() {
	return footprintImgs;
    }

    public void setFootprintImgs(String footprintImgs) {
	this.footprintImgs = footprintImgs;
    }

}