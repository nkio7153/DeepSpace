package com.depthspace.ticketorders.model.ticketorders;

import java.util.List;

public interface HibernateToDao_Interface {
    int insert(TicketOrdersVO entity);

    int update(TicketOrdersVO entity);

    int delete(Integer id);

    TicketOrdersVO getById(Integer id);

    List<TicketOrdersVO> getAll();

    List<TicketOrdersVO> getAll(int currentPage);

    long getTotal();
}
