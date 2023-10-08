package com.depthspace.faq.model.faq;

import java.util.List;

public interface FaqDAO {
    void insertFaq(FaqVO faqVO);
    void updateFaq(FaqVO faqVO);
    void deleteFaq(Integer serialId);

    FaqVO findByPrimaryKey(Integer serialId);
    List<FaqVO> getAllFaqs();
}

