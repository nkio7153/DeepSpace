package com.depthspace.ticket.model;

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
@Table(name="TICKET_IMAGES")
public class TicketImagesVO implements Serializable {

	@Id
	@Column(name="SERIAL_ID" , updatable=false)
	@GeneratedValue(strategy = GenerationType.IDENTITY)	
	private Integer serialId; 
	
    @ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name="TICKET_ID", nullable = false)
	private TicketVO ticket;
	
    @Lob
	@Column(name="IMAGE", columnDefinition = "MEDIUMBLOB")
	private byte[] image; 
	
	@Column(name = "IS_MAIN_IMAGE") //1為主圖，0為多張圖
	private byte isMainImage;

	public TicketImagesVO() {
		super();
		// TODO Auto-generated constructor stub
	}


	public TicketImagesVO(Integer serialId, TicketVO ticket, byte[] image, byte isMainImage) {
		super();
		this.serialId = serialId;
		this.ticket = ticket;
		this.image = image;
		this.isMainImage = isMainImage;
	}

	@Override
	public String toString() {
		return "TicketImagesVO [serialId=" + serialId + ", ticket=" + ticket + ", image=" + Arrays.toString(image)
				+ ", isMainImage=" + isMainImage + "]";
	}


	public Integer getSerialId() {
		return serialId;
	}


	public void setSerialId(Integer serialId) {
		this.serialId = serialId;
	}


	public TicketVO getTicket() {
		return ticket;
	}


	public void setTicket(TicketVO ticket) {
		this.ticket = ticket;
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


	/******JDBC用*******/
	public TicketImagesVO(Integer serialId, Integer ticketId, byte[] image, byte isMainImage) {
		super();
		this.serialId = serialId;
//		this.ticketId = ticketId;
		this.image = image;
		this.isMainImage = isMainImage;
	}


 
}
