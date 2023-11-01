package com.depthspace.ticketshoppingcart;

import java.util.Arrays;
import java.util.Objects;

public class CartVO {
    private Integer ticketId;
    private Integer serialId;
    private String ticketName;
    private String description;
    private Integer price;
    private Integer quantity;
    public CartVO(){}

    public CartVO(Integer ticketId,Integer serialId, String ticketName, String description, Integer price, Integer quantity) {
        this.ticketId = ticketId;
        this.serialId = serialId;
        this.ticketName = ticketName;
        this.description = description;
        this.price = price;
        this.quantity = quantity;
    }

    public Integer getTicketId() {
        return ticketId;
    }

    public void setTicketId(Integer ticketId) {
        this.ticketId = ticketId;
    }

    public Integer getSerialId() {
        return serialId;
    }

    public void setSerialId(Integer serialId) {
        this.serialId = serialId;
    }

    public String getTicketName() {
        return ticketName;
    }

    public void setTicketName(String ticketName) {
        this.ticketName = ticketName;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Integer getPrice() {
        return price;
    }

    public void setPrice(Integer price) {
        this.price = price;
    }

    public Integer getQuantity() {
        return quantity;
    }

    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        CartVO cartVO = (CartVO) o;
        return Objects.equals(ticketId, cartVO.ticketId) && Objects.equals(serialId, cartVO.serialId) && Objects.equals(ticketName, cartVO.ticketName) && Objects.equals(description, cartVO.description) && Objects.equals(price, cartVO.price) && Objects.equals(quantity, cartVO.quantity);
    }

    @Override
    public int hashCode() {
        return Objects.hash(ticketId, serialId, ticketName, description, price, quantity);
    }
}
