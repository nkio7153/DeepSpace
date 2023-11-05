package com.depthspace.memticketowned.model.hibernate;

import com.depthspace.memticketowned.model.MemTicketDetails;
import com.depthspace.memticketowned.model.MemTicketOwnedVO;

import java.util.List;

public interface HbMtoDao {
    void insertBatch(List<MemTicketOwnedVO> mtoList);

    int update(MemTicketOwnedVO entity);

    int delete(Integer id);

    MemTicketOwnedVO getById(Integer id);

    List<MemTicketDetails> getByMemId(Integer memId, int currentPage);

    long getTotalByMemId(Integer memId);
    List<MemTicketOwnedVO> getAll();
}
