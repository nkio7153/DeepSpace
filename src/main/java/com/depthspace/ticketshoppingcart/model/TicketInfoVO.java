package com.depthspace.ticketshoppingcart.model;

public class TicketInfoVO {
    private Integer memId;
    private Integer ticketId;
    private byte[] image;
    private String ticketName;
    private String description;
    private Integer price;
    private Integer quantity;
    private Integer subtotal;

    public String getBase64Image() {
        return base64Image;
    }

    public void setBase64Image(String base64Image) {
        this.base64Image = base64Image;
    }

    private String base64Image;

    public TicketInfoVO() {
    }

    public TicketInfoVO(Integer memId, Integer ticketId, byte[] image, String ticketName, String description, Integer price, Integer quantity, Integer subtotal) {
        this.memId = memId;
        this.ticketId=ticketId;
        this.image = image;
        this.ticketName = ticketName;
        this.description = description;
        this.price = price;
        this.quantity = quantity;
        this.subtotal = subtotal;
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

    public byte[] getImage() {
        return image;
    }

    public void setImage(byte[] image) {
        this.image = image;
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

    public Integer getSubtotal() {
        return subtotal;
    }

    public void setSubtotal(Integer subtotal) {
        this.subtotal = subtotal;
    }
}
