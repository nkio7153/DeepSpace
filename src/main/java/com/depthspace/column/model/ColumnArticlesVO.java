package com.depthspace.column.model;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.hibernate.annotations.CreationTimestamp;

import com.depthspace.admin.model.AdminVO;

/**
 * @author Tibame_T14
 *
 */
@Entity
@Table(name="COLUMN_ARTICLES")
public class ColumnArticlesVO implements Serializable{
	@Id
	@Column(name="ARTI_ID" , updatable = false)
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer artiId;

	@ManyToOne(fetch = FetchType.EAGER) //多方Many搭配Join，name為自己要映射的表格欄位名、ref為對方要被關聯的表格欄位名
	@JoinColumn(name="COL_TYPE_ID",referencedColumnName = "COL_TYPE_ID")
	private ColumnTypesVO colType;
//	@Column(name="COL_TYPE_ID")
//	private Integer colTypeId;	
	
	@Column(name="ARTI_TITLE")
	private String artiTitle;
	
	@Column(name="ARTI_CONTENT")
	private String artiContent;
	
	@CreationTimestamp
	@Column(name="ARTICLE_DATE", updatable = false, nullable = false)
	@Temporal(TemporalType.TIMESTAMP)
	private Date articleDate;
		
	@ManyToOne(fetch = FetchType.EAGER) //多方Many搭配Join，name為自己要映射的表格欄位名、ref為對方要被關聯的表格欄位名
	@JoinColumn(name="ADMIN_ID",referencedColumnName = "ADMIN_ID")
	private AdminVO admin;	
//	@Column(name="ADMIN_ID")
//	private Integer adminId;	
	
	@Column(name="ARTI_STATUS")
	private byte artiStatus;

	public ColumnArticlesVO() {
		super();
		// TODO Auto-generated constructor stub
	}

	public ColumnArticlesVO(Integer artiId, ColumnTypesVO colType, String artiTitle, String artiContent,
			Date articleDate, AdminVO admin, byte artiStatus) {
		super();
		this.artiId = artiId;
		this.colType = colType;
		this.artiTitle = artiTitle;
		this.artiContent = artiContent;
		this.articleDate = articleDate;
		this.admin = admin;
		this.artiStatus = artiStatus;
	}

	public Integer getArtiId() {
		return artiId;
	}

	public void setArtiId(Integer artiId) {
		this.artiId = artiId;
	}

	public ColumnTypesVO getColType() {
		return colType;
	}

	public void setColType(ColumnTypesVO colType) {
		this.colType = colType;
	}

	public String getArtiTitle() {
		return artiTitle;
	}

	public void setArtiTitle(String artiTitle) {
		this.artiTitle = artiTitle;
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

	public void setArticleDate(Date articleDate) {
		this.articleDate = articleDate;
	}

	public AdminVO getAdmin() {
		return admin;
	}

	public void setAdmin(AdminVO admin) {
		this.admin = admin;
	}

	public byte getArtiStatus() {
		return artiStatus;
	}

	public void setArtiStatus(byte artiStatus) {
		this.artiStatus = artiStatus;
	}




	
}