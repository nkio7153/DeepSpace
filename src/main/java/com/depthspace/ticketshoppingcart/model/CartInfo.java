package com.depthspace.ticketshoppingcart.model;

import org.hibernate.annotations.Immutable;

import javax.persistence.*;
import java.math.BigDecimal;
import java.sql.Timestamp;
import java.util.Objects;

@Entity
@Table(name="CartInfo")
public class CartInfo {
    @Id
    @Column(name="TICKET_ID")
    private Integer ticketId;
    @Column(name="SERIAL_ID")
    private Integer serialId;
    @Column(name="TICKET_NAME")
    private String ticketName;
    //修改

    @Column(name="PROMOTION_ID")
    private Integer promotionId;

    @Column(name="START_DATE")
    private Timestamp startDate;

    @Column(name="END_DATE")
    private Timestamp endDate;
    //修改
    @Column(name="DESCRIPTION")
    private String description;
    @Column(name="PRICE")
    private Integer price;
    @Column(name="PROMO_DISCOUNT")
    private BigDecimal discount;
    @Transient
    private Integer quantity;
    @Column(name="STOCK")
    private Integer stock;

    public CartInfo() {
    }

    public CartInfo(Integer ticketId, Integer serialId, String ticketName,Integer promotionId, Timestamp startDate, Timestamp endDate, String description, Integer price, BigDecimal discount, Integer quantity, Integer stock) {
        this.ticketId = ticketId;
        this.serialId = serialId;
        this.ticketName = ticketName;
        this.promotionId = promotionId;
        this.startDate = startDate;
        this.endDate = endDate;
        this.description = description;
        this.price = price;
        this.discount = discount;
        this.quantity = quantity;
        this.stock = stock;
    }

    public Integer getTicketId() {
        return ticketId;
    }

    public void setTicketId(Integer ticketId) {
        this.ticketId = ticketId;
    }

    public Integer getSerialId() {
        return serialId;
    }

    public void setSerialId(Integer serialId) {
        this.serialId = serialId;
    }

    public String getTicketName() {
        return ticketName;
    }

    public void setTicketName(String ticketName) {
        this.ticketName = ticketName;
    }

    public Integer getPromotionId() {
        return promotionId;
    }

    public void setPromotionId(Integer promotionId) {
        this.promotionId = promotionId;
    }

    public Timestamp getStartDate() {
        return startDate;
    }

    public void setStartDate(Timestamp startDate) {
        this.startDate = startDate;
    }

    public Timestamp getEndDate() {
        return endDate;
    }

    public void setEndDate(Timestamp endDate) {
        this.endDate = endDate;
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

    public BigDecimal getDiscount() {
        return discount;
    }

    public void setDiscount(BigDecimal discount) {
        this.discount = discount;
    }

    public Integer getQuantity() {
        return quantity;
    }

    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }

    public Integer getStock() {
        return stock;
    }

    public void setStock(Integer stock) {
        this.stock = stock;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        CartInfo cartInfo = (CartInfo) o;
        return Objects.equals(ticketId, cartInfo.ticketId) && Objects.equals(serialId, cartInfo.serialId) && Objects.equals(ticketName, cartInfo.ticketName) && Objects.equals(promotionId, cartInfo.promotionId) && Objects.equals(startDate, cartInfo.startDate) && Objects.equals(endDate, cartInfo.endDate) && Objects.equals(description, cartInfo.description) && Objects.equals(price, cartInfo.price) && Objects.equals(discount, cartInfo.discount) && Objects.equals(quantity, cartInfo.quantity) && Objects.equals(stock, cartInfo.stock);
    }

    @Override
    public int hashCode() {
        return Objects.hash(ticketId, serialId, ticketName, promotionId, startDate, endDate, description, price, discount, quantity, stock);
    }

    @Override
    public String toString() {
        return "CartInfo{" +
                "ticketId=" + ticketId +
                ", serialId=" + serialId +
                ", ticketName='" + ticketName + '\'' +
                ", promotionId=" + promotionId +
                ", startDate=" + startDate +
                ", endDate=" + endDate +
                ", description='" + description + '\'' +
                ", price=" + price +
                ", discount=" + discount +
                ", quantity=" + quantity +
                ", stock=" + stock +
                '}';
    }
}
