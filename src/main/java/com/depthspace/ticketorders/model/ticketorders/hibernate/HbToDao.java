package com.depthspace.ticketorders.model.ticketorders.hibernate;

import com.depthspace.ticketorders.model.ticketorders.TicketOrdersVO;

import java.util.List;
import java.util.Map;

public interface HbToDao {
    int insert(TicketOrdersVO entity);

    int update(TicketOrdersVO entity);

    int delete(Integer id);

    TicketOrdersVO getById(Integer id);

    List<TicketOrdersVO> getByMemId(Integer MemId);

    List<TicketOrdersVO> getAll(int currentPage);
    List<TicketOrdersVO> getAll();

    List<TicketOrdersVO> getAllByMemId(int currentPage, Integer memId);

    long getTotal();
    //取得最新一筆訂單

    TicketOrdersVO getLatestOrderByMemId(Integer memId);

    long getTotalByMemId(Integer memId);

    List<TicketOrdersVO> getByCompositeQuery(Map<String, String> map, int page);
    int getResultTotal(Map<String, String> map);
}
