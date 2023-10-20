package com.depthspace.ticketorders.model.ticketorderdetail.hibernate;



import com.depthspace.ticketorders.model.ticketorderdetail.TicketOrderDetailVO;

import java.util.List;

public interface HbTodDao_Interface {
    int insert(TicketOrderDetailVO entity);

    int update(TicketOrderDetailVO entity);

    int delete(TicketOrderDetailVO.CompositeDetail id);

    TicketOrderDetailVO getById(TicketOrderDetailVO.CompositeDetail id);
    List<TicketOrderDetailVO> getByOrderId(Integer orderId);

    List<TicketOrderDetailVO> getAll();

    List<TicketOrderDetailVO> getAll(int currentPage);

    long getTotal();
}
