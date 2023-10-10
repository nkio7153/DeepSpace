package com.depthspace.ticketshoppingcart.model;

import java.io.Serializable;
import java.sql.Timestamp;

public class TicketShoppingCartVO implements Serializable {
    private Integer memId;
    private Integer ticketId;
    private String ticketName;
    private Integer quantity;
    private Timestamp addedDate;

    public TicketShoppingCartVO() {
    }

    public String getTicketName() {
        return ticketName;
    }

    public void setTicketName(String ticketName) {
        this.ticketName = ticketName;
    }

    public TicketShoppingCartVO(Integer memId, Integer ticketId, String ticketName, Integer quantity, Timestamp addedDate) {  // 3. 更新構造函數
        this.memId = memId;
        this.ticketId = ticketId;
        this.ticketName=ticketName;
        this.quantity = quantity;
        this.addedDate = addedDate;
    }
    public TicketShoppingCartVO(Integer memId, Integer ticketId, Integer quantity, Timestamp addedDate) {  // 3. 更新構造函數
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

    public Timestamp getAddedDate() {
        return addedDate;
    }

    public void setAddedDate(Timestamp addedDate) {  // 3. 更新 setter
        this.addedDate = addedDate;
    }
}
