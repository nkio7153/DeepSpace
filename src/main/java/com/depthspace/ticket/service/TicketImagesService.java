package com.depthspace.ticket.service;

import com.depthspace.ticket.model.TicketImagesVO;

public interface TicketImagesService {
	
	void save(TicketImagesVO ticketImage);

	TicketImagesVO getTicketImagesById(Integer ticketId);

}
