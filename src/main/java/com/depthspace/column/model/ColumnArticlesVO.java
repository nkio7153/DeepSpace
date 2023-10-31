package com.depthspace.column.model;

import java.io.Serializable;
import java.sql.Timestamp;
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

import com.depthspace.ticket.model.TicketTypesVO;

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
	private Integer aritId;

	@ManyToOne(fetch = FetchType.EAGER) //多方Many搭配Join，name為自己要映射的表格欄位名、ref為對方要被關聯的表格欄位名
	@JoinColumn(name="COL_TYPE_ID",referencedColumnName = "COL_TYPE_ID")
	private ColumnTypesVO colType;
//	@Column(name="COL_TYPE_ID")
//	private Integer colTypeId;	
	
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



	public Integer getAritId() {
		return aritId;
	}

	public void setAritId(Integer aritId) {
		this.aritId = aritId;
	}

	public ColumnTypesVO getColType() {
		return colType;
	}

	public void setColType(ColumnTypesVO colType) {
		this.colType = colType;
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

	public Timestamp getArticleDate() {
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



	@Override
	public String toString() {
		return "ColumnArticlesVO [aritId=" + aritId + ", colType=" + colType + ", aritTitle=" + aritTitle
				+ ", artiContent=" + artiContent + ", articleDate=" + articleDate + ", adminId=" + adminId
				+ ", artiStatus=" + artiStatus + "]";
	}



	public ColumnArticlesVO(Integer aritId, ColumnTypesVO colType, String aritTitle, String artiContent,
			Timestamp articleDate, Integer adminId, byte artiStatus) {
		super();
		this.aritId = aritId;
		this.colType = colType;
		this.aritTitle = aritTitle;
		this.artiContent = artiContent;
		this.articleDate = articleDate;
		this.adminId = adminId;
		this.artiStatus = artiStatus;
	}


	
}
