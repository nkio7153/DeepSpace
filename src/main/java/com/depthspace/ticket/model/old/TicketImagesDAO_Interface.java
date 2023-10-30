package com.depthspace.ticket.model.old;

import java.util.List;

import com.depthspace.ticket.model.TicketImagesVO;

public interface TicketImagesDAO_Interface {
    void insert(TicketImagesVO ticketImagesVO);
    void update(TicketImagesVO ticketImagesVO);
    void delete(Integer serialId);
    TicketImagesVO findByPrimaryKey(Integer serialId);
    List<TicketImagesVO> getAll();
}
