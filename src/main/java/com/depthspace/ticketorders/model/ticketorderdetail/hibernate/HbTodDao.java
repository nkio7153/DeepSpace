package com.depthspace.ticketorders.model.ticketorderdetail.hibernate;



import com.depthspace.ticketorders.model.ticketorderdetail.TicketOrderDetailVO;

import java.util.List;
import java.util.Objects;

public interface HbTodDao {
    void insertBatch(List<TicketOrderDetailVO> todList);

    int insert(TicketOrderDetailVO entity);

    int update(TicketOrderDetailVO entity);

    int delete(Integer orderId);

    TicketOrderDetailVO getById(TicketOrderDetailVO.CompositeDetail id);
    List<TicketOrderDetailVO> getByOrderId(Integer orderId);

    //join票券表格取得訂單明細中的票券名稱
    List<Object[]> getResult(Integer orderId);

//    List<TicketOrderDetailVO> getAll();

    List<TicketOrderDetailVO> getAll(int currentPage);

    long getTotal(Integer orderId);
    
    //取得一個票券的所有訂單明細
	List<TicketOrderDetailVO> findByTicketId(Integer ticketId);
}
