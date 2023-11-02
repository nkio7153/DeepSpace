package com.depthspace.ticket.model.old;

import java.sql.Timestamp;

public class TicketInfo {
    private Integer ticketId;
    private Integer ticketTypeId;
    private String typeName; 
    private String ticketName;
    private String description;
    private Integer price;
    private Integer stock;
    private Integer validDays;
    private Byte status;
    private java.sql.Timestamp publishedDate;
    private Integer totalStarRatings;
    private Integer totalStars;
    private Integer areaId;
    private Integer cityId;
	private String cityName;    
    private String address;
    private Double longitude;
    private Double latitude;
    private byte[] image;
    private Integer salesVolume;   
//  private String base64Image;

    
	public TicketInfo() {

	
		super();
		// TODO Auto-generated constructor stub
	}


	public TicketInfo(Integer ticketId, Integer ticketTypeId, String typeName, String ticketName, String description,
			Integer price, Integer stock, Integer validDays, Byte status, Timestamp publishedDate,
			Integer totalStarRatings, Integer totalStars, Integer areaId, Integer cityId, String cityName,
			String address, Double longitude, Double latitude, byte[] image, Integer salesVolume) {
		super();
		this.ticketId = ticketId;
		this.ticketTypeId = ticketTypeId;
		this.typeName = typeName;
		this.ticketName = ticketName;
		this.description = description;
		this.price = price;
		this.stock = stock;
		this.validDays = validDays;
		this.status = status;
		this.publishedDate = publishedDate;
		this.totalStarRatings = totalStarRatings;
		this.totalStars = totalStars;
		this.areaId = areaId;
		this.cityId = cityId;
		this.cityName = cityName;
		this.address = address;
		this.longitude = longitude;
		this.latitude = latitude;
		this.image = image;
		this.salesVolume = salesVolume;
//		this.base64Image = base64Image;
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
	public String getTypeName() {
		return typeName;
	}
	public void setTypeName(String typeName) {
		this.typeName = typeName;
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
	public Byte getStatus() {
		return status;
	}
	public void setStatus(Byte status) {
		this.status = status;
	}
	public java.sql.Timestamp getPublishedDate() {
		return publishedDate;
	}
	public void setPublishedDate(java.sql.Timestamp publishedDate) {
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
    public byte[] getImage() {
		return image;
	}
	public void setImage(byte[] image) {
        this.image = image;
    }
	public Integer getSalesVolume() {
		return salesVolume;
	}
	public void setSalesVolume(Integer salesVolume) {
		this.salesVolume = salesVolume;
	}
	public String getCityName() {
		return cityName;
	}
	public void setCityName(String cityName) {
		this.cityName = cityName;
	}
	public Integer getCityId() {
		return cityId;
	}
	public void setCityId(Integer cityId) {
		this.cityId = cityId;
	}

	 
//  public String getBase64Image() {
//      return base64Image;
//  }
//  public void setBase64Image(String base64Image) {
//      this.base64Image = base64Image;
//  }

}