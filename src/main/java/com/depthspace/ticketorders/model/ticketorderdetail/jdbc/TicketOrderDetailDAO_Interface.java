package com.depthspace.ticketorders.model.ticketorderdetail.jdbc;

import com.depthspace.ticketorders.model.ticketorderdetail.TicketOrderDetailVO;

import java.util.List;

public interface TicketOrderDetailDAO_Interface {
    public void insert(TicketOrderDetailVO tod);
    public void update(TicketOrderDetailVO tod);
    public void delete(Integer orderId, Integer ticketId);
    public TicketOrderDetailVO findByPrimaryKey(Integer orderId, Integer ticketId);
    public List<TicketOrderDetailVO> getAll();
}
