package com.depthspace.column.model;

import java.io.Serializable;
import java.sql.Timestamp;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="COLUMN_ARTICLES")
public class ColumnArticlesVO implements Serializable{
	@Id
	@Column(name="ARTI_ID" , updatable = false)
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer aritId;
	
	@Column(name="COL_TYPE_ID")
	private Integer colTypeId;
	
	@Column(name="ARTI_TITLE")
	private String aritTitle;
	
	@Column(name="ARTI_CONTENT")
	private String artiContent;
	
	@Column(name="ARTICLE_DATE")
	private Timestamp articleDate;
	
	@Column(name="ADMIN_ID")
	private Integer adminId;	
	
	@Column(name="ARTI_STATUS")
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
