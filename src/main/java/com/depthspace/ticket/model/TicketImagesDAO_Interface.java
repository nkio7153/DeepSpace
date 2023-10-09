package com.depthspace.ticket.model;

import java.util.List;

public interface TicketImagesDAO_Interface {
    void insert(TicketImagesVO ticketImagesVO);
    void update(TicketImagesVO ticketImagesVO);
    void delete(Integer serialId);
    TicketImagesVO findByPrimaryKey(Integer serialId);
    List<TicketImagesVO> getAll();
}
