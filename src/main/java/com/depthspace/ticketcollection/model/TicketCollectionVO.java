package com.depthspace.ticketcollection.model;

import java.io.Serializable;
import java.util.Objects;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.IdClass;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.depthspace.ticket.model.TicketVO;

@Entity
@Table(name="TICKET_COLLECTION")
@IdClass(TicketCollectionVO.CompositeDetail.class) //內嵌類別 CompositeDetail 為複合主鍵
public class TicketCollectionVO implements Serializable {
	
	@Id
	@Column(name="MEM_ID")
    private Integer memId; 
	
	@Id
//	@Column(name="TICKET_ID")
	@ManyToOne
    @JoinColumn(name="TICKET_ID", referencedColumnName="TICKET_ID", insertable=false, updatable=false)
    private TicketVO ticketVO;
	//    private Integer ticketId; 

    public TicketCollectionVO() {
    }

    public TicketCollectionVO(Integer memId, TicketVO ticketVO) {
        this.memId = memId;
        this.ticketVO = ticketVO;
    }

    public Integer getMemId() {
        return memId;
    }

    public void setMemId(Integer memId) {
        this.memId = memId;
    }

    public TicketVO getTicketVO() {
        return ticketVO;
    }

    public void setTicketVO(TicketVO ticketVO) {
        this.ticketVO = ticketVO;
    }
    
    public static class CompositeDetail implements Serializable {

		private Integer memId;
		private TicketVO ticketVO;
		
		public CompositeDetail() {
			super();
			
		}

		public CompositeDetail(Integer memId,TicketVO ticketVO) {
			super();
			this.memId = memId;
			this.ticketVO = ticketVO;
		}

		public Integer getMemId() {
			return memId;
		}

		public void setMemId(Integer memId) {
			this.memId = memId;
		}

		public TicketVO getTicketVO() {
			return ticketVO;
		}

		public void setTticketVO(TicketVO ticketVO) {
			this.ticketVO = ticketVO;
		}

		@Override
		public int hashCode() {
			return Objects.hash(memId, ticketVO);
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
			return Objects.equals(memId, other.memId) && Objects.equals(ticketVO, other.ticketVO);
		}

		
	}
}

