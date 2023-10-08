package com.depthspace.attractions.model;

import java.io.Serializable;

public class AttractionsImagesVO implements Serializable{
	private Integer attractionsImagesId;
	private Integer attractionsId;
	private byte[] attractionsImg;
	public AttractionsImagesVO() {
		super();
	}
	public AttractionsImagesVO(Integer attractionsImagesId, Integer attractionsId, byte[] attractionsImg) {
		super();
		this.attractionsImagesId = attractionsImagesId;
		this.attractionsId = attractionsId;
		this.attractionsImg = attractionsImg;
	}
	public Integer getAttractionsImagesId() {
		return attractionsImagesId;
	}
	public void setAttractionsImagesId(Integer attractionsImagesId) {
		this.attractionsImagesId = attractionsImagesId;
	}
	public Integer getAttractionsId() {
		return attractionsId;
	}
	public void setAttractionsId(Integer attractionsId) {
		this.attractionsId = attractionsId;
	}
	public byte[] getAttractionsImg() {
		return attractionsImg;
	}
	public void setAttractionsImg(byte[] attractionsImg) {
		this.attractionsImg = attractionsImg;
	}
	
}
