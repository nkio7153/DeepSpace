package com.depthspace.faq.model;

import java.util.List;

import com.depthspace.ticketshoppingcart.model.TicketInfoVO;

public interface FaqDAO {
    void insert(FaqVO faqVO);
    void update(FaqVO faqVO);
    void delete(Integer serialId);

    FaqVO findByPrimaryKey(Integer serialId);
    List<FaqVO> getAllFaqs();
}

