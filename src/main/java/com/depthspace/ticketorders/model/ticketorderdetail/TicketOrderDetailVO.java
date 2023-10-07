package com.depthspace.ticketorders.model.ticketorderdetail;

import java.io.Serializable;

public class TicketOrderDetailVO implements Serializable {
    private Integer orderId;
    private Integer ticketId;
    private Integer quantity;
    private Integer unitPrice;
    private Integer discountPrice;
    private Integer subtotal;
    private String ticketReviews;
    private Byte stars;

    public TicketOrderDetailVO() {
    }

    public TicketOrderDetailVO(Integer orderId, Integer ticketId, Integer quantity, Integer unitPrice, Integer discountPrice, Integer subtotal, String ticketReviews, Byte stars) {
        this.orderId = orderId;
        this.ticketId = ticketId;
        this.quantity = quantity;
        this.unitPrice = unitPrice;
        this.discountPrice = discountPrice;
        this.subtotal = subtotal;
        this.ticketReviews = ticketReviews;
        this.stars = stars;
    }

    public Integer getOrderId() {
        return orderId;
    }

    public void setOrderId(Integer orderId) {
        this.orderId = orderId;
    }

    public Integer getTicketId() {
        return ticketId;
    }

    public void setTicketId(Integer ticketId) {
        this.ticketId = ticketId;
    }

    public Integer getQuantity() {
        return quantity;
    }

    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }

    public Integer getUnitPrice() {
        return unitPrice;
    }

    public void setUnitPrice(Integer unitPrice) {
        this.unitPrice = unitPrice;
    }

    public Integer getDiscountPrice() {
        return discountPrice;
    }

    public void setDiscountPrice(Integer discountPrice) {
        this.discountPrice = discountPrice;
    }

    public Integer getSubtotal() {
        return subtotal;
    }

    public void setSubtotal(Integer subtotal) {
        this.subtotal = subtotal;
    }

    public String getTicketReviews() {
        return ticketReviews;
    }

    public void setTicketReviews(String ticketReviews) {
        this.ticketReviews = ticketReviews;
    }

    public Byte getStars() {
        return stars;
    }

    public void setStars(Byte stars) {
        this.stars = stars;
    }
}
