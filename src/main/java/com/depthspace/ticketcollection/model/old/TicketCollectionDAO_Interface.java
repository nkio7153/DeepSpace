package com.depthspace.ticketcollection.model.old;

import java.util.*;

import com.depthspace.ticket.model.TicketVO;
import com.depthspace.ticketcollection.model.TicketCollectionVO;

public interface TicketCollectionDAO_Interface {
	public void insert(TicketCollectionVO ticketCollectionVO);
//	public void update(TicketCollectionVO ticketCollectionVO);  都是主鍵故不會有更新
	public void delete(TicketCollectionVO ticketCollectionVO); 
    public TicketCollectionVO findByPrimaryKey(Integer memId, Integer ticketId);
    public List<TicketCollectionVO> getAll(); 
    public List<TicketVO> getTicketsByMemID(Integer memId); //會員查詢
    
}