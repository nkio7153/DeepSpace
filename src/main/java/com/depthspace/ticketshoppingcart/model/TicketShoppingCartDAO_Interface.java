package com.depthspace.ticketshoppingcart.model;

import com.depthspace.ticketorders.model.ticketorders.TicketOrdersVO;

import java.util.List;

public interface TicketShoppingCartDAO_Interface {
    public void insert(TicketShoppingCartVO tsc);
    public void update(TicketShoppingCartVO tsc);
    public void delete(Integer memId, Integer ticketId);
    public TicketShoppingCartVO findByPrimaryKey(Integer memId, Integer ticketId);
    public List<TicketShoppingCartVO> findByMemId(Integer memId);
    public List<TicketShoppingCartVO> getAll();
}
