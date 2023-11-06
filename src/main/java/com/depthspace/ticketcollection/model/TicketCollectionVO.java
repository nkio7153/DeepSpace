package com.depthspace.ticketcollection.model;

import java.io.Serializable;
import java.util.Objects;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.IdClass;
import javax.persistence.Table;

@Entity
@Table(name="TICKET_COLLECTION")
@IdClass(TicketCollectionVO.CompositeDetail.class) //內嵌類別 CompositeDetail 為複合主鍵
public class TicketCollectionVO implements Serializable {
	
	@Id
	@Column(name="MEM_ID")
    private Integer memId; 
	
	@Id
	@Column(name="TICKET_ID")
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
    
	static class CompositeDetail implements Serializable {
		private static final long serialVersionUID = 1L;

		private Integer memId;
		private Integer ticketId;
		
		public CompositeDetail() {
			super();
			
		}

		public CompositeDetail(Integer memId, Integer ticketId) {
			super();
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

		@Override
		public int hashCode() {
			return Objects.hash(memId, ticketId);
		}

		@Override
		public boolean equals(Object obj) {
			if (this == obj)
				return true;
			if (obj == null)
				return false;
			if (getClass() != obj.getClass())
				return false;
			CompositeDetail other = (CompositeDetail) obj;
			return Objects.equals(memId, other.memId) && Objects.equals(ticketId, other.ticketId);
		}

		
	}
}

