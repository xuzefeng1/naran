package com.naran.bd.vo.company;

import java.util.List;

import com.naran.foundation.util.BasePageVO;

public class CompanyPageVO extends BasePageVO {

    private List<CompanyVO> companys;

    public List<CompanyVO> getCompanys() {
	return companys;
    }

    public void setCompanys(List<CompanyVO> companys) {
	this.companys = companys;
    }

}
