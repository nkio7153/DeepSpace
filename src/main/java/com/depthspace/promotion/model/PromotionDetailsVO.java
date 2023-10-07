package com.depthspace.promotion.model;

import java.io.Serializable;
import java.sql.Date;


public class PromotionDetailsVO implements Serializable {
    private Integer ticketOwnedId;
    private Integer memId;
    private Integer ticketId;
    private Date releaseDate;
    private Date expiryDate;
    private Byte statusOfUse;

    public PromotionDetailsVO() {
    }

    public PromotionDetailsVO(Integer ticketOwnedId, Integer memId, Integer ticketId, Date releaseDate, Date expiryDate, Byte statusOfUse) {
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

    public Byte getStatusOfUse() {
        return statusOfUse;
    }

    public void setStatusOfUse(Byte statusOfUse) {
        this.statusOfUse = statusOfUse;
    }
}
