package com.depthspace.forum.model.articlestype;

import java.io.Serializable;

public class ArticlesStypeVO implements Serializable {
	private Integer artiTypeId;
	private String artiTypeText;

	public ArticlesStypeVO() {

	}

	public ArticlesStypeVO(Integer artiTypeId, String artiTypeText) {
		this.artiTypeId = artiTypeId;
		this.artiTypeText = artiTypeText;
	}

	public Integer getArtiTypeId() {
		return artiTypeId;
	}

	public void setArtiTypeId(Integer artiTypeId) {
		this.artiTypeId = artiTypeId;
	}

	public String getArtiTypeText() {
		return artiTypeText;
	}

	public void setArtiTypeText(String artiTypeText) {
		this.artiTypeText = artiTypeText;
	}

}
