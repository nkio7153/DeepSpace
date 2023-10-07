package com.depthspace.column.model;

import java.io.Serializable;

public class ColumnImagesVO implements Serializable{
	
	private Integer colImgId;
	private Integer artiId;
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
	
	
}
