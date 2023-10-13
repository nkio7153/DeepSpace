package com.depthspace.ticketshoppingcart.model;

import javax.persistence.*;
import java.io.Serializable;
import java.sql.Date;
import java.sql.Timestamp;
import java.util.Objects;

@Entity
@Table(name="TICKET_SHOPPING_CART")
@IdClass(TicketShoppingCartVO.CompositeDetail.class)
public class TicketShoppingCartVO implements Serializable {
    @Id
    @Column(name = "MEM_ID")
    private Integer memId;

    @Id
    @Column(name = "TICKET_ID")
    private Integer ticketId;

    @Column(name = "QUANTITY", nullable = false)
    private Integer quantity;

    @Column(name = "ADDED_DATE", nullable = false)
    private Date addedDate;

    public TicketShoppingCartVO() {
    }

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


        public static class CompositeDetail implements Serializable {
        private static final long serialVersionUID = 1L;

        private Integer ticketId;
        private Integer memId; // 添加 memId 屬性

        public CompositeDetail() {
            super();
        }

        public CompositeDetail(Integer ticketId, Integer memId) {
            super();
            this.ticketId = ticketId; // 修正了此處
            this.memId = memId;       // 修正了此處
        }

        public Integer getTicketId() {
            return ticketId;
        }

        public void setTicketId(Integer ticketId) {
            this.ticketId = ticketId;
        }

        public Integer getMemId() { // 添加 memId 的 getter
            return memId;
        }

        public void setMemId(Integer memId) { // 添加 memId 的 setter
            this.memId = memId;
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;
            CompositeDetail that = (CompositeDetail) o;
            return Objects.equals(ticketId, that.ticketId) && Objects.equals(memId, that.memId);
        }

        @Override
        public int hashCode() {
            return Objects.hash(ticketId, memId);
        }

    }
}
