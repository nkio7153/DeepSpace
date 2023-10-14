package com.depthspace.memticketowned.model;

import com.depthspace.ticketorders.model.ticketorders.TicketOrdersVO;

import java.util.List;

public interface HibernateMtoDao_Interface {
    int insert(MemTicketOwnedVO entity);

    int update(MemTicketOwnedVO entity);

    int delete(Integer id);

    MemTicketOwnedVO getById(Integer id);

    List<MemTicketOwnedVO> getAll();

    List<MemTicketOwnedVO> getAll(int currentPage);

    long getTotal();
}
