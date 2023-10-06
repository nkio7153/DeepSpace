package com.depthspace.ticket.model.bean;

import java.util.Date;

public class TicketVO {
    private Integer ticketId; // 使用 Integer 作為包裝類型
    private Integer ticketTypeId; // 使用 Integer 作為包裝類型
    private String ticketName;
    private String description;
    private Integer price; // 使用 Integer 作為包裝類型
    private Integer stock; // 使用 Integer 作為包裝類型
    private Integer validDays; // 使用 Integer 作為包裝類型
    private Byte status; // 使用 Byte 作為包裝類型
    private Date publishedDate;
    private Integer totalStarRatings; // 使用 Integer 作為包裝類型
    private Integer totalStars; // 使用 Integer 作為包裝類型

    public TicketVO(Integer ticketId, Integer ticketTypeId, String ticketName, String description, Integer price, Integer stock, Integer validDays, Byte status, Date publishedDate, Integer totalStarRatings, Integer totalStars) {
        this.ticketId = ticketId;
        this.ticketTypeId = ticketTypeId;
        this.ticketName = ticketName;
        this.description = description;
        this.price = price;
        this.stock = stock;
        this.validDays = validDays;
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
