package com.depthspace.ticket.model;

public class TicketImagesVO {
    private Integer serialId; // 使用 Integer 作為包裝類型
    private Integer ticketId; // 使用 Integer 作為包裝類型
    private byte[] image; // 使用 Byte[] 作為包裝類型

    public TicketImagesVO(Integer serialId, Integer ticketId, byte[] image) {
        this.serialId = serialId;
        this.ticketId = ticketId;
        this.image = image;
    }

    public Integer getSerialId() {
        return serialId;
    }

    public void setSerialId(Integer serialId) {
        this.serialId = serialId;
    }

    public Integer getTicketId() {
        return ticketId;
    }

    public void setTicketId(Integer ticketId) {
        this.ticketId = ticketId;
    }

    public byte[] getImage() {
        return image;
    }

    public void setImage(byte[] image) {
        this.image = image;
    }
}
