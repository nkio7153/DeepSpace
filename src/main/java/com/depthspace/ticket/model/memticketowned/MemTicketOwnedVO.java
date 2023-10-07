package com.depthspace.ticket.model.memticketowned;
import java.io.Serializable;
import java.sql.Date;


public class MemTicketOwnedVO implements Serializable{
    private Integer ticketOwnedId;  // 使用 Integer 包裝類
    private Integer memId;          // 使用 Integer 包裝類
    private Integer ticketId;       // 使用 Integer 包裝類
    private Date releaseDate;       // 使用 Date 類型
    private Date expiryDate;        // 使用 Date 類型
    private Integer statusOfUse;    // 使用 Integer 包裝類

    public MemTicketOwnedVO() {
    }

    // 帶參數的構造函數
    public MemTicketOwnedVO(Integer ticketOwnedId, Integer memId, Integer ticketId, Date releaseDate, Date expiryDate, Integer statusOfUse) {
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

    public Integer getStatusOfUse() {
        return statusOfUse;
    }

    public void setStatusOfUse(Integer statusOfUse) {
        this.statusOfUse = statusOfUse;
    }
}
