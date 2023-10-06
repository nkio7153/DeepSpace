package com.depthspace.ticket.model.bean;

import java.util.Date;

public class TicketShoppingCartVO {
    private Integer memId; // 使用 Integer 作為包裝類型
    private Integer ticketId; // 使用 Integer 作為包裝類型
    private Integer quantity; // 使用 Integer 作為包裝類型
    private Date addedDate;

    public TicketShoppingCartVO(Integer memId, Integer ticketId, Integer quantity, Date addedDate) {
        this.memId = memId;
        this.ticketId = ticketId;
        this.quantity = quantity;
        this.addedDate = addedDate;
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

    public Integer getQuantity() {
        return quantity;
    }

    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }

    public Date getAddedDate() {
        return addedDate;
    }

    public void setAddedDate(Date addedDate) {
        this.addedDate = addedDate;
    }
}
