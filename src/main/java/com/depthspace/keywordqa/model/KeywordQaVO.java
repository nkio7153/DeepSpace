package com.depthspace.keywordqa.model;

import java.io.Serializable;
import java.util.Objects;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import com.google.gson.annotations.Expose;

public class KeywordQaVO implements java.io.Serializable {
	private Integer serialId;
	private String kwTypes;
	private String kwAns;
	
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
	@Override
	public String toString() {
		return "KeywordQaVO [serialId=" + serialId + ", kwTypes=" + kwTypes + ", kwAns=" + kwAns + "]";
	}

	
}