package com.depthspace.ticket.model;

import java.io.Serializable;

public class TicketImagesVO implements Serializable {
    private Integer serialId; 
    private Integer ticketId; 
    private byte[] image; 

    public TicketImagesVO() {
    }

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
