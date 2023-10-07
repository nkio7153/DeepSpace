package com.depthspace.ticket.model;

import java.io.Serializable;
import java.sql.Date;

public class TicketVO implements Serializable{

	private Integer ticketId;
	private Integer ticketTypeId;
	private String ticketName;
	private String description;
	private Integer price;
	private Integer stock;
	private byte status;
	private Date  publishedDate;
	private Integer totalStarRatings;
	private Integer totalStars;

	    
		
	public TicketVO() {
		super();
		// TODO Auto-generated constructor stub
	}

	public TicketVO(Integer ticketId, Integer ticketTypeId, String ticketName, String description, Integer price,
			Integer stock,  byte status, Date publishedDate, Integer totalStarRatings, Integer totalStars) {
		super();
		this.ticketId = ticketId;
		this.ticketTypeId = ticketTypeId;
		this.ticketName = ticketName;
		this.description = description;
		this.price = price;
		this.stock = stock;
		this.status = status;
		this.publishedDate = publishedDate;
		this.totalStarRatings = totalStarRatings;
		this.totalStars = totalStars;
	}
	
	public Integer getTicketId() {
		return ticketId;
	}
	public void setTicketId(Integer ticketId) {
		this.ticketId = ticketId;
	}
	public Integer getTicketTypeId() {
		return ticketTypeId;
	}
	public void setTicketTypeId(Integer ticketTypeId) {
		this.ticketTypeId = ticketTypeId;
	}
	public String getTicketName() {
		return ticketName;
	}
	public void setTicketName(String ticketName) {
		this.ticketName = ticketName;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public Integer getPrice() {
		return price;
	}
	public void setPrice(Integer price) {
		this.price = price;
	}
	public Integer getStock() {
		return stock;
	}
	public void setStock(Integer stock) {
		this.stock = stock;
	}
	public byte getStatus() {
		return status;
	}
	public void setStatus(byte status) {
		this.status = status;
	}
	public Date getPublishedDate() {
		return publishedDate;
	}
	public void setPublishedDate(Date publishedDate) {
		this.publishedDate = publishedDate;
	}
	public Integer getTotalStarRatings() {
		return totalStarRatings;
	}
	public void setTotalStarRatings(Integer totalStarRatings) {
		this.totalStarRatings = totalStarRatings;
	}
	public Integer getTotalStars() {
		return totalStars;
	}
	public void setTotalStars(Integer totalStars) {
		this.totalStars = totalStars;
	}
	
	
}
