package com.depthspace.ticket.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="TICKET_TYPES")
public class TicketTypesVO implements Serializable {
	
	@Id
	@Column(name="TICKET_TYPE_ID",updatable = false)
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer ticketTypeId;
	
	@Column(name="TYPE_NAME")
	private String typeName;
	
	@Column(name="DESCRIPTION")
	private String description;
	
	public TicketTypesVO() {
		super();
	}

	public TicketTypesVO(Integer ticketTypeId, String typeName, String description) {
		super();
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