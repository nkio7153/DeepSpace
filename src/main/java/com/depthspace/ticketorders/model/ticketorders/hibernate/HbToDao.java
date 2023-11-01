package com.depthspace.ticketorders.model.ticketorders.hibernate;

import com.depthspace.ticketorders.model.ticketorders.TicketOrdersVO;

import java.util.List;

public interface HbToDao {
    int insert(TicketOrdersVO entity);

    int update(TicketOrdersVO entity);

    int delete(Integer id);

    TicketOrdersVO getById(Integer id);

    List<TicketOrdersVO> getByMemId(Integer MemId);

    List<TicketOrdersVO> getAll();

    List<TicketOrdersVO> getAll(int currentPage);

    long getTotal();
    //取得最新一筆訂單

    TicketOrdersVO getLatestOrderByMemId(Integer memId);

}
