package com.depthspace.column.model;

import java.io.Serializable;
import java.sql.Timestamp;
import java.util.Date;

public class ColumnArticlesVO implements Serializable{

	private Integer aritId;
	private Integer colTypeId;
	private String aritTitle;
	private String artiContent;
	private Timestamp articleDate;
	private Integer adminId;	
	private byte artiStatus;
	
	public ColumnArticlesVO() {
		super();
		// TODO Auto-generated constructor stub
	}

	public ColumnArticlesVO(Integer aritId, Integer colTypeId, String aritTitle, String artiContent, Timestamp articleDate,
			Integer adminId, byte artiStatus) {
		super();
		this.aritId = aritId;
		this.colTypeId = colTypeId;
		this.aritTitle = aritTitle;
		this.artiContent = artiContent;
		this.articleDate = articleDate;
		this.adminId = adminId;
		this.artiStatus = artiStatus;
	}

	public Integer getAritId() {
		return aritId;
	}

	public void setAritId(Integer aritId) {
		this.aritId = aritId;
	}

	public Integer getColTypeId() {
		return colTypeId;
	}

	public void setColTypeId(Integer colTypeId) {
		this.colTypeId = colTypeId;
	}

	public String getAritTitle() {
		return aritTitle;
	}

	public void setAritTitle(String aritTitle) {
		this.aritTitle = aritTitle;
	}

	public String getArtiContent() {
		return artiContent;
	}

	public void setArtiContent(String artiContent) {
		this.artiContent = artiContent;
	}

	public Date getArticleDate() {
		return articleDate;
	}

	public void setArticleDate(Timestamp articleDate) {
		this.articleDate = articleDate;
	}

	public Integer getAdminId() {
		return adminId;
	}

	public void setAdminId(Integer adminId) {
		this.adminId = adminId;
	}

	public byte getArtiStatus() {
		return artiStatus;
	}

	public void setArtiStatus(byte artiStatus) {
		this.artiStatus = artiStatus;
	}
	
	
	
}
