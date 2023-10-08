package com.depthspace.faq.faq.model;

public class FaqVO {
	private Integer serialId;
	private Integer faqNo;
	private String faqName;
	private String faqAns;
	
	public FaqVO(Integer serialId, Integer faqNo, String faqName, String faqAns) {
        this.serialId = serialId;
        this.faqNo = faqNo;
        this.faqName = faqName;
        this.faqAns = faqAns;
    }

	public Integer getSerialId() {
		return serialId;
	}

	public void setSerialId(Integer serialId) {
		this.serialId = serialId;
	}

	public Integer getFaqNo() {
		return faqNo;
	}

	public void setFaqNo(Integer faqNo) {
		this.faqNo = faqNo;
	}

	public String getFaqName() {
		return faqName;
	}

	public void setFaqName(String faqName) {
		this.faqName = faqName;
	}

	public String getFaqAns() {
		return faqAns;
	}

	public void setFaqAns(String faqAns) {
		this.faqAns = faqAns;
	}
}
	