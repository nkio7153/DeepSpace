package com.depthspace.memticketowned.model;

import org.hibernate.annotations.Immutable;

import javax.persistence.*;
import java.math.BigDecimal;
import java.sql.Date;

@Immutable
@Entity
@Table(name="MemTicketDetails")
public class MemTicketDetails {
    @Column(name="memberId")
    private Integer memId;
    @Id
    @Column(name="ticketOwnedId")
    private Integer ticketOwnedId;
    @Column(name="serialId")
    private Integer serialId;
    @Column(name="statusOfUse")
    private Byte statusOfUse;
    @Column(name="ticketId")
    private Integer ticketId;
    @Column(name="orderId")
    private Integer orderId;
    @Column(name="ticketName")
    private String ticketName;
    @Column(name="releaseDate")
    private Date releaseDate;
    @Column(name="expiryDate")
    private Date expiryDate;
    @Column(name="discountPrice")
    private Integer discountPrice;

    public MemTicketDetails(Integer memId, Integer ticketOwnedId, Integer serialId, Byte statusOfUse, Integer ticketId, Integer orderId, String ticketName, Date releaseDate,Date expiryDate, Integer discountPrice) {
        this.memId = memId;
        this.ticketOwnedId = ticketOwnedId;
        this.serialId = serialId;
        this.statusOfUse = statusOfUse;
        this.ticketId = ticketId;
        this.orderId = orderId;
        this.ticketName = ticketName;
        this.releaseDate = releaseDate;
        this.expiryDate= expiryDate;
        this.discountPrice = discountPrice;
    }

    public MemTicketDetails() {
    }

    public Integer getMemId() {
        return memId;
    }

    public void setMemId(Integer memId) {
        this.memId = memId;
    }

    public Integer getTicketOwnedId() {
        return ticketOwnedId;
    }

    public void setTicketOwnedId(Integer ticketOwnedId) {
        this.ticketOwnedId = ticketOwnedId;
    }

    public Integer getSerialId() {
        return serialId;
    }

    public void setSerialId(Integer serialId) {
        this.serialId = serialId;
    }

    public Byte getStatusOfUse() {
        return statusOfUse;
    }

    public void setStatusOfUse(Byte statusOfUse) {
        this.statusOfUse = statusOfUse;
    }

    public Integer getTicketId() {
        return ticketId;
    }

    public void setTicketId(Integer ticketId) {
        this.ticketId = ticketId;
    }

    public Integer getOrderId() {
        return orderId;
    }

    public void setOrderId(Integer orderId) {
        this.orderId = orderId;
    }

    public String getTicketName() {
        return ticketName;
    }

    public void setTicketName(String ticketName) {
        this.ticketName = ticketName;
    }

    public Date getReleaseDate() {
        return releaseDate;
    }

    public void setReleaseDate(Date releaseDate) {
        this.releaseDate = releaseDate;
    }

    public Date getExpiryDate() {
        return expiryDate;
    }

    public void setExpiryDate(Date expiryDate) {
        this.expiryDate = expiryDate;
    }

    public Integer getDiscountPrice() {
        return discountPrice;
    }

    public void setDiscountPrice(Integer discountPrice) {
        this.discountPrice = discountPrice;
    }
}
