package com.depthspace.ticket.service;

import java.util.List;
import java.util.Map;

import com.depthspace.attractions.model.CityVO;
import com.depthspace.ticket.model.TicketTypesVO;
import com.depthspace.ticket.model.TicketVO;
import com.depthspace.ticketorders.model.ticketorderdetail.TicketOrderDetailVO;
import com.depthspace.ticketorders.model.ticketorders.TicketOrdersVO;


public interface TicketTypesService {
	
	Integer insert(TicketTypesVO ticketTypesVO);
    
    void update(TicketTypesVO ticketTypesVO);

	List<TicketTypesVO> getAll();

	TicketTypesVO getOneById(Integer ticketTyepId);
}

