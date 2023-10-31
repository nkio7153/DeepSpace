package com.depthspace.column.model;

import java.io.Serializable;
import java.util.Arrays;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="COLUMN_IMAGES")
public class ColumnImagesVO implements Serializable{
	
	@Id
	@Column(name="COL_IMG_ID" , updatable=false)
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer colImgId;
	
	@Column(name="ARTI_ID")
	private Integer artiId;
	
	@Column(name="COL_IMG", columnDefinition = "mediumblob")
	private byte[] colImg;

	public ColumnImagesVO() {
		super();
		// TODO Auto-generated constructor stub
	}

	public ColumnImagesVO(Integer colImgId, Integer artiId, byte[] colImg) {
		super();
		this.colImgId = colImgId;
		this.artiId = artiId;
		this.colImg = colImg;
	}

	public Integer getColImgId() {
		return colImgId;
	}

	public void setColImgId(Integer colImgId) {
		this.colImgId = colImgId;
	}

	public Integer getArtiId() {
		return artiId;
	}

	public void setArtiId(Integer artiId) {
		this.artiId = artiId;
	}

	public byte[] getColImg() {
		return colImg;
	}

	public void setColImg(byte[] colImg) {
		this.colImg = colImg;
	}

	@Override
	public String toString() {
		return "ColumnImagesVO [colImgId=" + colImgId + ", artiId=" + artiId + ", colImg=" + Arrays.toString(colImg)
				+ "]";
	}
	

	
}
