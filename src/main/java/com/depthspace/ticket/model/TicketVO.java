package com.depthspace.ticket.model;

import java.io.Serializable;
import java.sql.Timestamp;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name="TICKET")
public class TicketVO implements Serializable{

	@Id
	@Column(name="TICKET_ID",updatable = false)
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer ticketId;
	
	//fetch為存取，不立即跟著關聯
	@ManyToOne(fetch = FetchType.LAZY) //多方Many搭配Join，name為自己要映射的表格欄位名、ref為對方要被關聯的表格欄位名
	@JoinColumn(name="TICKET_TYPE_ID",referencedColumnName = "TICKET_TYPE_ID")
	private TicketTypesVO ticketType; //新增被關聯的類別自取名
//	@Column(name="TICKET_TYPE_ID")
//	private Integer ticketTypeId;
	
	@Column(name="TICKET_NAME")
	private String ticketName;
	
	@Column(name="DESCRIPTION")
	private String description;
	
	@Column(name="PRICE")
	private Integer price;
	
	@Column(name="STOCK")
	private Integer stock;
	
	@Column(name="VALID_DAYS")
	private Integer validDays;
	
	@Column(name="STATUS")
	private byte status;
	
	@Column(name="PUBLISHED_DATE")
	private Timestamp publishedDate;
	
	@Column(name="TOTAL_STAR_RATINGS")
	private Integer totalStarRatings;
	
	@Column(name="TOTAL_STARS")
	private Integer totalStars;
	
	@Column(name="AREA_ID")
    private Integer areaId;  
    
	@Column(name="ADDRESS")
    private String address;  
    
	@Column(name="LONGITUDE")
    private Double longitude;  
    
	@Column(name="LATITUDE")
    private Double latitude;

	
	//不用不帶任何參數的建構子、也不用所有物件初始化
	//加上get/set/toString's methods
	
	public Integer getTicketId() {
		return ticketId;
	}

	public void setTicketId(Integer ticketId) {
		this.ticketId = ticketId;
	}

	public TicketTypesVO getTicketType() {
		return ticketType;
	}

	public void setTicketType(TicketTypesVO ticketType) {
		this.ticketType = ticketType;
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

	public Integer getValidDays() {
		return validDays;
	}

	public void setValidDays(Integer validDays) {
		this.validDays = validDays;
	}

	public byte getStatus() {
		return status;
	}

	public void setStatus(byte status) {
		this.status = status;
	}

	public Timestamp getPublishedDate() {
		return publishedDate;
	}

	public void setPublishedDate(Timestamp publishedDate) {
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

	public Integer getAreaId() {
		return areaId;
	}

	public void setAreaId(Integer areaId) {
		this.areaId = areaId;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public Double getLongitude() {
		return longitude;
	}

	public void setLongitude(Double longitude) {
		this.longitude = longitude;
	}

	public Double getLatitude() {
		return latitude;
	}

	public void setLatitude(Double latitude) {
		this.latitude = latitude;
	}

	@Override
	public String toString() {
		return "TicketVO [ticketId=" + ticketId + ", ticketType=" + ticketType + ", ticketName=" + ticketName
				+ ", description=" + description + ", price=" + price + ", stock=" + stock + ", validDays=" + validDays
				+ ", status=" + status + ", publishedDate=" + publishedDate + ", totalStarRatings=" + totalStarRatings
				+ ", totalStars=" + totalStars + ", areaId=" + areaId + ", address=" + address + ", longitude="
				+ longitude + ", latitude=" + latitude + "]";
	}
	
	
/*****	JDBC用的版本 ******/
//	public TicketVO() {
//		super();
//		// TODO Auto-generated constructor stub
//	}
//
//	public TicketVO(Integer ticketId, Integer ticketTypeId, String ticketName, String description, Integer price,
//			Integer stock, Integer validDays, byte status, Timestamp publishedDate, Integer totalStarRatings,
//			Integer totalStars, Integer areaId, String address, Double longitude, Double latitude) {
//		super();
//		this.ticketId = ticketId;
//		this.ticketTypeId = ticketTypeId;
//		this.ticketName = ticketName;
//		this.description = description;
//		this.price = price;
//		this.stock = stock;
//		this.validDays = validDays;
//		this.status = status;
//		this.publishedDate = publishedDate;
//		this.totalStarRatings = totalStarRatings;
//		this.totalStars = totalStars;
//		this.areaId = areaId;
//		this.address = address;
//		this.longitude = longitude;
//		this.latitude = latitude;
//	}
//
//	public Integer getTicketId() {
//		return ticketId;
//	}
//
//	public void setTicketId(Integer ticketId) {
//		this.ticketId = ticketId;
//	}
//
//	public Integer getTicketTypeId() {
//		return ticketTypeId;
//	}
//
//	public void setTicketTypeId(Integer ticketTypeId) {
//		this.ticketTypeId = ticketTypeId;
//	}
//
//	public String getTicketName() {
//		return ticketName;
//	}
//
//	public void setTicketName(String ticketName) {
//		this.ticketName = ticketName;
//	}
//
//	public String getDescription() {
//		return description;
//	}
//
//	public void setDescription(String description) {
//		this.description = description;
//	}
//
//	public Integer getPrice() {
//		return price;
//	}
//
//	public void setPrice(Integer price) {
//		this.price = price;
//	}
//
//	public Integer getStock() {
//		return stock;
//	}
//
//	public void setStock(Integer stock) {
//		this.stock = stock;
//	}
//
//	public Integer getValidDays() {
//		return validDays;
//	}
//
//	public void setValidDays(Integer validDays) {
//		this.validDays = validDays;
//	}
//
//	public byte getStatus() {
//		return status;
//	}
//
//	public void setStatus(byte status) {
//		this.status = status;
//	}
//
//	public Timestamp getPublishedDate() {
//		return publishedDate;
//	}
//
//	public void setPublishedDate(Timestamp publishedDate) {
//		this.publishedDate = publishedDate;
//	}
//
//	public Integer getTotalStarRatings() {
//		return totalStarRatings;
//	}
//
//	public void setTotalStarRatings(Integer totalStarRatings) {
//		this.totalStarRatings = totalStarRatings;
//	}
//
//	public Integer getTotalStars() {
//		return totalStars;
//	}
//
//	public void setTotalStars(Integer totalStars) {
//		this.totalStars = totalStars;
//	}
//
//	public Integer getAreaId() {
//		return areaId;
//	}
//
//	public void setAreaId(Integer areaId) {
//		this.areaId = areaId;
//	}
//
//	public String getAddress() {
//		return address;
//	}
//
//	public void setAddress(String address) {
//		this.address = address;
//	}
//
//	public Double getLongitude() {
//		return longitude;
//	}
//
//	public void setLongitude(Double longitude) {
//		this.longitude = longitude;
//	}
//
//	public Double getLatitude() {
//		return latitude;
//	}
//
//	public void setLatitude(Double latitude) {
//		this.latitude = latitude;
//	}
//    

}
