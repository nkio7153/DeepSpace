package com.depthspace.ticketorders.model.ticketorders;

import com.depthspace.ticketorders.model.ticketorderdetail.TicketOrderDetailVO;

import java.util.List;

public interface TicketOrdersDAO_Interface {
    public void insert(TicketOrdersVO tod);
    public void update(TicketOrdersVO tod);
    public void delete(Integer orderId);
    public TicketOrdersVO findByPrimaryKey(Integer orderId);
    public List<TicketOrdersVO> getAll();
}
