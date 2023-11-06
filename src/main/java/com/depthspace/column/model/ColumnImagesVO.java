package com.depthspace.column.model;

import java.io.Serializable;
import java.util.Arrays;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.Lob;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "COLUMN_IMAGES")
public class ColumnImagesVO implements Serializable {

	@Id
	@Column(name = "COL_IMG_ID", updatable = false)
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer colImgId;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name="ARTI_ID", nullable = false)
	private ColumnArticlesVO columnArticles;
//	@Column(name = "ARTI_ID")
//	private Integer artiId;

	@Lob
	@Column(name = "COL_IMG", columnDefinition = "MEDIUMBLOB")
	private byte[] colImg;

	@Column(name = "IS_MAIN_IMAGE") // 1為主圖，0為多張圖
	private byte isMainImage;

	public ColumnImagesVO() {
		super();
		// TODO Auto-generated constructor stub
	}

	@Override
	public String toString() {
		return "ColumnImagesVO [colImgId=" + colImgId + ", columnArticles=" + columnArticles + ", colImg="
				+ Arrays.toString(colImg) + ", isMainImage=" + isMainImage + "]";
	}

	public Integer getColImgId() {
		return colImgId;
	}

	public void setColImgId(Integer colImgId) {
		this.colImgId = colImgId;
	}

	public ColumnArticlesVO getColumnArticles() {
		return columnArticles;
	}

	public void setColumnArticles(ColumnArticlesVO columnArticles) {
		this.columnArticles = columnArticles;
	}

	public byte[] getColImg() {
		return colImg;
	}

	public void setColImg(byte[] colImg) {
		this.colImg = colImg;
	}

	public byte getIsMainImage() {
		return isMainImage;
	}

	public void setIsMainImage(byte isMainImage) {
		this.isMainImage = isMainImage;
	}

	public ColumnImagesVO(Integer colImgId, ColumnArticlesVO columnArticles, byte[] colImg, byte isMainImage) {
		super();
		this.colImgId = colImgId;
		this.columnArticles = columnArticles;
		this.colImg = colImg;
		this.isMainImage = isMainImage;
	}


	
}