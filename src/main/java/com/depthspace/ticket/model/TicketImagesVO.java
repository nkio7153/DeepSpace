package com.depthspace.ticket.model;

import java.io.Serializable;
import java.util.Arrays;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="TICKET_IMAGES")
public class TicketImagesVO implements Serializable {

	@Id
	@Column(name="SERIAL_ID" , updatable=false)
	@GeneratedValue(strategy = GenerationType.IDENTITY)	
	private Integer serialId; 
	
	@Column(name="TICKET_ID")
	private Integer ticketId; 
	
	@Column(name="IMAGE", columnDefinition = "mediumblob")
	private byte[] image; 
	
	@Column(name = "IS_MAIN_IMAGE") //1為主圖，0為多張圖
	private byte isMainImage;

	
	public TicketImagesVO() {
		super();
	
	}

	public Integer getSerialId() {
		return serialId;
	}

	public void setSerialId(Integer serialId) {
		this.serialId = serialId;
	}

	public Integer getTicketId() {
		return ticketId;
	}

	public void setTicketId(Integer ticketId) {
		this.ticketId = ticketId;
	}

	public byte[] getImage() {
		return image;
	}

	public void setImage(byte[] image) {
		this.image = image;
	}

	public byte getIsMainImage() {
		return isMainImage;
	}

	public void setIsMainImage(byte isMainImage) {
		this.isMainImage = isMainImage;
	}

	@Override
	public String toString() {
		return "TicketImagesVO [serialId=" + serialId + ", ticketId=" + ticketId + ", image=" + Arrays.toString(image)
				+ ", isMainImage=" + isMainImage + "]";
	}

	/******JDBC用*******/
	public TicketImagesVO(Integer serialId, Integer ticketId, byte[] image, byte isMainImage) {
		super();
		this.serialId = serialId;
		this.ticketId = ticketId;
		this.image = image;
		this.isMainImage = isMainImage;
	}


 
}
