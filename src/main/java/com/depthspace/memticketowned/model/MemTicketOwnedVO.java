package com.depthspace.memticketowned.model;

import java.io.Serializable;
import java.sql.Timestamp;  // 引入 Timestamp 類型

public class MemTicketOwnedVO implements Serializable {
    private Integer ticketOwnedId;
    private Integer memId;
    private Integer ticketId;
    private Timestamp releaseDate;
    private Timestamp expiryDate;
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
