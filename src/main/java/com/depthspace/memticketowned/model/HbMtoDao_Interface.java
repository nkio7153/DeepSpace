package com.depthspace.memticketowned.model;

import java.util.List;

public interface HbMtoDao_Interface {
    int insert(MemTicketOwnedVO entity);

    int update(MemTicketOwnedVO entity);

    int delete(Integer id);

    MemTicketOwnedVO getById(Integer id);

    List<MemTicketOwnedVO> getByMemId(Integer memId);
    List<MemTicketOwnedVO> getAll();

    List<MemTicketOwnedVO> getAll(int currentPage);

    long getTotal();
}
