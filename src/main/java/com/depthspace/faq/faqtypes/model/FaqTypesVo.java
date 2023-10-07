package com.depthspace.faq.faqtypes.model;

public class FaqTypesVo {
	private Integer faqno;
	private String qtypes;
	
	public FaqTypesVo(Integer faqno, String qtypes) {
        this.faqno = faqno;
        this.qtypes = qtypes;
    }
	
	public Integer getFaqno() {
		return faqno;
	}
	public void setFaqno(Integer faqno) {
		this.faqno = faqno;
	}
	public String getQtypes() {
		return qtypes;
	}
	public void setQtypes(String qtypes) {
		this.qtypes = qtypes;
	}
}
