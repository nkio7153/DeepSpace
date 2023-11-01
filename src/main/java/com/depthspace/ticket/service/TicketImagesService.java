package com.depthspace.ticket.service;

import java.util.List;

import com.depthspace.ticket.model.TicketImagesVO;

public interface TicketImagesService {
	
	void save(TicketImagesVO ticketImage);

	TicketImagesVO getTicketImagesById(Integer ticketId);

	void saveAll(List<TicketImagesVO> ticketImages);

}
