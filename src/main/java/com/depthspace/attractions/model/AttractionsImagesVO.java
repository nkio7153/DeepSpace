package com.depthspace.attractions.model;

import java.io.Serializable;
import java.util.Arrays;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
@Entity
@Table(name="ATTRACTIONS_IMAGES")
public class AttractionsImagesVO implements Serializable{
	@Id
	@Column(name="ATTRACTIONS_IMAGES_ID")
	private Integer attractionsImagesId;
	
	@Column(name="ATTRACTIONS_ID")
	private Integer attractionsId;
	
	@Column(name="ATTRACTIONS_IMG", columnDefinition ="mediumblob")
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
	@Override
	public String toString() {
		return "AttractionsImagesVO [attractionsImagesId=" + attractionsImagesId + ", attractionsId=" + attractionsId
				+ ", attractionsImg=" + Arrays.toString(attractionsImg) + "]";
	}
	
}
