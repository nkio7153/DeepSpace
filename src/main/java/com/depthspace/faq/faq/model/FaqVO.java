package com.depthspace.faq.faq.model;

public class FaqVO {
	private Integer serialid;
	private Integer faqno;
	private String faqname;
	private String faqans;
	
	public FaqVO(Integer serialid, Integer faqno, String faqname, String faqans) {
        this.serialid = serialid;
        this.faqno = faqno;
        this.faqname = faqname;
        this.faqans = faqans;
    }
	
	public Integer getSerialid() {
		return serialid;
	}
	public void setSerialid(Integer serialid) {
		this.serialid = serialid;
	}
	public Integer getFaqno() {
		return faqno;
	}
	public void setFaqno(Integer faqno) {
		this.faqno = faqno;
	}
	public String getFaqname() {
		return faqname;
	}
	public void setFaqname(String faqname) {
		this.faqname = faqname;
	}
	public String getFaqans() {
		return faqans;
	}
	public void setFaqans(String faqans) {
		this.faqans = faqans;
	}
}
