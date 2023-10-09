package com.depthspace.ticket.model;

import java.util.*;

public interface TicketTypesDAO_Interface {
	
	public void insert(TicketTypesVO ticketTypesVO);
	public void update(TicketTypesVO ticketTypesVO);
	public void delete(Integer ticketTypeId); //測試用
    public TicketTypesVO findByPrimaryKey(Integer ticketTypeId);
    public List<TicketTypesVO> getAll();  
}
