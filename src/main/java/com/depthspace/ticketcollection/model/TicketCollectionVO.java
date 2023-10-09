package com.depthspace.ticketcollection.model;

import java.io.Serializable;

public class TicketCollectionVO implements Serializable {
	
    private Integer memId; 
    private Integer ticketId; 

    public TicketCollectionVO() {
    }

    public TicketCollectionVO(Integer memId, Integer ticketId) {
        this.memId = memId;
        this.ticketId = ticketId;
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
}

