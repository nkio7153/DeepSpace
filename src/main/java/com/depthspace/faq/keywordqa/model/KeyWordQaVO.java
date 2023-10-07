package com.depthspace.faq.keywordqa.model;

public class KeyWordQaVO {
	private Integer serialid;
	private String kwtypes;
	private String kwans;
	
	public KeyWordQaVO(Integer serialid, String kwtypes, String kwans) {
        this.serialid = serialid;
        this.kwtypes = kwtypes;
        this.kwans = kwans;
    }
	
	public Integer getSerialid() {
		return serialid;
	}
	public void setSerialid(Integer serialid) {
		this.serialid = serialid;
	}
	public String getKwtypes() {
		return kwtypes;
	}
	public void setKwtypes(String kwtypes) {
		this.kwtypes = kwtypes;
	}
	public String getKwans() {
		return kwans;
	}
	public void setKwans(String kwtyans) {
		this.kwans = kwtyans;
	}
}
