package com.depthspace.faq.faqtypes.model;

public class FaqTypesVo {
	private Integer faqNo;
	private String qTypes;
	
	public FaqTypesVo(Integer faqNo, String qTypes) {
        this.faqNo = faqNo;
        this.qTypes = qTypes;
    }

	public Integer getFaqNo() {
		return faqNo;
	}

	public void setFaqNo(Integer faqNo) {
		this.faqNo = faqNo;
	}

	public String getqTypes() {
		return qTypes;
	}

	public void setqTypes(String qTypes) {
		this.qTypes = qTypes;
	}
	
	
}
