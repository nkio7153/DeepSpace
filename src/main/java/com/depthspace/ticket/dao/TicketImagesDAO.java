package com.depthspace.ticket.dao;

import java.util.List;

import com.depthspace.ticket.model.TicketImagesVO;

public interface TicketImagesDAO {
    void insert(TicketImagesVO ticketImagesVO);
    
    void update(TicketImagesVO ticketImagesVO);
    
    void delete(Integer serialId);
    
    TicketImagesVO findByPrimaryKey(Integer serialId);
    
    List<TicketImagesVO> getAll();
}
