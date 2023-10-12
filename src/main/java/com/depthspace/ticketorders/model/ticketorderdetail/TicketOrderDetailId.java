package com.depthspace.ticketorders.model.ticketorderdetail;

import java.io.Serializable;
import java.util.Objects;

public class TicketOrderDetailId implements Serializable {
    private Integer orderId;
    private Integer ticketId;

    // ... 無參數的構造函數、equals、hashCode、getters、setters ...


    public TicketOrderDetailId() {
    }

    public Integer getOrderId() {
        return orderId;
    }

    public void setOrderId(Integer orderId) {
        this.orderId = orderId;
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
        TicketOrderDetailId that = (TicketOrderDetailId) o;
        return Objects.equals(orderId, that.orderId) && Objects.equals(ticketId, that.ticketId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(orderId, ticketId);
    }
}
