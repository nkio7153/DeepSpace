package com.depthspace.ticket.dao;

import java.util.List;

import com.depthspace.ticket.model.TicketTypesVO;

public interface TicketTypesDAO {
	
	Integer insert(TicketTypesVO ticketTypesVO);
	
	void update(TicketTypesVO ticketTypesVO);
	
	List<TicketTypesVO> getAll();
	
	TicketTypesVO getOneById(Integer ticketTyepId);
	
}
