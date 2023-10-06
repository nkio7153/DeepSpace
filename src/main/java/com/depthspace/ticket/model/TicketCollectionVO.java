package com.depthspace.ticket.model;

public class TicketCollectionVO {
    private Integer memId; // 使用 Integer 作為包裝類型
    private Integer ticketId; // 使用 Integer 作為包裝類型

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

