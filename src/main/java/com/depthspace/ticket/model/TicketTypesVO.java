package com.depthspace.ticket.model;

import java.io.Serializable;

public class TicketTypesVO implements Serializable {
    private Integer ticketTypeId; // 使用 Integer 作為包裝類型
    private String typeName;
    private String description;

    public TicketTypesVO() {
    }

    public TicketTypesVO(Integer ticketTypeId, String typeName, String description) {
        this.ticketTypeId = ticketTypeId;
        this.typeName = typeName;
        this.description = description;
    }

    public Integer getTicketTypeId() {
        return ticketTypeId;
    }

    public void setTicketTypeId(Integer ticketTypeId) {
        this.ticketTypeId = ticketTypeId;
    }

    public String getTypeName() {
        return typeName;
    }

    public void setTypeName(String typeName) {
        this.typeName = typeName;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
