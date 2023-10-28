package com.depthspace.ticket.model;

import java.io.Serializable;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
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
	
	//One一方使用mappedBy代表被映射，取many自取的名稱，
	@OneToMany(mappedBy="ticketType", cascade = CascadeType.ALL) //cascade是否級聯受影響
	private Set<TicketVO> tickets;  //新增屬性

	
	public TicketTypesVO() {
		super();
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

	public Set<TicketVO> getTickets() {
		return tickets;
	}

	public void setTickets(Set<TicketVO> tickets) {
		this.tickets = tickets;
	}

//	@Override
//	public String toString() {
//		return "TicketTypesVO [ticketTypeId=" + ticketTypeId + ", typeName=" + typeName + ", description=" + description
//				+ ", tickets=" + tickets + "]";
//	}


	
	
			
}