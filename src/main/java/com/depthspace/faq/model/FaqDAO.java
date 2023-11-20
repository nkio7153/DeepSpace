package com.depthspace.faq.model;

import java.util.List;

import com.depthspace.ticketshoppingcart.model.TicketInfoVO;

public interface FaqDAO {
    void insert(FaqVO faqVO);
    void update(FaqVO faqVO);
    void delete(Integer serialId);

    FaqVO findByPrimaryKey(Integer serialId);
    List<FaqVO> getAllFaqs();
 
    List<FaqVO> findAllWithFaqTypes(); // 添加一個新方法來獲取帶有類型信息的FAQ列表
    
    List<String> getAllQTypes();
}

