package com.depthspace.ticketorders.model.ticketorderdetail.hibernate;



import com.depthspace.ticketorders.model.ticketorderdetail.TicketOrderDetailVO;

import java.util.List;

public interface HbTodDao {
    void insertBatch(List<TicketOrderDetailVO> todList);

    int insert(TicketOrderDetailVO entity);

    int update(TicketOrderDetailVO entity);

    int delete(Integer orderId);

    TicketOrderDetailVO getById(TicketOrderDetailVO.CompositeDetail id);
    List<TicketOrderDetailVO> getByOrderId(Integer orderId);

    List<TicketOrderDetailVO> getAll();

    List<TicketOrderDetailVO> getAll(int currentPage);

    long getTotal(Integer orderId);
}
