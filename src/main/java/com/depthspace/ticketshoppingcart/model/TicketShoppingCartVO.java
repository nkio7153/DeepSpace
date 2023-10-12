package com.depthspace.ticketshoppingcart.model;

import com.depthspace.ticketorders.model.ticketorderdetail.TicketOrderDetailId;

import javax.persistence.*;
import java.io.Serializable;
import java.sql.Timestamp;
@Entity
@Table(name="TICKET_SHOPPING_CART")
@IdClass(TicketShoppingCartId.class)
public class TicketShoppingCartVO implements Serializable {
    @Id
    @Column(name="MEM_ID")
    private Integer memId;
    @Id
    @Column(name="TICKET_ID")
    private Integer ticketId;
    @Column(name="QUANTITY", nullable = false)
    private Integer quantity;
    @Column(name="ADDED_DATE", nullable = false)
    private Timestamp addedDate;

    public TicketShoppingCartVO() {
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
