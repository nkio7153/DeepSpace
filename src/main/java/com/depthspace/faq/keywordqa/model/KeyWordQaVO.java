package com.depthspace.faq.keywordqa.model;

public class KeyWordQaVO {
	private Integer serialId;
	private String kwTypes;
	private String kwAns;
	
	public KeyWordQaVO(Integer serialId, String kwTypes, String kwAns) {
        this.serialId = serialId;
        this.kwTypes = kwTypes;
        this.kwAns = kwAns;
    }

	public Integer getSerialId() {
		return serialId;
	}

	public void setSerialId(Integer serialId) {
		this.serialId = serialId;
	}

	public String getKwTypes() {
		return kwTypes;
	}

	public void setKwTypes(String kwTypes) {
		this.kwTypes = kwTypes;
	}

	public String getKwAns() {
		return kwAns;
	}

	public void setKwAns(String kwAns) {
		this.kwAns = kwAns;
	}
	
	
}
