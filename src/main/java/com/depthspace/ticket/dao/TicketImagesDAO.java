package com.depthspace.ticket.dao;

import java.util.List;

import com.depthspace.ticket.model.TicketImagesVO;

public interface TicketImagesDAO {
	
	TicketImagesVO saveImage(byte[] imageBytes);
	
    void insert(TicketImagesVO ticketImagesVO);
    
    void update(TicketImagesVO ticketImagesVO);
    
    void delete(Integer serialId);
    
    TicketImagesVO findByPrimaryKey(Integer serialId);
    
	void TicketIsMainImage (byte isMainImage) ;
    
    List<TicketImagesVO> getAll();
}
