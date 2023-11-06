package com.depthspace.memticketowned.model;

import javax.annotation.processing.Generated;
import javax.persistence.*;
import java.io.Serializable;
import java.sql.Date;
import java.sql.Timestamp;  // 引入 Timestamp 類型
@Entity
@Table(name="MEM_TICKET_OWNED")
public class MemTicketOwnedVO implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="TICKET_OWNED_ID")
    private Integer ticketOwnedId;
    @Column(name="MEM_ID",nullable=false)
    private Integer memId;
    @Column(name="TICKET_ID",nullable=false)
    private Integer ticketId;
    @Column(name="ORDER_ID",nullable=false)
    private Integer orderId;
    @Column(name="RELEASE_DATE")
    private Date releaseDate;
    @Column(name="EXPIRY_DATE")
    private Date expiryDate;
    @Column(name="STATUS_OF_USE", columnDefinition = "TINYINT")
    private Integer statusOfUse;

    public MemTicketOwnedVO() {
    }

    public MemTicketOwnedVO(Integer ticketOwnedId, Integer memId, Integer ticketId, Integer orderId, Date releaseDate, Date expiryDate, Integer statusOfUse) {
        this.ticketOwnedId = ticketOwnedId;
        this.memId = memId;
        this.ticketId = ticketId;
        this.orderId=orderId;
        this.releaseDate = releaseDate;
        this.expiryDate = expiryDate;
        this.statusOfUse = statusOfUse;
    }

    public Integer getOrderId() {
        return orderId;
    }

    public void setOrderId(Integer orderId) {
        this.orderId = orderId;
    }

    public Integer getTicketOwnedId() {
        return ticketOwnedId;
    }

    public void setTicketOwnedId(Integer ticketOwnedId) {
        this.ticketOwnedId = ticketOwnedId;
    }

    public Integer getMemId() {
        return memId;
    }

    public void setMemId(Integer memId) {
        this.memId = memId;
    }

    public Integer getTicketId() {
        return ticketId;
    }

    public void setTicketId(Integer ticketId) {
        this.ticketId = ticketId;
    }

    public Date getReleaseDate() {
        return releaseDate;
    }

    public void setReleaseDate(Date releaseDate) {
        this.releaseDate = releaseDate;
    }

    public Date getExpiryDate() {  // 使用 Timestamp
        return expiryDate;
    }

    public void setExpiryDate(Date expiryDate) {  // 使用 Timestamp
        this.expiryDate = expiryDate;
    }

    public Integer getStatusOfUse() {
        return statusOfUse;
    }

    public void setStatusOfUse(Integer statusOfUse) {
        this.statusOfUse = statusOfUse;
    }
}
