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
@IdClass(TicketCollectionVO.CompositeDetail.class) // 內嵌類別 CompositeDetail 為複合主鍵
public class TicketCollectionVO implements Serializable {
	
    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Id
    @Column(name="MEM_ID")
    private Integer memId; 
    
    @Id
    @Column(name="TICKET_ID")
    private Integer ticketId; 
    
    @ManyToOne
    @JoinColumn(name="TICKET_ID", referencedColumnName="TICKET_ID", insertable=false, updatable=false)
    private TicketVO ticketVO;

	public TicketCollectionVO() {
		super();
		// TODO Auto-generated constructor stub
	}

	public TicketCollectionVO(Integer memId, Integer ticketId, TicketVO ticketVO) {
		super();
		this.memId = memId;
		this.ticketId = ticketId;
		this.ticketVO = ticketVO;
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

	public TicketVO getTicketVO() {
		return ticketVO;
	}

	public void setTicketVO(TicketVO ticketVO) {
		this.ticketVO = ticketVO;
	}

	@Override
	public int hashCode() {
		return Objects.hash(memId, ticketId, ticketVO);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		TicketCollectionVO other = (TicketCollectionVO) obj;
		return Objects.equals(memId, other.memId) && Objects.equals(ticketId, other.ticketId)
				&& Objects.equals(ticketVO, other.ticketVO);
	}

	@Override
	public String toString() {
		return "TicketCollectionVO [memId=" + memId + ", ticketId=" + ticketId + ", ticketVO=" + ticketVO + "]";
	}
    public static class CompositeDetail implements Serializable {
        // 複合鍵的屬性
        private Integer memId;
        private Integer ticketId; 
        
        public CompositeDetail() {}

        public CompositeDetail(Integer memId, Integer ticketId) {
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
 