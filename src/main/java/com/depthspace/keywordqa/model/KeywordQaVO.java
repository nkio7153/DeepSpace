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
	
	private Integer parentId; // 新增：指向父問題/答案的ID
    private boolean isFinal; // 新增：標誌是否為終端問題/答案
	
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
	public Integer getParentId() {
		return parentId;
	}
	public void setParentId(Integer parentId) {
		this.parentId = parentId;
	}
	public boolean isFinal() {
		return isFinal;
	}
	public void setFinal(boolean isFinal) {
		this.isFinal = isFinal;
	}

	
}