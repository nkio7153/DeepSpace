package com.depthspace.ticketshoppingcart.model;

import com.depthspace.ticket.model.TicketVO;

import javax.persistence.*;
import java.io.Serializable;
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


    public TicketShoppingCartVO() {
    }

    public TicketShoppingCartVO(Integer memId, Integer ticketId, Integer quantity) {
        this.memId = memId;
        this.ticketId = ticketId;
        this.quantity = quantity;
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



        public static class CompositeDetail implements Serializable {
        private static final long serialVersionUID = 1L;

        private Integer ticketId;
        private Integer memId; // 添加 memId 屬性

        public CompositeDetail() {
            super();
        }

        public CompositeDetail(Integer ticketId, Integer memId) {
            super();
            this.ticketId = ticketId;
            this.memId = memId;
        }

        public Integer getTicketId() {
            return ticketId;
        }

        public void setTicketId(Integer ticketId) {
            this.ticketId = ticketId;
        }

        public Integer getMemId() {
            return memId;
        }

        public void setMemId(Integer memId) {
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
