package com.depthspace.memticketowned.model;

import javax.persistence.*;
import java.io.Serializable;
import java.sql.Timestamp;  // 引入 Timestamp 類型
@Entity
@Table(name="MEM_TICKET_OWNED")
public class MemTicketOwnedVO implements Serializable {
    @Id
    @Column(name="TICKET_OWNED_ID")
    private Integer ticketOwnedId;
    @Column(name="MEM_ID",nullable=false)
    private Integer memId;
    @Column(name="TICKET_ID",nullable=false)
    private Integer ticketId;
    @Column(name="RELEASE_DATE")
    private Timestamp releaseDate;
    @Column(name="EXPIRY_DATE")
    private Timestamp expiryDate;
    @Column(name="STATUS_OF_USE", columnDefinition = "TINYINT")
    private Integer statusOfUse;

    public MemTicketOwnedVO() {
    }

    public MemTicketOwnedVO(Integer ticketOwnedId, Integer memId, Integer ticketId, Timestamp releaseDate, Timestamp expiryDate, Integer statusOfUse) {
        this.ticketOwnedId = ticketOwnedId;
        this.memId = memId;
        this.ticketId = ticketId;
        this.releaseDate = releaseDate;
        this.expiryDate = expiryDate;
        this.statusOfUse = statusOfUse;
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

    public Timestamp getReleaseDate() {
        return releaseDate;
    }

    public void setReleaseDate(Timestamp releaseDate) {
        this.releaseDate = releaseDate;
    }

    public Timestamp getExpiryDate() {  // 使用 Timestamp
        return expiryDate;
    }

    public void setExpiryDate(Timestamp expiryDate) {  // 使用 Timestamp
        this.expiryDate = expiryDate;
    }

    public Integer getStatusOfUse() {
        return statusOfUse;
    }

    public void setStatusOfUse(Integer statusOfUse) {
        this.statusOfUse = statusOfUse;
    }
}
