package com.depthspace.ticketshoppingcart.model;

import java.io.Serializable;
import java.util.Objects;

public class TicketShoppingCartId implements Serializable {
    Integer memId;
    Integer ticketId;

    public TicketShoppingCartId() {
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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        TicketShoppingCartId that = (TicketShoppingCartId) o;
        return Objects.equals(memId, that.memId) && Objects.equals(ticketId, that.ticketId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(memId, ticketId);
    }
}
